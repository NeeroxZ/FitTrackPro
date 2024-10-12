package de.neeroxz.ui;

/**
 * Class: CreateUserPanel
 *
 * @author NeeroxZ
 * @date 11.10.2024
 */
public class CreateUserPanel extends AbstractConsolePanel{

    public CreateUserPanel() {
        super();
        super.addMenuAction("Weitere benutzer Anlegen", this::addNewUser);
    }

    private void addNewUser() {
    }

    @Override
    public void showPanel() {

    }
}
