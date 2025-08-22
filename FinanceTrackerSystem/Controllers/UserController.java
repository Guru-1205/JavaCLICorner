package Controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Models.User;
import Models.UserProfile;
import Models.enums.UserType;

/**
 * Provides static methods to manage user profiles, authentication, and
 * persistence
 * in the Finance Tracker System. This controller supports creating user
 * profiles,
 * loading and saving user data, retrieving users by profile or ID, and managing
 * the currently logged-in user.
 *
 * <h2>Responsibilities</h2>
 * <ul>
 * <li>Create new user profiles and add them to the system.</li>
 * <li>Authenticate users by profile details and user type.</li>
 * <li>Retrieve users by unique ID.</li>
 * <li>Manage the currently logged-in user for session-based operations.</li>
 * <li>Persist all user data to disk and load it on startup.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * UUID userId = UserController.createUserProfile("Alice", "123 Main St", "555-1234", LocalDate.of(1990, 1, 1),
 *         UserType.REGULAR);
 * UserController.getUserIdByProfile("Alice", LocalDate.of(1990, 1, 1), UserType.REGULAR);
 * User user = UserController.getUserById(userId);
 * UserController.saveUsersDetails();
 * UserController.loadUsersDetails();
 * User current = UserController.getCurrentUser();
 * </pre>
 *
 * <h2>Dependencies</h2>
 * <ul>
 * <li>{@link Models.User} - The user model being managed.</li>
 * <li>{@link Models.UserProfile} - The profile details for each user.</li>
 * <li>{@link Models.enums.UserType} - Enum for user type (ADMIN, REGULAR).</li>
 * </ul>
 *
 * <h2>Thread Safety</h2>
 * <p>
 * This class is <b>not thread-safe</b>. All operations assume single-threaded
 * access to the static user list and current user.
 * If used in a multi-threaded environment, external synchronization is
 * required.
 * </p>
 *
 * <h2>Persistence</h2>
 * <p>
 * User data is serialized to and deserialized from the file specified by
 * {@link #USER_PROFILE_FILE}.
 * The static block ensures user data is loaded when the class is first
 * accessed.
 * </p>
 *
 * <h2>Validation</h2>
 * <p>
 * This class performs basic validation (e.g., existence of user, matching
 * profile details).
 * Further validation (such as input format or duplicate users) should be
 * handled externally.
 * </p>
 *
 * <h2>Extensibility</h2>
 * <p>
 * Additional user operations (such as password management, session expiration,
 * or audit logging) can be added as needed.
 * </p>
 *
 * @author Finance Tracker System
 * @version 1.0
 */
public class UserController {
    /**
     * Path to the file where user profiles are serialized and stored.
     * This should be an absolute path to ensure consistent access across sessions.
     */
    public final static String USER_PROFILE_FILE = "E:\\CODE GALLATA\\JavaCLICorner\\FinanceTrackerSystem\\Files\\UsersDetails.txt";

    /**
     * List of all users in the system.
     * This list is populated on startup and updated whenever users are added or
     * modified.
     */
    public static List<User> users = new ArrayList<>();

    /**
     * The currently logged-in user.
     * This field is set during authentication and used for session-based
     * operations.
     */
    public static User currentUser;

    /**
     * Static block to load user details when the class is first loaded.
     * Ensures that the user list is populated from persistent storage before any
     * operations are performed.
     */
    static {
        UserController.loadUsersDetails();
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return The current {@link User} object, or null if no user is logged in.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Saves all user details to the file specified by {@link #USER_PROFILE_FILE}.
     * This method should be called after any modification to user data to ensure
     * persistence.
     * Uses Java serialization to write the user list to disk.
     *
     * @return true if user details are saved successfully; false otherwise.
     */
    public static boolean saveUsersDetails() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_PROFILE_FILE))) {
            oos.writeObject(users);
            return true;
        } catch (Exception e) {
            System.out.println("Error saving user details: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads all user details from the file specified by {@link #USER_PROFILE_FILE}.
     * Populates the static {@link #users} list. If the file does not exist or is
     * corrupted,
     * the user list remains empty and an error message is printed.
     * Uses Java deserialization to read the user list from disk.
     */
    @SuppressWarnings("unchecked")
    public static void loadUsersDetails() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_PROFILE_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading user details: " + e.getMessage());
        }
    }

    /**
     * Creates a new user profile and adds it to the system.
     * Also sets the newly created user as the current user for the session.
     * The new user is persisted to disk.
     *
     * @param name        Name of the user.
     * @param address     Address of the user.
     * @param phoneNumber Phone number of the user.
     * @param dob         Date of birth of the user.
     * @param type        Type of the user (e.g., ADMIN, REGULAR).
     * @return UUID of the newly created user if successful; null otherwise.
     */
    public static UUID createUserProfile(String name, String address, String phoneNumber, LocalDate dob,
            UserType type) {
        User user = new User(new UserProfile(name, address, phoneNumber, dob), type);
        users.add(user);
        if (UserController.saveUsersDetails()) {
            System.out.println("User profile created successfully!");
            currentUser = user; // Set the current user
            return user.getId();
        } else {
            System.out.println("Failed to create user profile.");
            return null;
        }
    }

    /**
     * Authenticates a user by matching profile details and user type.
     * If a matching user is found, sets the user as the current user for the
     * session.
     * Prints a success or failure message to the console.
     *
     * @param name Name of the user.
     * @param dob  Date of birth of the user.
     * @param type Type of the user.
     * @return UUID of the user if authentication is successful; null otherwise.
     */
    public static UUID getUserIdByProfile(String name, LocalDate dob, UserType type) {
        for (User user : users) {
            if (user.getProfile().getName().equals(name) && user.getProfile().getDob().equals(dob)
                    && user.getType() == type) {
                System.out.println("Login successful for user: " + name);
                currentUser = user; // Set the current user
                return user.getId();
            }
        }
        System.out.println("Login failed. User not found.");
        return null;
    }

    /**
     * Retrieves a user by their unique ID.
     * Searches the static {@link #users} list for a user with the specified UUID.
     *
     * @param userId UUID of the user.
     * @return {@link User} object if found; null otherwise.
     */
    public static User getUserById(UUID userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

}
