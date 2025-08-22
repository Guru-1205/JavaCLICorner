package Controllers;

import java.time.LocalDate;
import Models.UserProfile;
import static Controllers.UserController.currentUser;

/**
 * Provides static methods to manage the profile of the currently logged-in user
 * in the Finance Tracker System. This controller supports viewing, editing, and
 * deleting
 * user profiles, and ensures that changes are persisted to disk.
 *
 * <h2>Responsibilities</h2>
 * <ul>
 * <li>Display the profile details of the current user.</li>
 * <li>Edit the profile details (name, address, phone number, date of birth) of
 * the current user.</li>
 * <li>Delete the profile of the current user from the system.</li>
 * <li>Persist all changes to user data using
 * {@link Controllers.UserController#saveUsersDetails()}.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * UserProfileController.viewProfile();
 * UserProfileController.editProfile("Alice Smith", "456 Elm Ave", "555-5678", LocalDate.of(1990, 1, 1));
 * UserProfileController.deleteProfile();
 * </pre>
 *
 * <h2>Dependencies</h2>
 * <ul>
 * <li>{@link Models.UserProfile} - The profile model being managed.</li>
 * <li>{@link Controllers.UserController} - For accessing {@code currentUser}
 * and saving user details.</li>
 * </ul>
 *
 * <h2>Thread Safety</h2>
 * <p>
 * This class is <b>not thread-safe</b>. All operations assume single-threaded
 * access to {@code currentUser}.
 * If used in a multi-threaded environment, external synchronization is
 * required.
 * </p>
 *
 * <h2>Validation</h2>
 * <p>
 * This class performs basic validation (e.g., existence of current user).
 * Further validation (such as input format or duplicate users) should be
 * handled externally.
 * </p>
 *
 * <h2>Extensibility</h2>
 * <p>
 * Additional profile operations (such as password management, profile picture,
 * or audit logging) can be added as needed.
 * </p>
 *
 * @author Finance Tracker System
 * @version 1.0
 */
public class UserProfileController {

    /**
     * Displays the profile details of the currently logged-in user.
     * Prints "User not found." if no user is logged in.
     * The profile is displayed using its {@link UserProfile#toString()}
     * representation.
     */
    public static void viewProfile() {
        if (currentUser != null) {
            System.out.println("User Profile:");
            System.out.println(currentUser.getProfile().toString());
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Edits the profile details of the currently logged-in user.
     * Updates the name, address, phone number, and date of birth fields.
     * Persists changes to disk and prints a success or failure message.
     *
     * @param name        New name of the user.
     * @param address     New address of the user.
     * @param phoneNumber New phone number of the user.
     * @param dob         New date of birth of the user.
     */
    public static void editProfile(String name, String address, String phoneNumber, LocalDate dob) {
        if (currentUser != null) {
            UserProfile profile = currentUser.getProfile();
            profile.setName(name);
            profile.setAddress(address);
            profile.setPhoneNumber(phoneNumber);
            profile.setDob(dob);
            if (UserController.saveUsersDetails()) {
                System.out.println("Profile updated successfully.");
            } else {
                System.out.println("Failed to update profile.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Deletes the profile of the currently logged-in user from the system.
     * Removes the user from the system-wide user list and persists changes to disk.
     * Prints a success or failure message.
     */
    public static void deleteProfile() {
        if (currentUser != null) {
            UserController.users.remove(currentUser);
            if (UserController.saveUsersDetails()) {
                System.out.println("Profile deleted successfully.");
            } else {
                System.out.println("Failed to delete profile.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
}
