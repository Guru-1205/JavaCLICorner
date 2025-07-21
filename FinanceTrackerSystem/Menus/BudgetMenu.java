package Menus;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import Controllers.BudgetController;
import Controllers.CategoryController;

public class BudgetMenu {
    public static Scanner scanner = new Scanner(System.in);

    public static void menu(UUID userId) {
        System.out.print("\nBudget Management Menu:");
        while (true) {
            System.out.print("\n1. Create Budget");
            System.out.print("\n2. View Budgets");
            System.out.print("\n3. Edit Budget");
            System.out.print("\n4. Delete Budget");
            System.out.print("\n5. Back to Main Menu");
            int userOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (userOption) {
                case 1 -> {
                    System.out.print("\nEnter budget name: ");
                    String budgetName = scanner.nextLine();
                    System.out.print("\nEnter budget amount: ");
                    double budgetAmount = scanner.nextDouble();
                    System.out.print("\nEnter Category Name:");
                    String categoryName = scanner.nextLine();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("\nEnter start date (YYYY-MM-DD): ");
                    String startDateInput = scanner.nextLine();
                    System.out.print("\nEnter end date (YYYY-MM-DD): ");
                    String endDateInput = scanner.nextLine();
                    System.out.print("\nEnter description: ");
                    String description = scanner.nextLine();
                    LocalDate startDate, endDate;
                    try {
                        startDate = LocalDate.parse(startDateInput);
                        endDate = LocalDate.parse(endDateInput);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        continue;
                    }

                    BudgetController.createBudget(categoryName, budgetName, budgetAmount, startDate, endDate,
                            description);
                }
                case 2 -> BudgetController.viewBudgets();
                case 3 -> {
                    BudgetController.viewBudgets();
                    System.out.print("\nEnter category name: ");
                    String budgetName = scanner.nextLine();
                    System.out.print("\nEnter budget name to edit: ");
                    String newBudgetName = scanner.nextLine();
                    System.out.print("\nEnter new budget amount: ");
                    double newBudgetAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("\nEnter new start date (YYYY-MM-DD): ");
                    String newStartDateInput = scanner.nextLine();
                    System.out.print("\nEnter new end date (YYYY-MM-DD): ");
                    String newEndDateInput = scanner.nextLine();
                    System.out.print("\nEnter new description: ");
                    String description = scanner.nextLine();
                    LocalDate newStartDate, newEndDate;
                    try {
                        newStartDate = LocalDate.parse(newStartDateInput);
                        newEndDate = LocalDate.parse(newEndDateInput);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        continue;
                    }
                    BudgetController.editBudget(budgetName, newBudgetName, newBudgetAmount, newStartDate, newEndDate,
                            description);
                }
                case 4 -> {
                    BudgetController.viewBudgets();
                    System.out.print("\nEnter budget name to delete: ");
                    String budgetName = scanner.nextLine();
                    BudgetController.deleteBudget(budgetName);
                }
                case 5 -> {
                    System.out.println("Returning to main menu...");
                    return;
                }
            }
        }
    }
}
