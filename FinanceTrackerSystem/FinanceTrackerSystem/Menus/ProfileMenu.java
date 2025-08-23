package FinanceTrackerSystem.Menus;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import FinanceTrackerSystem.Controllers.UserProfileController;

/**
 * The ProfileMenu class provides a console-based menu for managing the profile
 * of the currently logged-in user in the Finance Tracker System. It allows
 * users
 * to view, edit, and delete their profile.
 *
 * <p>
 * Typical Usage:
 * Call {@link #menu(UUID)} to display the profile management menu for a
 * specific user.
 * </p>
 *
 * <p>
 * Dependencies:
 * <ul>
 * <li>Controllers.UserProfileController</li>
 * <li>java.util.Scanner</li>
 * <li>java.util.UUID</li>
 * <li>java.time.LocalDate</li>
 * </ul>
 * </p>
 */
public class ProfileMenu {
    /** Scanner for reading user input from the console. */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Displays the profile management menu for the specified user.
     * Provides options to view, edit, delete the profile, or return to the main
     * menu.
     *
     * @param userId UUID of the user for whom the menu is displayed.
     */
    public static void menu(UUID userId) {
        System.out.print("\nProfile Management Menu:");
        while (true) {
            System.out.print("1. View Profile");
            System.out.print("2. Edit Profile");
            System.out.print("3. Delete Profile");
            System.out.print("4. Back to Main Menu");
            int userOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (userOption) {
                case 1 -> UserProfileController.viewProfile();
                case 2 -> {
                    System.out.print("\nEnter your new name: ");
                    String name = scanner.nextLine();
                    System.out.print("\nEnter your new address: ");
                    String address = scanner.nextLine();
                    System.out.print("\nEnter your new phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("\nEnter your new date of birth (YYYY-MM-DD): ");
                    LocalDate dob = LocalDate.parse(scanner.nextLine());
                    UserProfileController.editProfile(name, address, phoneNumber, dob);
                }
                case 3 -> UserProfileController.deleteProfile();
                case 4 -> {
                    System.out.println("Returning to Main Menu.");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}