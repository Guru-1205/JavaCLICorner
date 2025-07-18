package Menus;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import Controllers.UserController;

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
                case 1 -> UserController.viewProfile(userId);
                case 2 -> {
                    if (UserController.getUserById(userId) == null) {
                        System.out.println("User not found.");
                        return;
                    } else {
                        System.out.print("\nEnter your new name: ");
                        String name = scanner.nextLine();
                        System.out.print("\nEnter your new address: ");
                        String address = scanner.nextLine();
                        System.out.print("\nEnter your new phone number: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("\nEnter your new date of birth (YYYY-MM-DD): ");
                        LocalDate dob = LocalDate.parse(scanner.nextLine());
                        UserController.editProfile(userId, name, address, phoneNumber, dob);
                    }
                }
                case 3 -> UserController.deleteProfile(userId);
                case 4 -> {
                    System.out.println("Returning to Main Menu.");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}