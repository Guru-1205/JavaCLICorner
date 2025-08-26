package NumberConverterSystem.Controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import NumberConverterSystem.Models.User;
import NumberConverterSystem.Models.Conversion;

/**
 * The {@code UserController} class provides static utility methods for managing
 * user accounts,
 * authentication, session conversion data, and persistent storage of user
 * information for the
 * Number Converter System. It supports user creation, verification, session
 * management, and
 * serialization/deserialization of user data to and from disk.
 * <p>
 * This controller is designed to facilitate user-centric operations, including:
 * <ul>
 * <li>Creating new users with unique identifiers.</li>
 * <li>Verifying user credentials for authentication.</li>
 * <li>Retrieving user objects by their unique IDs.</li>
 * <li>Saving and loading the user list to/from a persistent file.</li>
 * <li>Managing session-based conversion histories and exporting them to user
 * history.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Features:</b>
 * <ul>
 * <li>Persistent storage of all users and their conversion histories using Java
 * serialization.</li>
 * <li>Graceful error handling for file I/O operations, with fallback to empty
 * user list if loading fails.</li>
 * <li>Session management for tracking conversions performed in the current
 * session.</li>
 * <li>Support for batch saving of session conversions to historical records,
 * grouped by date.</li>
 * <li>Efficient user lookup by username, password, or unique ID.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 * // Create a new user
 * int userId = UserController.createUser("alice", "password123");
 *
 * // Verify user credentials
 * int verifiedId = UserController.verifyUser("alice", "password123");
 *
 * // Retrieve a user by ID
 * User user = UserController.getUserById(userId);
 *
 * // Save current session conversions to history
 * UserController.saveCurrentSessionConversions(userId);
 * </pre>
 * </p>
 *
 * @author Guru Charan K S
 * @version 1.0
 * @since 2025-08-26
 */
public class UserController {

    /**
     * The list of all registered {@link User} objects in the system.
     * <p>
     * This list is loaded from persistent storage at class initialization and
     * updated
     * whenever users are created or modified.
     * </p>
     */
    public static ArrayList<User> userList;

    /**
     * The absolute file path for storing serialized user data.
     * <p>
     * All user information, including conversion histories, is persisted to this
     * file
     * using Java object serialization.
     * </p>
     */
    private static final String USER_DATA_FILE_PATH = "E:\\CODE GALLATA\\OVERALL NOTES\\LONG CODING & DESIGN PATTERNS\\SAMPLE CODE\\NumberConverterSystem\\Files\\User.txt";

    /**
     * Static initializer block that loads the user list from persistent storage
     * when the class is first loaded.
     * <p>
     * If the file cannot be read or does not exist, the user list is initialized as
     * an empty list.
     * </p>
     */
    static {
        loadUserList();
    }

    /**
     * Saves the current {@link #userList} to the persistent storage file.
     * <p>
     * Uses Java object serialization to write the entire user list, including all
     * user data and conversion histories,
     * to disk. If the operation fails, an error message is printed and
     * {@code false} is returned.
     * </p>
     *
     * @return {@code true} if the user list was successfully saved; {@code false}
     *         if an error occurred.
     */
    public static boolean saveUserList() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE_PATH))) {
            oos.writeObject(userList);
            return true;
        } catch (Exception e) {
            System.out.println("There was a problem while saving the user file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads the {@link #userList} from the persistent storage file.
     * <p>
     * Uses Java object deserialization to read the user list from disk. If the file
     * cannot be read,
     * does not exist, or contains invalid data, the user list is initialized as an
     * empty list and an error
     * message is printed.
     * </p>
     *
     * @implNote This method is called automatically in the static initializer
     *           block.
     */
    @SuppressWarnings("unchecked")
    public static void loadUserList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE_PATH))) {
            userList = (ArrayList<User>) ois.readObject();
        } catch (Exception e) {
            userList = new ArrayList<>(); // fallback to empty list
            System.out.println("There was a problem while loading the user file.");
        }
    }

    /**
     * Creates a new user with the specified username and password.
     * <p>
     * Validates that both username and password are non-null and non-empty.
     * Generates a unique integer ID
     * for the user using {@link Random}. Adds the new user to the {@link #userList}
     * and persists the updated list.
     * If saving fails, the newly created user is removed from the list.
     * </p>
     *
     * @param username The username for the new user. Must not be {@code null} or
     *                 empty.
     * @param password The password for the new user. Must not be {@code null} or
     *                 empty.
     * @return The unique integer ID assigned to the new user, or {@code -1} if
     *         creation failed due to invalid input or save error.
     */
    public static int createUser(String username, String password) {
        if (username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
            int id = new Random().nextInt();
            userList.add(new User(id, username, password));
            if (!saveUserList()) {
                userList.removeIf(user -> user.username.equals(username));
            }
            return id;
        }
        return -1;
    }

    /**
     * Verifies the credentials of a user by matching the username and password.
     * <p>
     * Iterates through the {@link #userList} to find a user whose username and
     * password match the provided values.
     * Returns the user's unique ID if a match is found, or {@code -1} if no
     * matching user exists.
     * </p>
     *
     * @param username The username to verify.
     * @param password The password to verify.
     * @return The unique integer ID of the verified user, or {@code -1} if
     *         verification fails.
     */
    public static int verifyUser(String username, String password) {
        for (User user : userList) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user.id;
            }
        }
        return -1;
    }

    /**
     * Retrieves a {@link User} object by its unique ID.
     * <p>
     * Searches the {@link #userList} for a user whose {@code id} matches the
     * provided value.
     * Returns the {@link User} object if found, or {@code null} if no such user
     * exists.
     * </p>
     *
     * @param userId The unique integer ID of the user to retrieve.
     * @return The {@link User} object with the specified ID, or {@code null} if not
     *         found.
     */
    public static User getUserById(int userId) {
        for (User user : userList) {
            if (user.id == userId) {
                return user;
            }
        }
        return null;
    }

    /**
     * Saves all conversions performed in the current session for the specified user
     * to their historical record.
     * <p>
     * Retrieves the user by ID, and if the user exists, appends all conversions
     * from the user's
     * {@code currentSessionConversions} list to their {@code conversionHistory} for
     * the current date.
     * Clears the session conversions after saving and persists the updated user
     * list.
     * If the user does not exist, prints an error message and does nothing.
     * </p>
     *
     * @param userId The unique integer ID of the user whose session conversions are
     *               to be saved.
     */
    public static void saveCurrentSessionConversions(int userId) {
        User u = UserController.getUserById(userId);
        if (u == null) {
            System.out.println("User dont exist !!!");
            return;
        }
        ArrayList<Conversion> conversionsToday = u.conversionHistory.getOrDefault(LocalDate.now(), new ArrayList<>());
        conversionsToday.addAll(u.currentSessionConversions);
        u.conversionHistory.put(LocalDate.now(), conversionsToday);
        u.currentSessionConversions.clear();
        UserController.saveUserList();
    }

}
