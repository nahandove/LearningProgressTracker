package tracker;

import java.util.*;

public class Statistics {
    private final Registrar registrar;

    public Statistics(Registrar registrar) {
        this.registrar = registrar;
    }

    public void listGeneralStatistics() {
        ConsoleHelper.writeMessage("Most popular: " + listMostPopularCourses());
        ConsoleHelper.writeMessage("Least popular: " + listLeastPopularCourses());
        ConsoleHelper.writeMessage("Highest activity: " + listMostActiveCourses());
        ConsoleHelper.writeMessage("Lowest activity: " + listLeastActiveCourses());
        ConsoleHelper.writeMessage("Easiest course: " + listEasiestCourses());
        ConsoleHelper.writeMessage("Hardest course: " + listHardestCourses());
    }

    public void printStatisticsByCourse(String course) {
        if ("java".equalsIgnoreCase(course)) {
            ConsoleHelper.writeMessage("Java\nid      points  completed");
            printStudentsRecordByGrade("Java");
        } else if ("DSA".equalsIgnoreCase(course)) {
            ConsoleHelper.writeMessage("DSA\nid      points  completed");
            printStudentsRecordByGrade("DSA");
        } else if ("databases".equalsIgnoreCase(course)) {
            ConsoleHelper.writeMessage("Databases\nid      points  completed");
            printStudentsRecordByGrade("Databases");
        } else if ("spring".equalsIgnoreCase(course)) {
            ConsoleHelper.writeMessage("Spring\nid      points  completed");
            printStudentsRecordByGrade("Spring");
        } else {
            ConsoleHelper.writeMessage("Unknown course");
        }
    }

