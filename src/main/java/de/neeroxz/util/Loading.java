package de.neeroxz.util;

import java.sql.SQLOutput;

/**
 * Class: Loading
 *
 * @author NeeroxZ
 * @date 02.10.2024
 */
public class Loading {
    private String asciiArt =
            "  ______ _           _____                 _____                     \n" +
                    " |  ____| |         |_   _|               |  __ \\                    \n" +
                    " | |__  | |__   __ _| |   _ __   ___ _ __ | |  | | ___ _ __ ___  ___ \n" +
                    " |  __| | '_ \\ / _` | | | | '_ \\ / _ \\ '_ \\| |  | |/ _ \\ '__/ __|/ _ \\\n" +
                    " | |____| | | | (_| | | | | | | |  __/ | | | |__| |  __/ |  \\__ \\  __/\n" +
                    " |______|_| |_|\\__,_| |_| |_| |_|\\___|_| |_|_____/ \\___|_|  |___/\\___|\n";

    public Loading() {
        super();
    }

    public void progressBar(int total) throws InterruptedException {
        int barLength = 50; // Länge des Fortschrittsbalkens
        System.out.println(asciiArt); // Ausgabe der ASCII-Art

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
        System.out.print("\nFertig!\n"); // Abschlussausgabe
    }
}
