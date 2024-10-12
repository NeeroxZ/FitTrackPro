package de.neeroxz.ui;

import de.neeroxz.util.Loading;

/**
 * Class: AppPanel
 *
 * @author NeeroxZ
 * @date 12.10.2024
 */
public class AppPanel extends AbstractConsolePanel{

    public AppPanel() {
        // Menüaktionen dynamisch hinzufügen
        removeMainMenu();
        addMenuAction("User anlegen", this::createUser);
        addMenuAction("User anzeigen", this::showUsers);
        addMenuAction("Training auswählen", this::selectTraining);
        addMenuAction("Beenden", this::exitApp);

    }

    @Override
    public void showPanel() {
        try {
            new Loading().progressBar(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        handleInput();
    }

        private void createUser() {
            System.out.println("User anlegen (noch nicht implementiert).");
        }

        private void showUsers() {
            new ShowUserPanel().showPanel();
        }

        private void selectTraining() {
            System.out.println("Trainingsauswahl (noch nicht implementiert).");
        }
        private void exitApp() {
        System.out.println("Programm wird beendet...");
        System.exit(0);
    }
}


