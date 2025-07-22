package Controllers;

import java.time.LocalDate;
import java.util.UUID;

import Models.Budget;
import Models.Transaction;

import static Controllers.UserController.currentUser;

public class BudgetController {
    public static boolean createBudget(String categoryName, String budgetName, double budgetAmount, LocalDate startDate,
            LocalDate endDate, String description) {
        UUID categoryId = CategoryController.getCategoryIdByName(categoryName);
        if (categoryId == null) {
            System.out.println("Category not found.");
            return false;
        }
        Budget newBudget = new Budget(categoryId, budgetName, budgetAmount, startDate, endDate, description);
        currentUser.getBudgets().add(newBudget);
        if (UserController.saveUsersDetails()) {
            System.out.println("Budget created successfully!");
            return true;
        } else {
            System.out.println("Failed to save user details after creating budget.");
            return false;
        }
    }

    public static void viewBudgets() {
        if (currentUser != null) {
            if (currentUser.getBudgets().isEmpty()) {
                System.out.println("No budgets found.");
            } else {
                System.out.println("User Budgets:");
                for (Budget budget : currentUser.getBudgets()) {
                    System.out.println(budget);
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public static boolean deleteBudget(String budgetName) {
        if (currentUser != null) {
            for (Budget budget : currentUser.getBudgets()) {
                if (budget.getBudgetName().equals(budgetName)) {
                    currentUser.getBudgets().remove(budget);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Budget deleted successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after deleting budget.");
                        return false;
                    }
                }
            }
            System.out.println("Budget not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    public static boolean editBudget(String budgetName, String newBudgetName, double newBudgetAmount,
            LocalDate newStartDate, LocalDate newEndDate, String newDescription) {
        if (currentUser != null) {
            for (Budget budget : currentUser.getBudgets()) {
                if (budget.getBudgetName().equals(budgetName)) {
                    budget.setBudgetName(newBudgetName);
                    budget.setBudgetAmount(newBudgetAmount);
                    budget.setStartDate(newStartDate);
                    budget.setEndDate(newEndDate);
                    budget.setDescription(newDescription);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Budget edited successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after editing budget.");
                        return false;
                    }
                }
            }
            System.out.println("Budget not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    public static void calculateBudgetProgressByTransactions() {
        if (currentUser != null) {
            for (Budget budget : currentUser.getBudgets()) {
                double totalSpent = 0;
                for (Transaction transaction : currentUser.getTransactions()) {
                    if (transaction.getCategoryId().equals(budget.getCategoryId()) && transaction.getTransactionDate()
                            .isAfter(budget.getStartDate())
                            && transaction.getTransactionDate().isBefore(budget.getEndDate())) {
                        totalSpent += transaction.getAmount();
                    }
                }
                budget.setProgressAmount(totalSpent);
            }
        } else {
            System.out.println("User not found.");
        }
    }

}
