package de.neeroxz.ui;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class: AbstractConsolePanel
 *
 * @author NeeroxZ
 * @date 12.10.2024
 */
public abstract class AbstractConsolePanel {

    private final List<MenuAction> menuActions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public AbstractConsolePanel() {
        // Exit-Option immer hinzufügen
        addMenuAction("Zurück zum Hauptmenü", this::exitPanel);
    }

    protected void addMenuAction(String name, Runnable action) {
        menuActions.add(new MenuAction(name, action));
    }
    protected void removeMainMenu(){
        menuActions.remove(0);
    }

    protected int getMenuActionCount() {
        return menuActions.size();
    }

    public void handleInput() {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("\nWählen Sie bitte aus der Auswahl:");

            // Menüoptionen anzeigen
            for (int i = 0; i < menuActions.size(); i++) {
                System.out.println((i + 1) + ". " + menuActions.get(i).getName());
            }

            System.out.print("Ihre Auswahl: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Überprüfen, ob die Eingabe gültig ist
            if (choice > 0 && choice <= menuActions.size()) {
                menuActions.get(choice - 1).execute();
                if (isExitOption(choice)) {  // Wenn "Beenden" gewählt wird
                    validInput = true;
                }
            } else {
                System.out.println("Ungültige Auswahl! Bitte erneut versuchen.");
            }
        }
    }

    // Standard Exit-Logik
    protected void exitPanel() {
        System.out.println("Zurück zum Hauptmenü...");
    }

    protected boolean isExitOption(int choice) {
        return choice == 1;  // Standardmäßig ist "1" die Exit-Option
    }

    // Abstrakte Methode, die in den abgeleiteten Klassen implementiert wird
    public abstract void showPanel() throws InterruptedException;
}
