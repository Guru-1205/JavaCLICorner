package Menus;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import Controllers.TransactionController;

public class TransactionMenu {
    public static Scanner scanner = new Scanner(System.in);

    public static void menu(UUID userId) {
        System.out.print("\nTransaction Management Menu:");
        while (true) {
            System.out.print("\n1. Add Transaction");
            System.out.print("\n2. View Transactions");
            System.out.print("\n3. Edit Transaction");
            System.out.print("\n4. Delete Transaction");
            System.out.print("\n5. Back to Main Menu");
            int userOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (userOption) {
                case 1 -> {
                    // title, amount,account,category,date,description
                    System.out.print("\nEnter transaction title: ");
                    String title = scanner.nextLine();
                    System.out.print("\nEnter transaction amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("\nEnter account name: ");
                    String accountName = scanner.nextLine();
                    System.out.print("\nEnter category name: ");
                    String categoryName = scanner.nextLine();
                    System.out.print("\nEnter transaction date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    LocalDate date;
                    try {
                        date = LocalDate.parse(dateInput);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        continue;
                    }
                    System.out.print("\nEnter transaction description: ");
                    String description = scanner.nextLine();
                    TransactionController.addTransaction(title, amount, accountName, categoryName, date, description);
                }
                case 2 -> TransactionController.viewTransactions();
                case 3 -> {
                    TransactionController.viewTransactions();
                    System.out.print("\nEnter transaction ID to edit: ");
                    String transactionId = scanner.nextLine();
                    System.out.print("\nEnter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("\nEnter new date (YYYY-MM-DD): ");
                    String newDateInput = scanner.nextLine();
                    LocalDate newDate;
                    try {
                        newDate = LocalDate.parse(newDateInput);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        continue;
                    }
                    System.out.print("\nEnter new description: ");
                    String newDescription = scanner.nextLine();
                    TransactionController.editTransaction(UUID.fromString(transactionId), newTitle, newDate,
                            newDescription);
                }
                case 4 -> {
                    // Assuming transactions are stored in a list, we can display them first
                    TransactionController.viewTransactions();
                    System.out.print("\nEnter transaction ID to delete: ");
                    String transactionId = scanner.nextLine();
                    if (TransactionController.deleteTransaction(UUID.fromString(transactionId))) {
                        System.out.println("Transaction deleted successfully.");
                    } else {
                        System.out.println("Failed to delete transaction. Please check the ID and try again.");
                    }
                }
            }
        }
    }
}
