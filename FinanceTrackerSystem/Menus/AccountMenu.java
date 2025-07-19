package Menus;

import java.util.Scanner;
import java.util.UUID;

import Controllers.AccountController;

public class AccountMenu {
    public static Scanner scanner = new Scanner(System.in);

    public static void menu(UUID userId) {
        System.out.print("\nAccount Management Menu:");
        while (true) {
            System.out.print("\n1. Add Account");
            System.out.print("\n2. View Accounts");
            System.out.print("\n3. Edit Account");
            System.out.print("\n4. Delete Account");
            System.out.print("\n5. Transfer Funds");
            System.out.print("\n6. Back to Main Menu");
            int userOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (userOption) {
                case 1 -> {
                    System.out.print("\nEnter account name: ");
                    String accountName = scanner.nextLine();
                    System.out.print("\nEnter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    AccountController.addAccount(accountName, initialBalance);
                }
                case 2 -> AccountController.viewAccounts();
                case 3 -> {
                    AccountController.viewAccounts();
                    System.out.print("\nEnter account name to edit: ");
                    String accountName = scanner.nextLine();
                    System.out.print("\nEnter new account name: ");
                    String newAccountName = scanner.nextLine();
                    System.out.print("\nEnter new balance: ");
                    double newBalance = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    AccountController.editAccount(accountName, newAccountName, newBalance);
                }
                case 4 -> {
                    AccountController.viewAccounts();
                    String accountName = scanner.nextLine();
                    if (AccountController.deleteAccount(accountName)) {
                        System.out.println("Account deleted successfully.");
                    } else {
                        System.out.println("Failed to delete account.");
                    }
                }
                case 5 -> {
                    AccountController.viewAccounts();
                    System.out.print("\nEnter source account name: ");
                    String sourceAccountName = scanner.nextLine();
                    System.out.print("\nEnter destination account name: ");
                    String destinationAccountName = scanner.nextLine();
                    System.out.print("\nEnter amount to transfer: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    if (AccountController.transferFunds(sourceAccountName, destinationAccountName, amount)) {
                        System.out.println("Funds transferred successfully.");
                    } else {
                        System.out.println("Failed to transfer funds.");
                    }
                }
                case 6 -> {
                    System.out.println("Returning to Main Menu.");
                    return;
                }
            }

        }
    }
}
