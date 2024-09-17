import java.time.LocalDateTime; // Für das Datum und die Uhrzeit
import java.time.format.DateTimeFormatter; // Formatierung des Zeitstempels
import java.util.ArrayList;
import java.util.Scanner;

// Todo-Klasse mit Zeitstempel und "Checked"-Variable
class Todo {
    String task; // Aufgabe
    LocalDateTime timestamp; // Zeitpunkt der Erstellung
    boolean checked; // Gibt an, ob die Aufgabe erledigt ist (true = erledigt, false = unerledigt)

    // Konstruktor, um ein neues Todo zu erstellen
    public Todo(String task) {
        this.task = task;
        this.timestamp = LocalDateTime.now(); // Setzt den aktuellen Zeitpunkt als Zeitstempel
        this.checked = false; // Standardmäßig ist die Aufgabe unerledigt
    }

    // Methode, um das Todo als erledigt oder unerledigt zu markieren
    public void toggleChecked() {
        this.checked = !this.checked; // Wechselt zwischen true und false
    }

    // Überschreibt die toString()-Methode, um das Todo mit Zeitstempel und Status anzuzeigen
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter); // Formatierung des Zeitstempels
        String status = checked ? "[Erledigt]" : "[Unerledigt]"; // Statusanzeige je nach checked-Status
        return task + " (Erstellt am: " + formattedTimestamp + ") " + status;
    }
}

public class Main {
    // Scanner-Objekt für Benutzereingaben und ArrayList zur Speicherung der Todos
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Todo> todoList = new ArrayList<>();

    // Menüoptionen, die dem Benutzer angezeigt werden
    final static String OPTIONS = "----------\n" +
            "New : Neue Todo Erstellen\n" +
            "delete : Löscht eine Todo\n" +
            "list : Zeigt alle Todos an\n" +
            "check : Markiert ein Todo als erledigt/unerledigt\n" +
            "exit : Beenden des Programms\n";

    public static void main(String[] args) {
        // Begrüßung des Benutzers
        System.out.println("Geben Sie Ihren Namen ein:");
        String name = sc.nextLine();
        System.out.println("Hallo " + name);

        // Hauptschleife des Programms
        boolean running = true;
        while (running) {
            // Menüoptionen anzeigen
            System.out.println(OPTIONS);
            System.out.println("Bitte wählen Sie eine Option:");
            String choice = sc.nextLine().toLowerCase(); // Benutzereingabe

            // Schalter zur Verarbeitung der Benutzerauswahl
            switch (choice) {
                case "new":
                    createTodo(); // Neue Todo erstellen
                    break;
                case "delete":
                    deleteTodo(); // Vorhandene Todo löschen
                    break;
                case "list":
                    listTodos(); // Alle Todos anzeigen
                    break;
                case "check":
                    checkTodo(); // Todo als erledigt/unerledigt markieren
                    break;
                case "exit":
                    System.out.println("Programm beendet.");
                    running = false; // Programm beenden
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte versuchen Sie es erneut.");
                    break;
            }
        }
    }

    // Methode, um eine neue Aufgabe zu erstellen und der Liste hinzuzufügen
    public static void createTodo() {
        System.out.println("Geben Sie eine neue Aufgabe ein:");
        String task = sc.nextLine(); // Benutzereingabe
        todoList.add(new Todo(task)); // Neue Aufgabe zur ArrayList hinzufügen
        System.out.println("Todo hinzugefügt!");
    }

    // Methode, um alle Todos in der Liste anzuzeigen
    public static void listTodos() {
        // Überprüfung, ob die Liste leer ist
        if (todoList.isEmpty()) {
            System.out.println("Keine Todos vorhanden.");
        } else {
            System.out.println("Ihre Todos:");
            for (int i = 0; i < todoList.size(); i++) {
                // Alle Todos mit Index anzeigen
                System.out.println((i + 1) + ": " + todoList.get(i));
            }
        }
    }

    // Methode, um eine Aufgabe als erledigt oder unerledigt zu markieren
    public static void checkTodo() {
        if (todoList.isEmpty()) {
            System.out.println("Keine Todos vorhanden, die markiert werden können.");
            return;
        }
        System.out.println("Welches Todo möchten Sie als erledigt/unerledigt markieren? Geben Sie die Nummer ein:");
        listTodos();
        try {
            String input = sc.nextLine(); // Benutzereingabe lesen
            int index = Integer.parseInt(input) - 1; // 1-basierte Benutzereingabe, 0-basierter Index

            // Überprüfen, ob der Index gültig ist
            if (index >= 0 && index < todoList.size()) {
                todoList.get(index).toggleChecked(); // Wechselt den Status des Todos
                System.out.println("Todo-Status geändert!");
            } else {
                System.out.println("Ungültige Nummer.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl ein.");
        }
    }

    // Methode, um ein Todo zu löschen
    public static void deleteTodo() {
        if (todoList.isEmpty()) {
            System.out.println("Keine Todos zum Löschen vorhanden.");
            return;
        }
        System.out.println("Welches Todo möchten Sie löschen? Geben Sie die Nummer ein:");
        listTodos();
        try {
            String input = sc.nextLine(); // Benutzereingabe lesen
            int index = Integer.parseInt(input) - 1; // 1-basierte Benutzereingabe, 0-basierter Index

            // Überprüfen, ob der Index gültig ist
            if (index >= 0 && index < todoList.size()) {
                todoList.remove(index); // Todo aus der Liste entfernen
                System.out.println("Todo gelöscht!");
            } else {
                System.out.println("Ungültige Nummer.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl ein.");
        }
    }
}

