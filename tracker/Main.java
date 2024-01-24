package tracker;
import java.io.IOException;

public class Main {
    public static final Registrar registrar = Registrar.getInstance();

    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("Learning Progress Tracker");
        String command = ConsoleHelper.readString();
        while (!"exit".equals(command)) {
            if (command.isBlank()) {
                System.out.println("No input.");
            } else if ("add students".equals(command)) {
                registrar.addStudent();
            } else if ("list".equals(command)) {
                registrar.listStudents();
            } else if ("add points".equals(command)) {
                registrar.addCredits();
            } else if ("find".equals(command)) {
                registrar.printStudentRecords();
            } else if ("statistics".equals(command)) {
                registrar.printStatistics();
            } else if ("notify".equals(command)) {
                registrar.printNotificationEmails();
            } else if ("back".equals(command)) {
                ConsoleHelper.writeMessage("Enter 'exit' to exit the program.");
            } else {
                System.out.println("Error: unknown command!");
            }
            command = ConsoleHelper.readString();
        }
        System.out.println("Bye!");
    }
}
