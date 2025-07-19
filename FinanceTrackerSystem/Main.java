import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;
import java.util.Locale.Category;

import Controllers.UserController;
import Menus.AccountMenu;
import Menus.CategoryMenu;
import Menus.ProfileMenu;
import Models.Account;
import Models.enums.UserType;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

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

    public static void showAdminFeaturesMenu(UUID userId) {

    }

    public static void showUserFeaturesMenu(UUID userId) {
        System.out.print("\n User Features Menu:");
        while (true) {
            System.out.print("\n1. Manage Profile");
            System.out.print("\n2. Manage Accounts");
            System.out.print("\n3. Manage Categories");
            int userOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (userOption) {
                case 1 -> ProfileMenu.menu(userId);
                case 2 -> AccountMenu.menu(userId);
                case 3 -> CategoryMenu.menu(userId);
                case 7 -> {
                    System.out.println("The User is logged out.");
                    UserController.currentUser = null; // Clear the current user
                }
            }
        }
    }

}
