
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import Controllers.UserController;
import Menus.AccountMenu;
import Menus.BudgetMenu;
import Menus.CategoryMenu;
import Menus.ProfileMenu;
import Menus.TransactionMenu;
import Models.enums.UserType;

/**
 * Main class for the Budget Management System CLI application.
 * Handles user registration, login, and navigation through user/admin features.
 */
public class Main {

    /**
     * Scanner instance for reading user input from the console.
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Entry point for the application.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        showMainMenu();
    }

    /**
     * Displays the main menu and handles user selection for registration, login, or
     * exit.
     */
    public static void showMainMenu() {
        System.out.print("Welcome to the Budget Management System!\n");
        System.out.print("\n1. Create User Profile");
        System.out.print("\n2. Login to User Profile");
        System.out.print("\n3. Exit");
        while (true) {
            int userOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (userOption) {
                case 1 -> registerMenu();
                case 2 -> loginMenu();
                case 3 -> {
                    System.out.println("\nExiting the system. Goodbye!");
                    return;
                }
            }
        }
    }

    /**
     * Handles user registration by collecting user details and creating a profile.
     * Shows relevant feature menus based on user type.
     */
    public static void registerMenu() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        System.out.print("\nEnter your address: ");
        String address = scanner.nextLine();
        System.out.print("\nEnter your phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("\nEnter your date of birth (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        System.out.print("\nEnter user type (ADMIN/REGULAR): ");
        String userTypeInput = scanner.nextLine().toUpperCase();
        UserType userType;
        try {
            userType = UserType.valueOf(userTypeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user type. Please enter ADMIN or REGULAR.");
            return;
        }
        UUID userId = UserController.createUserProfile(name, address, phoneNumber, dob, userType);
        if (userId != null) {
            System.out.println("User profile created successfully with ID: " + userId);
            if (userType == UserType.ADMIN) {
                // Show admin features if the user is an admin
                System.out.printf("\nWelcome, Admin - %s! You can manage users and budgets.", name);
                showAdminFeaturesMenu(userId);
            } else if (userType == UserType.REGULAR) {
                // Show regular user features
                System.out.printf("\nWelcome, User - %s! You can manage your budgets and transactions.", name);
                showUserFeaturesMenu(userId);
            }
        } else {
            System.out.println("Failed to create user profile.");
            return;
        }
    }

    /**
     * Handles user login by verifying credentials and showing relevant feature
     * menus.
     */
    public static void loginMenu() {
        System.out.print("\nEnter your user name :");
        String userName = scanner.nextLine();
        System.out.print("\nEnter your Date of Birth (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        System.out.print("\nEnter your User type (ADMIN/REGULAR): ");
        String userTypeInput = scanner.nextLine().toUpperCase();
        UserType userType;
        try {
            userType = UserType.valueOf(userTypeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user type. Please enter ADMIN or REGULAR.");
            return;
        }
        UUID userId = UserController.getUserIdByProfile(userName, dob, userType);
        if (userId != null) {
            System.out.println("User profile found with ID: " + userId);
            if (userType == UserType.ADMIN) {
                // Show admin features if the user is an admin
                System.out.printf("\nWelcome, Admin - %s! You can manage users and budgets.", userName);
                showAdminFeaturesMenu(userId);
            } else if (userType == UserType.REGULAR) {
                // Show regular user features
                System.out.printf("\nWelcome, User - %s! You can manage your budgets and transactions.", userName);
                showUserFeaturesMenu(userId);
            }
        } else {
            System.out.println("User profile not found.");
            return;
        }
    }

    /**
     * Displays the admin features menu for the given user.
     * 
     * @param userId UUID of the logged-in admin user.
     */
    public static void showAdminFeaturesMenu(UUID userId) {
        // Admin features menu implementation goes here.
    }

    /**
     * Displays the regular user features menu for the given user.
     * Allows navigation to profile, accounts, categories, transactions, budgets,
     * and logout.
     * 
     * @param userId UUID of the logged-in regular user.
     */
    public static void showUserFeaturesMenu(UUID userId) {
        System.out.print("\n User Features Menu:");
        while (true) {
            System.out.print("\n1. Manage Profile");
            System.out.print("\n2. Manage Accounts");
            System.out.print("\n3. Manage Categories");
            System.out.print("\n4. Manage Transactions");
            System.out.print("\n5. Manage Budgets");
            System.out.print("\n6. Logout");
            int userOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (userOption) {
                case 1 -> ProfileMenu.menu(userId);
                case 2 -> AccountMenu.menu(userId);
                case 3 -> CategoryMenu.menu(userId);
                case 4 -> TransactionMenu.menu(userId);
                case 5 -> BudgetMenu.menu(userId);
                case 6 -> {
                    System.out.println("The User is logged out.");
                    UserController.currentUser = null; // Clear the current user
                    System.exit(0); // Exit the application
                }
            }
        }
    }

}
