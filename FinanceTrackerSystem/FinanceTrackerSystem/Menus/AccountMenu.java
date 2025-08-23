package FinanceTrackerSystem.Menus;

import java.util.Scanner;
import java.util.UUID;

import FinanceTrackerSystem.Controllers.AccountController;

/**
 * The AccountMenu class provides a console-based menu for managing user
 * accounts
 * in the Finance Tracker System. It allows users to add, view, edit, delete
 * accounts,
 * and transfer funds between accounts.
 *
 * <p>
 * Typical Usage:
 * Call {@link #menu(UUID)} to display the account management menu for a
 * specific user.
 * </p>
 *
 * <p>
 * Dependencies:
 * <ul>
 * <li>Controllers.AccountController</li>
 * <li>java.util.Scanner</li>
 * <li>java.util.UUID</li>
 * </ul>
 * </p>
 */
public class AccountMenu {
    /** Scanner for reading user input from the console. */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Displays the account management menu for the specified user.
     * Provides options to add, view, edit, delete accounts, transfer funds, or
     * return to the main menu.
     *
     * @param userId UUID of the user for whom the menu is displayed.
     */
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
                    // Add Account
                    System.out.print("\nEnter account name: ");
                    String accountName = scanner.nextLine();
                    System.out.print("\nEnter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    AccountController.addAccount(accountName, initialBalance);
                }
                case 2 -> {
                    // View Accounts
                    AccountController.viewAccounts();
                }
                case 3 -> {
                    // Edit Account
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
                    // Delete Account
                    AccountController.viewAccounts();
                    String accountName = scanner.nextLine();
                    if (AccountController.deleteAccount(accountName)) {
                        System.out.println("Account deleted successfully.");
                    } else {
                        System.out.println("Failed to delete account.");
                    }
                }
                case 5 -> {
                    // Transfer Funds
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
                    // Back to Main Menu
                    System.out.println("Returning to Main Menu.");
                    return;
                }
            }

        }
    }
}