    private void printStudentsRecordByGrade(String courseName) {
        List<Student> enrolledStudents = new ArrayList<>();
        registrar.getStudents().forEach(student -> {
            if (student.getCourseByName(courseName).getAssignments() > 0) {
                enrolledStudents.add(student);
            }
        });

        List<Student> enrolledStudentSorted = new ArrayList<>();
        if (enrolledStudents.size() == 0) {

        } else if (enrolledStudents.size() == 1) {
            enrolledStudentSorted = enrolledStudents;
        } else {
            enrolledStudentSorted = sortStudentByGrade(enrolledStudents, courseName);
        }

        enrolledStudentSorted.forEach(student -> {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%-8d%-8d%.1f%%", student.getId(),
                    student.getCourseByName(courseName).getCredits(),
                    student.getCourseByName(courseName).getPercentCompletion()));
        });
    }

    private String listMostPopularCourses() {
        if (registrar.getStudents().size() == 0) {
            return "n/a";
        }
        List<Map.Entry<String, Integer>> sortedCourse = sortCoursesByPopularity();
        if (sortedCourse.isEmpty()) {
            return "n/a";
        }
        int maxEnrollment = sortedCourse.get(0).getValue();
        List<String> popularCourses = new ArrayList<>();
        sortedCourse.forEach(course -> {
            if (course.getValue() == maxEnrollment) {
                popularCourses.add(course.getKey());
            }
        });
        return String.join(", ", popularCourses);
    }

    private String listLeastPopularCourses() {
        if (registrar.getStudents().size() == 0) {
            return "n/a";
        }

        List<Map.Entry<String, Integer>> sortedCourses = sortCoursesByPopularity();
        if (sortedCourses.isEmpty()) {
            return "n/a";
        }

        int maxEnrollment = sortedCourses.get(0).getValue();
        int minEnrollment = sortedCourses.get(sortedCourses.size() - 1).getValue();
        if (minEnrollment == maxEnrollment) {
            return "n/a";
        }
        List<String> leastPopularCourses = new ArrayList<>();
        sortedCourses.forEach(course -> {
            if (course.getValue() == minEnrollment) {
                leastPopularCourses.add(course.getKey());
            }
        });
        return String.join(", ", leastPopularCourses);
    }

    private String listMostActiveCourses() {
        if (registrar.getStudents().size() == 0) {
            return "n/a";
        }

        List<Map.Entry<String, Integer>> sortedCourse = sortCoursesByActivity();
        if (sortedCourse.isEmpty()) {
            return "n/a";
        }

        int maxActivity = sortedCourse.get(0).getValue();
        List<String> mostActiveCourse = new ArrayList<>();
        sortedCourse.forEach(course -> {
            if (course.getValue() == maxActivity) {
                mostActiveCourse.add(course.getKey());
            }
        });
        return String.join(", ", mostActiveCourse);
    }

    private String listLeastActiveCourses() {
        if (registrar.getStudents().size() == 0) {
            return "n/a";
        }

        List<Map.Entry<String, Integer>> sortedCourses = sortCoursesByActivity();
        if (sortedCourses.isEmpty()) {
            return "n/a";
        }

        int maxActivity = sortedCourses.get(0).getValue();
        int minActivity = sortedCourses.get(sortedCourses.size() - 1).getValue();
        if (minActivity == maxActivity) {
            return "n/a";
        }

        List<String> leastActiveCourse = new ArrayList<>();
        sortedCourses.forEach(course -> {
            if (course.getValue() == minActivity) {
                leastActiveCourse.add(course.getKey());
            }
        });
        return String.join(", ", leastActiveCourse);
    }

    private String listEasiestCourses() {
        if (registrar.getStudents().size() == 0) {
            return "n/a";
        }

        List<Map.Entry<String, Double>> sortedCourses = sortCoursesByDifficulty();
        if (sortedCourses.isEmpty()) {
            return "n/a";
        }

        double highestAvgScore = sortedCourses.get(sortedCourses.size() - 1).getValue();
        List<String> easiestCourses = new ArrayList<>();
        sortedCourses.forEach(course -> {
            if (course.getValue() == highestAvgScore) {
                easiestCourses.add(course.getKey());
            }
        });
        return String.join(", ", easiestCourses);
    }

    private String listHardestCourses() {
        if (registrar.getStudents().size() == 0) {
            return "n/a";
        }

        List<Map.Entry<String, Double>> sortedCourses = sortCoursesByDifficulty();
        if (sortedCourses.isEmpty()) {
            return "n/a";
        }

        double highestAvgScore = sortedCourses.get(sortedCourses.size() - 1).getValue();
        double lowestAvgScore = sortedCourses.get(0).getValue();
        if (lowestAvgScore == highestAvgScore) {
            return "n/a";
        }

        List<String> hardestCourses = new ArrayList<>();
        sortedCourses.forEach(course -> {
            if (course.getValue() == lowestAvgScore) {
                hardestCourses.add(course.getKey());
            }
        });
        return String.join(", ", hardestCourses);
    }

    private List<Map.Entry<String, Integer>> sortCoursesByPopularity() {
        int javaStudents = 0;
        int dsaStudents = 0;
        int databasesStudents = 0;
        int springStudents = 0;

        for (Student student: registrar.getStudents()) {
            if (student.getCourseByName("Java").getAssignments() > 0) {
                javaStudents++;
            }
            if (student.getCourseByName("DSA").getAssignments() > 0) {
                dsaStudents++;
            }
            if (student.getCourseByName("Databases").getAssignments() > 0) {
                databasesStudents++;
            }
            if (student.getCourseByName("Spring").getAssignments() > 0) {
                springStudents++;
            }
        }

        if (javaStudents == 0 && dsaStudents == 0 && databasesStudents == 0 && springStudents == 0) {
            return Collections.emptyList();
        }

        Map<String, Integer> popularityMap = new LinkedHashMap<>();
        popularityMap.put("Java", javaStudents);
        popularityMap.put("DSA", dsaStudents);
        popularityMap.put("Databases", databasesStudents);
        popularityMap.put("Spring", springStudents);
        return sortIntegers(popularityMap);
    }

    private List<Map.Entry<String, Integer>> sortCoursesByActivity() {
        int javaActivity = 0;
        int dsaActivity = 0;
        int databasesActivity = 0;
        int springActivity = 0;

        for (Student student: registrar.getStudents()) {
            if (student.getCourseByName("Java").getAssignments() > 0) {
                javaActivity += student.getCourseByName("Java").getAssignments();
            }
            if (student.getCourseByName("DSA").getAssignments() > 0) {
                dsaActivity += student.getCourseByName("DSA").getAssignments();
            }
            if (student.getCourseByName("Databases").getAssignments() > 0) {
                databasesActivity+= student.getCourseByName("Databases").getAssignments();
            }
            if (student.getCourseByName("Spring").getAssignments() > 0) {
                springActivity += student.getCourseByName("Spring").getAssignments();
            }
        }

        if (javaActivity == 0 && dsaActivity == 0 && databasesActivity == 0 && springActivity == 0) {
            return Collections.emptyList();
        }

        Map<String, Integer> activityMap = new LinkedHashMap<>();
        activityMap.put("Java", javaActivity);
        activityMap.put("DSA", dsaActivity);
        activityMap.put("Databases", databasesActivity);
        activityMap.put("Spring", springActivity);
        return sortIntegers(activityMap);
    }

    private List<Map.Entry<String, Double>> sortCoursesByDifficulty() {
        int javaActivity = 0;
        int dsaActivity = 0;
        int databasesActivity = 0;
        int springActivity = 0;
        int javaCredits = 0;
        int dsaCredits = 0;
        int databasesCredits = 0;
        int springCredits = 0;

        for (Student student: registrar.getStudents()) {
            if (student.getCourseByName("Java").getAssignments() > 0) {
                javaActivity += student.getCourseByName("Java").getAssignments();
                javaCredits += student.getCourseByName("Java").getCredits();
            }
            if (student.getCourseByName("DSA").getAssignments() > 0) {
                dsaActivity += student.getCourseByName("DSA").getAssignments();
                dsaCredits += student.getCourseByName("DSA").getCredits();
            }
            if (student.getCourseByName("Databases").getAssignments() > 0) {
                databasesActivity += student.getCourseByName("Databases").getAssignments();
                databasesCredits += student.getCourseByName("Databases").getCredits();
            }
            if (student.getCourseByName("Spring").getAssignments() > 0) {
                springActivity += student.getCourseByName("Spring").getAssignments();
                springCredits += student.getCourseByName("Spring").getCredits();
            }
        }

        if (javaActivity == 0 && dsaActivity == 0 && databasesActivity == 0 && springActivity == 0) {
            return Collections.emptyList();
        }

        Map<String, Double> difficultyMap = new LinkedHashMap<>();
        if (javaActivity != 0) {
            difficultyMap.put("Java", 1.0 * javaCredits / javaActivity);
        }
        if (dsaActivity != 0) {
            difficultyMap.put("DSA", 1.0 * dsaCredits / dsaActivity);
        }
        if (databasesActivity != 0) {
            difficultyMap.put("Databases", 1.0 * databasesCredits / databasesActivity);
        }
        if (springActivity != 0) {
            difficultyMap.put("Spring", 1.0 * springCredits / springActivity);
        }
        return sortDouble(difficultyMap);
    }

    private List<Map.Entry<String, Integer>> sortIntegers(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((t1, t2) -> t2.getValue() - t1.getValue());
        return list;
    }

    private List<Map.Entry<String, Double>> sortDouble(Map<String, Double> map) {
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        if (list.size() == 1) {
            return list;
        }
        list.sort(Map.Entry.comparingByValue());
        return list;
    }

    private List<Student> sortStudentByGrade(List<Student> students, String courseName) {
        students.sort((student1, student2) -> {
            if (student1.getCourseByName(courseName).getCredits() > student2.getCourseByName(courseName).getCredits()) {
                return -1;
            }
            if (student1.getCourseByName(courseName).getCredits() < student2.getCourseByName(courseName).getCredits()) {
                return 1;
            }
            if (student1.getId() < student2.getId()) {
                return -1;
            }
            return 1;
        });
        return students;
    }
}
