package de.neeroxz.util;

/**
 * Class: SimpleValidator
 *
 * @author NeeroxZ
 * @date 12.10.2024
 */
import de.neeroxz.db.UserRepository;
import de.neeroxz.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class SimpleValidator implements Validator {

    private final UserRepository userRepository;

    public SimpleValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validateName(String name) {
        return name != null && name.length() > 1;
    }

    @Override
    public boolean validateUsername(String username) {
        return username != null && username.length() >= 3;
    }

    @Override
    public boolean validateGender(String gender) {
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    @Override
    public boolean validateWeight(String weight) {
        try {
            double w = Double.parseDouble(weight);
            return w > 0;  // Gewicht muss positiv sein
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public boolean validateHeight(String weight) {
        try {
            double w = Double.parseDouble(weight);
            return w > 0;  // Gewicht muss positiv sein
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean validateBirthDate(String birthDate) {
        try {
            LocalDate.parse(birthDate, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean validatePassword(String password) {
        return password != null && password.length() >= 8 && password.matches(".*\\d.*");
    }

    // Überprüfen, ob der Benutzername bereits existiert
    @Override
    public boolean validateUserUnique(String username) throws Exception {
        return true;
        //Optional<User> existingUser = userRepository.getUserByUsernameAndPassword(username, null);
        //return existingUser.isEmpty();  // Gibt true zurück, wenn der Benutzername noch nicht existiert
    }
}
