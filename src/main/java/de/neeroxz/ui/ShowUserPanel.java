package de.neeroxz.ui;
/**
 * Class: ShowUserPanel
 *
 * @author NeeroxZ
 * @date 12.10.2024
 */
public class ShowUserPanel extends AbstractConsolePanel {

    public ShowUserPanel() {
        // Aktionen im Konstruktor hinzufügen
        addMenuAction("Benutzer speichern", this::saveUser);
        addMenuAction("Benutzer löschen", this::deleteUser);
        handleInput();
    }

    @Override
    public void showPanel() {
        System.out.println("----------------------");
        System.out.println("Benutzerverwaltung:");

    }

    private void saveUser() {
        System.out.println("Speichere Benutzer...");
        // Logik zum Speichern von Benutzern
    }

    private void deleteUser() {
        System.out.println("Lösche Benutzer...");
        // Logik zum Löschen von Benutzern
    }
}

