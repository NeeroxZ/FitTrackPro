package de.neeroxz.ui;

/**
 * Class: MenuAction
 *
 * @author NeeroxZ
 * @date 12.10.2024
 */
public class MenuAction {
    private String name;
    private Runnable action;

    public MenuAction(String name, Runnable action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void execute() {
        action.run();
    }
}
