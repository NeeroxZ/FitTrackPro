package de.neeroxz.db;

/**
 * Class: UserRepository
 *
 * @author NeeroxZ
 * @date 12.10.2024
 */
import de.neeroxz.user.User;

import java.util.Optional;

public interface UserRepository {
    // Speichert einen neuen Benutzer, wenn der Benutzername nicht bereits existiert
    boolean saveUser(User user) throws Exception;

    // Lädt einen Benutzer basierend auf Benutzername und Passwort
    Optional<User> getUserByUsernameAndPassword(String username, String password) throws Exception;

    // Löscht einen Benutzer basierend auf dem Benutzernamen
    boolean deleteUser(String username, String password) throws Exception;
}

