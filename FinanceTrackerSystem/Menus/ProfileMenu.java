package Menus;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import Controllers.UserController;
import Controllers.UserProfileController;

public class ProfileMenu {
    public static Scanner scanner = new Scanner(System.in);

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