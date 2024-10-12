package de.neeroxz.user;

/**
 * Class: User
 *
 * @author NeeroxZ
 * @date 01.10.2024
 */
import de.neeroxz.util.PasswordHasher;

import java.time.LocalDate;
import java.time.Period;

public record User(
        String firstName,
        String lastName,
        String username,
        String gender,
        double weight,
        double height,
        LocalDate birthDate,
        String hashedPassword
) {

    // Berechnung des Alters aus dem Geburtsdatum
    public int getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    // Benutzerinformationen anzeigen
    public void printUserInfo() {
        System.out.println("Benutzer: " + firstName + " " + lastName);
        System.out.println("Alter: " + getAge() + " Jahre");
        System.out.println("Größe: " + height + " cm");
        System.out.println("Gewicht: " + weight + " kg");
        System.out.println("Geschlecht: " + gender);
        System.out.println("Benutzername: " + username);
    }

    // Static factory method to create a User with hashed password
    public static User createUserWithHashedPassword(
            String firstName,
            String lastName,
            String username,
            String gender,
    double weight,
    double height,
    LocalDate birthDate,
    String password,
    PasswordHasher passwordHasher
    ) throws Exception {
        String hashedPassword = passwordHasher.hash(password);  // Passwort verschlüsseln
        return new User(firstName, lastName, username, gender, weight, height, birthDate, hashedPassword);
    }
}

