package tracker;

import tracker.course.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registrar {
    List<Student> students;
    private static Registrar instance;
    private Registrar() {
        students = new ArrayList<>();
    }

    public static Registrar getInstance() {
        if (instance == null) {
            instance = new Registrar();
        }
        return instance;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent() throws IOException {
        ConsoleHelper.writeMessage("Enter student credentials or 'back' to return:");
        String input = ConsoleHelper.readString();
        int currentCount = students.size();
        while(!"back".equals(input)) {
            if (checkCredentials(input)) {
                int lastIndex = input.lastIndexOf(" ");
                String name = input.substring(0, lastIndex);
                String email = input.substring(lastIndex + 1);
                int id = 10000 + students.size();
                students.add(new Student(id, name, email));
                ConsoleHelper.writeMessage("The student has been added.");
            }
            input = ConsoleHelper.readString();
        }
        ConsoleHelper.writeMessage(String.format("Total %d students have been added.", students.size() - currentCount));
    }

    public void listStudents() {
        if (students.isEmpty()) {
            ConsoleHelper.writeMessage("No students found");
        } else {
            ConsoleHelper.writeMessage("Students:");
            for (Student student: students) {
                ConsoleHelper.writeMessage(String.valueOf(student.getId()));
            }
        }
    }

    public void addCredits() throws IOException {
        ConsoleHelper.writeMessage("Enter an id and points or 'back' to return:");
        String input = ConsoleHelper.readString();
        while(!"back".equals(input)) {
            if (validateCredits(input)) {
                String[] credits = input.split(" ");
                for (Student student: students) {
                    if (student.getId() == Integer.parseInt(credits[0])) {
                        student.getCourseByName("Java").addCredits(Integer.parseInt(credits[1]));
                        student.getCourseByName("DSA").addCredits(Integer.parseInt(credits[2]));
                        student.getCourseByName("Databases").addCredits(Integer.parseInt(credits[3]));
                        student.getCourseByName("Spring").addCredits(Integer.parseInt(credits[4]));
                        ConsoleHelper.writeMessage("Points updated.");
                    }
                }
            }
            input = ConsoleHelper.readString();
        }
    }

    public void printStudentRecords() throws IOException {
        ConsoleHelper.writeMessage("Enter an id or 'back' to return:");
        String input = ConsoleHelper.readString();
        while(!"back".equals(input)) {
            if (!verifyId(Integer.parseInt(input))) {
                ConsoleHelper.writeMessage("No student is found for id=" + input);
            } else {
                for (Student student: students) {
                    if (student.getId() == Integer.parseInt(input)) {
                        ConsoleHelper.writeMessage(String.format("%d points: %s=%d; %s=%d; %s=%d; %s=%d",
                                student.getId(),
                                student.getCourseByName("Java").getName(), student.getCourseByName("Java").getCredits(),
                                student.getCourseByName("DSA").getName(), student.getCourseByName("DSA").getCredits(),
                                student.getCourseByName("Databases").getName(), student.getCourseByName("Databases").getCredits(),
                                student.getCourseByName("Spring").getName(), student.getCourseByName("Spring").getCredits()));
                    }
                }
            }
            input = ConsoleHelper.readString();
        }
    }

    public void printStatistics() throws IOException {
        ConsoleHelper.writeMessage("Type the name of a course to see details or 'back' to quit:");
        Statistics statistics = new Statistics(this);
        statistics.listGeneralStatistics();
        String input = ConsoleHelper.readString();
        while (!"back".equals(input)) {
            statistics.printStatisticsByCourse(input);
            input = ConsoleHelper.readString();
        }
    }

    public void printNotificationEmails() {
        int notifiedStudents = 0;
        for (Student student: students) {
            boolean hasNotification = false;
            for (Course course: student.getCourses()) {
                if (course.isCompleted() && !course.isNotified()) {
                    ConsoleHelper.writeMessage("To: " + student.getEmail());
                    ConsoleHelper.writeMessage("Re: Your Learning Progress");
                    ConsoleHelper.writeMessage(String.format("Hello, %s! You have accomplished our %s course!",
                            student.getName(), course.getName()));
                    course.setNotified(true);
                    hasNotification = true;
                }
            }
            if (hasNotification) {
                notifiedStudents++;
            }
        }
        ConsoleHelper.writeMessage(String.format("Total %d students have been notified.", notifiedStudents));
    }

    private boolean checkCredentials(String input) {
        if (input.trim().split(" ").length < 3) {
            ConsoleHelper.writeMessage("Incorrect credentials.");
            return false;
        }
        int firstIndex = input.indexOf(" ");
        int lastIndex = input.lastIndexOf(" ");
        String firstName = input.substring(0, firstIndex);
        String lastName = input.substring(firstIndex + 1, lastIndex);
        String email = input.substring(lastIndex + 1);
        if (!verifyFirstName(firstName)) {
            ConsoleHelper.writeMessage("Incorrect first name.");
            return false;
        }
        if (!verifyLastName(lastName)) {
            ConsoleHelper.writeMessage("Incorrect last name.");
            return false;
        }
        if (!verifyUniqueEmail(email)) {
            ConsoleHelper.writeMessage("This email is already taken.");
            return false;
        }
        if (!verifyEmail(email)) {
            ConsoleHelper.writeMessage("Incorrect email.");
            return false;
        }
        return true;
    }

    private boolean verifyFirstName(String firstName) {
        return firstName.matches("([A-Za-z]+[-']?)+[A-Za-z]+");
    }

    private boolean verifyLastName(String lastName) {
        return lastName.matches("([A-Za-z]+[-' ]?)+[A-Za-z]+");
    }

    private boolean verifyUniqueEmail(String email) {
        for (Student student: students) {
            if (student.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyEmail(String email) {

        String regex = "[A-Za-z0-9]+([._-]?+[A-Za-z0-9]+)*@[A-Za-z0-9]+([.][A-Za-z0-9]+)+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean verifyId(int id) {
        for (Student student: students) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean validateCredits(String input) {
        String[] creditPoints = input.split(" ");
        try {
            if (!verifyId(Integer.parseInt(creditPoints[0]))) {
                ConsoleHelper.writeMessage("No student is found for id=" + creditPoints[0]);
                return false;
            }
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage("No student is found for id=" + creditPoints[0]);
            return false;
        }

        try {
            if (creditPoints.length != 5) {
                ConsoleHelper.writeMessage("Incorrect points format.");
                return false;
            }
            for (String creditPoint : creditPoints) {
                if (Integer.parseInt(creditPoint) < 0) {
                    ConsoleHelper.writeMessage("Incorrect points format.");
                    return false;
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            ConsoleHelper.writeMessage("Incorrect points format.");
            return false;
        }
        return true;
    }
}
