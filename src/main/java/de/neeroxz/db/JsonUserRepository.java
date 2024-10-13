package de.neeroxz.db;

import com.google.gson.*;
import de.neeroxz.user.User;
import de.neeroxz.util.SHA256PasswordHasher;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class JsonUserRepository implements UserRepository {

    private final String dbFilePath;
    private final Gson gson;

    public JsonUserRepository(String dbFilePath) {
        this.dbFilePath = dbFilePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();  // Pretty-Print JSON
    }
    private List<User> loadDatabase() throws IOException {
        File file = new File(dbFilePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();  // Verzeichnis erstellen, falls es nicht existiert
            file.createNewFile();  // Datei erstellen, falls sie nicht existiert
            return new ArrayList<>();  // Rückgabe einer leeren Liste
        }

        try (Reader reader = new FileReader(file)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);

            // Überprüfen, ob der Inhalt ein JSON-Array ist
            if (jsonElement == null || !jsonElement.isJsonArray()) {
                return new ArrayList<>();  // Rückgabe einer neuen leeren Liste
            }

            JsonArray jsonArray = jsonElement.getAsJsonArray();
            List<User> users = new ArrayList<>();

            // Schleife durch jedes Element im JSON-Array
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();

                // Extrahiere die einzelnen Felder
                String firstName = jsonObject.get("firstName").getAsString();
                String lastName = jsonObject.get("lastName").getAsString();
                String username = jsonObject.get("username").getAsString();
                String gender = jsonObject.get("gender").getAsString();
                double weight = jsonObject.get("weight").getAsDouble();
                double height = jsonObject.get("height").getAsDouble();
                String birthDate = jsonObject.get("birthDate").getAsString();
                String hashedPassword = jsonObject.get("hashedPassword").getAsString();

                // Erstelle einen neuen User mit den extrahierten Daten
                User user = new User(firstName, lastName, username, gender, weight, height, birthDate, hashedPassword);
                users.add(user);  // Füge den Benutzer zur Liste hinzu
            }

            return users;  // Rückgabe der Liste von Benutzern
        } catch (Exception e) {
            e.printStackTrace();  // Optional: Fehlerausgabe für Debugging
            return new ArrayList<>();  // Rückgabe einer neuen leeren Liste bei Fehlern
        }
    }



    // Hilfsmethode, um die Benutzerliste als JSON-Datei zu speichern
    private void saveDatabase(List<User> users) throws IOException {
        try (Writer writer = new FileWriter(dbFilePath)) {
            gson.toJson(users, writer);  // Benutzerliste als JSON schreiben
        }
    }


    @Override
    public boolean saveUser(User user) throws Exception {
        List<User> users = loadDatabase();

        // Überprüfen, ob der Benutzername bereits existiert
        if (users.stream().anyMatch(u -> u.username().equals(user.username()))) {
            return false;  // Benutzername bereits vorhanden
        }

        // Erstelle den Benutzer mit gehashtem Passwort, wenn noch nicht vorhanden
        User userWithHashedPassword = User.createUserWithHashedPassword(
                user.firstName(),
                user.lastName(),
                user.username(),
                user.gender(),
                user.weight(),
                user.height(),
                user.birthDate(),
                user.hashedPassword(),  // Übergebe das Passwort zum Hashen
                new SHA256PasswordHasher()  // Verwende den Hasher
        );

        users.add(userWithHashedPassword);
        saveDatabase(users);  // Speichern der aktualisierten Benutzerliste
        return true;
    }

    @Override
    public Optional<User> getUserByUsernameAndPassword(String username, String password) throws Exception {
        List<User> users = loadDatabase();
        return users.stream()
                .filter(user -> {
                    try {
                        return user.username().equals(username) &&
                                user.hashedPassword().equals(new SHA256PasswordHasher().hash(password));
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                })
                .findFirst();  // Benutzer suchen
    }

    @Override
    public boolean deleteUser(String username, String password) throws Exception {
        List<User> users = loadDatabase();

        // Finde den Benutzer anhand des Benutzernamens
        Optional<User> userToDelete = users.stream()
                .filter(user -> user.username().equals(username))
                .findFirst();

        // Debugging: Prüfe, ob der Benutzer gefunden wurde und ob das Passwort übereinstimmt
        if (userToDelete.isPresent()) {
            System.out.println("Benutzer gefunden: " + userToDelete.get().username());
            System.out.println("Vergleiche Passwort: " + userToDelete.get().hashedPassword() + " mit " + new SHA256PasswordHasher().hash(password));
        }

        // Überprüfe, ob der Benutzer existiert und ob das Passwort korrekt ist
        if (userToDelete.isPresent() && userToDelete.get().hashedPassword().equals(new SHA256PasswordHasher().hash(password))) {
            boolean removed = users.removeIf(user -> user.username().equals(username));
            if (removed) {
                saveDatabase(users);  // Speichern der aktualisierten Benutzerliste
            }
            return removed;
        }

        return false;
    }
}
