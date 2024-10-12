package de.neeroxz.ui;
import de.neeroxz.util.AppStrings;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Class: AbstractConsolePanel
 *
 * @author NeeroxZ
 * @date 12.10.2024
 */
public abstract class AbstractConsolePanel {

    private List<MenuAction> menuActions = new ArrayList<>();  // Liste der Menüaktionen
    private Scanner scanner = new Scanner(System.in);

    public AbstractConsolePanel() {
        // Exit-Option immer hinzufügen
        menuActions.add(new MenuAction("Zurück zum Hauptmenü", this::exitPanel));
    }

    // Methode zum Hinzufügen von weiteren Aktionen
    protected void addMenuAction(String name, Runnable action) {
        menuActions.add(new MenuAction(name, action));
    }
    protected void removeMainMenu(){
        menuActions.remove(0);
    }

    // Menü anzeigen und Eingaben verarbeiten
    public void handleInput() {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("\nWählen Sie bitte aus der Auswahl:");

            // Menüoptionen anzeigen
            for (int i = 0; i < menuActions.size(); i++) {
                System.out.println((i + 1) + ". " + menuActions.get(i).getName());
            }

            System.out.print("Ihre Eingabe: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            // Überprüfen, ob die Eingabe gültig ist
            if (input > 0 && input <= menuActions.size()) {
                System.out.println(AppStrings.LINESEPARATOR.getAppString());
                menuActions.get(input - 1).execute();
                if (input == 1) validInput = true;  // Bei Exit das Menü verlassen
            } else {
                System.out.println("Ungültige Auswahl! Bitte erneut versuchen.");
            }
        }
    }

    // Standard Exit-Logik
    protected void exitPanel() {
        System.out.println("Zurück zum Hauptmenü...");
    }

    public abstract void showPanel();
}
