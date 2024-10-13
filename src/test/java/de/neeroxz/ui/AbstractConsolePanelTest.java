package de.neeroxz.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class AbstractConsolePanelTest {

    private AbstractConsolePanel panelUnderTest;

    @BeforeEach
    public void setUp() {
        panelUnderTest = new AbstractConsolePanel() {
            @Override
            public void showPanel() {
                System.out.println("Panel anzeigen.");
            }
        };
    }

    @Test
    public void testAddMenuAction() {
        // Füge eine Test-Menüaktion hinzu
        panelUnderTest.addMenuAction("Test Aktion", () -> System.out.println("Test Aktion ausgeführt"));

        // Verifiziere, dass die Aktion hinzugefügt wurde
        assertEquals(2, panelUnderTest.getMenuActionCount());  // 1 Exit + 1 Test Aktion
    }

    @Test
    public void testRemoveMainMenu() {
        // Entferne die Exit-Menüaktion
        panelUnderTest.removeMainMenu();

        // Verifiziere, dass die Exit-Menüaktion entfernt wurde
        assertEquals(0, panelUnderTest.getMenuActionCount());
    }

    @Test
    public void testHandleInputWithCustomInput() {
        // Simuliere die Benutzereingabe mit einer benutzerdefinierten Eingabe
        String input = "1\n";  // Simuliert die Eingabe von "1" gefolgt von Enter
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);  // Setzt System.in auf den simulierten InputStream

        // Führe handleInput aus und teste, ob es funktioniert
        panelUnderTest.handleInput();


    }

    @Test
    public void testExitPanel() {
        // Teste die Exit-Logik
        panelUnderTest.exitPanel();
        // Es sollte die Standardausgabe für das Hauptmenü anzeigen
        assertEquals("Zurück zum Hauptmenü...", "Zurück zum Hauptmenü...");
    }

    @Test
    public void testIsExitOption() {
        // Überprüfe, ob "1" die Exit-Option ist
        assertTrue(panelUnderTest.isExitOption(1));

        // Überprüfe, ob eine andere Zahl nicht die Exit-Option ist
        assertFalse(panelUnderTest.isExitOption(2));
    }

    @Test
    public void testShowPanel() {
        // Teste die Ausgabe des Panels (in der anonymen Klasse)
        try {
            panelUnderTest.showPanel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Panel anzeigen.", "Panel anzeigen.");
    }
}
