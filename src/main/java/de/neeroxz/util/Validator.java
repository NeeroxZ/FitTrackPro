package de.neeroxz.util;

/**
 * Class: Validator
 *
 * @author NeeroxZ
 * @date 12.10.2024
 */
public interface Validator {
    boolean validateName(String name);
    boolean validateUsername(String username);
    boolean validateGender(String gender);
    boolean validateWeight(String weight);
    boolean validateHeight(String height);
    boolean validateBirthDate(String birthDate);
    boolean validatePassword(String password);

    // Neue Methode zum Validieren, ob der Benutzername eindeutig ist
    boolean validateUserUnique(String username) throws Exception;
}

