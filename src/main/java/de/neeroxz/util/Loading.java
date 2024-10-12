package de.neeroxz.util;

import de.neeroxz.App;

import java.sql.SQLOutput;

/**
 * Class: Loading
 *
 * @author NeeroxZ
 * @date 02.10.2024
 */
public class Loading {

    public Loading() {
        super();
    }

    public void progressBar(int total) throws InterruptedException {
        int barLength = 50; // Länge des Fortschrittsbalkens
        System.out.println(AppStrings.ASCIIART.getAppString()); // Ausgabe der ASCII-Art

        for (int i = 0; i <= total; i++) {
            // Berechnung des Anteils
            double progress = (double) i / total;
            // Berechnung der Anzahl der Zeichen im Balken
            int chars = (int) (barLength * progress);

            // Erstellen des Fortschrittsbalkens
            StringBuilder bar = new StringBuilder();
            for (int j = 0; j < barLength; j++) {
                if (j < chars) {
                    bar.append("#"); // Teil des Fortschritts
                } else {
                    bar.append("-"); // Unvollendeter Teil
                }
            }

            // Ausgabe des Fortschritts
            System.out.print("\r[" + bar + "] " + (int) (progress * 100) + "%");
            Thread.sleep(100); // Simuliere eine Aufgabe mit Verzögerung
        }
        System.out.print("\nErfolgreich Geladen!\n"); // Abschlussausgabe
    }
}
