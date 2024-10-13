package de.neeroxz.db;

import de.neeroxz.user.User;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class JsonUserRepositoryTest {

    private JsonUserRepository userRepository;
    private File tempFile;

    @BeforeEach
    public void setup() throws Exception {
        // Temporäre Datei für die JSON-Datenbank erstellen
        tempFile = File.createTempFile("userDatabase", ".json");
        userRepository = new JsonUserRepository(tempFile.getAbsolutePath());
    }

    @AfterEach
    public void teardown() {
        // Löschen der temporären Datei nach jedem Test
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testSaveUser_Success() throws Exception {
        User user = new User("Johannes", "Knecht", "Knechti", "male", 70.0, 180.0, "1990-01-01", "start123LULDHBW");
        boolean result = userRepository.saveUser(user);
        assertTrue(result, "Der Benutzer sollte erfolgreich gespeichert werden.");
    }

    @Test
    public void testSaveUser_Failure_DuplicateUsername() throws Exception {
        User user1 = new User("Jochaim", "Jochaim", "Jochaim", "male", 70.0, 180.0, "1990-01-01", "hashedpassword");
        User user2 = new User("Jochaim", "Jochaim", "Jochaim", "female", 65.0, 170.0, "1992-02-02", "hashedpassword2");

        userRepository.saveUser(user1);  // Speichere den ersten Benutzer
        boolean result = userRepository.saveUser(user2);  // Speichere den zweiten Benutzer mit demselben Benutzernamen

        assertFalse(result, "Der Benutzer sollte nicht gespeichert werden, da der Benutzername bereits existiert.");
    }

    @Test
    public void testGetUserByUsernameAndPassword_Success() throws Exception {
        User user = new User("Nick", "Obreiter", "neeroxz", "male", 65.0, 170.0, "1999-07-06", "hashedpassword");
        userRepository.saveUser(user);

        Optional<User> retrievedUser = userRepository.getUserByUsernameAndPassword("neeroxz", "hashedpassword");

        assertTrue(retrievedUser.isPresent(), "Der Benutzer sollte erfolgreich abgerufen werden.");
        assertEquals("neeroxz", retrievedUser.get().username(), "Der Benutzername sollte 'neeroxz' sein.");
    }

    @Test
    public void testGetUserByUsernameAndPassword_Failure_WrongPassword() throws Exception {
        User user = new User("Nick", "Obreiter", "neeroxz", "male", 65.0, 170.0, "1999-07-06", "hashedpassword");
        userRepository.saveUser(user);

        Optional<User> retrievedUser = userRepository.getUserByUsernameAndPassword("neeroxz", "wrongpassword");

        assertFalse(retrievedUser.isPresent(), "Der Benutzer sollte nicht abgerufen werden, da das Passwort falsch ist.");
    }

    @Test
    public void testDeleteUser_Success() throws Exception {
        User user = new User("Nick", "Obreiter", "neeroxz", "male", 65.0, 170.0, "1999-07-06", "hashedpassword");
        userRepository.saveUser(user);

        boolean result = userRepository.deleteUser("neeroxz", "hashedpassword");
        assertTrue(result, "Der Benutzer sollte erfolgreich gelöscht werden.");
    }

    @Test
    public void testDeleteUser_Failure_WrongPassword() throws Exception {
        User user = new User("Nick", "Obreiter", "neeroxz", "male", 65.0, 170.0, "1999-07-06", "hashedpassword");
        userRepository.saveUser(user);

        boolean result = userRepository.deleteUser("neeroxz", "wrongpassword");
        assertFalse(result, "Der Benutzer sollte nicht gelöscht werden, da das Passwort falsch ist.");
    }
}
