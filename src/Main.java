import java.util.ArrayList;
import java.util.Scanner;

class Todo {
    String task;

    public Todo(String task) {
        this.task = task;
    }

    public String toString() {
        return task;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Todo> todoList = new ArrayList<>();

    // Menu options
    final static String OPTIONS = "----------\n" +
            "New : Neue Todo Erstellen\n" +
            "delete : Löscht eine Todo\n" +
            "list : Zeigt alle Todos an\n" +
            "exit : Beenden des Programms\n";

    public static void main(String[] args) {
        // Greeting message
        System.out.println("Geben Sie Ihren Namen ein:");
        String name = sc.nextLine();
        System.out.println("Hallo " + name);

        // Main loop
        boolean running = true;
        while (running) {
            System.out.println(OPTIONS);
            System.out.println("Bitte wählen Sie eine Option:");
            String choice = sc.nextLine().toLowerCase();

            switch (choice) {
                case "new":
                    createTodo();
                    break;
                case "delete":
                    deleteTodo();
                    break;
                case "list":
                    listTodos();
                    break;
                case "exit":
                    System.out.println("Programm beendet.");
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte versuchen Sie es erneut.");
                    break;
            }
        }
    }

    // Method to create a new Todo
    public static void createTodo() {
        System.out.println("Geben Sie eine neue Aufgabe ein:");
        String task = sc.nextLine();
        todoList.add(new Todo(task));
        System.out.println("Todo hinzugefügt!");
    }

    // Method to list all Todos
    public static void listTodos() {
        if (todoList.isEmpty()) {
            System.out.println("Keine Todos vorhanden.");
        } else {
            System.out.println("Ihre Todos:");
            for (int i = 0; i < todoList.size(); i++) {
                System.out.println((i + 1) + ": " + todoList.get(i));
            }
        }
    }

    // Method to delete a Todo
    public static void deleteTodo() {
        if (todoList.isEmpty()) {
            System.out.println("Keine Todos zum Löschen vorhanden.");
            return;
        }
        System.out.println("Welches Todo möchten Sie löschen? Geben Sie die Nummer ein:");
        listTodos();
        int index = sc.nextInt() - 1; // Arrays are 0-indexed, user input is 1-indexed
        sc.nextLine(); // Consume newline

        if (index >= 0 && index < todoList.size()) {
            todoList.remove(index);
            System.out.println("Todo gelöscht!");
        } else {
            System.out.println("Ungültige Nummer.");
        }
    }
}
