package Controllers;

import java.time.LocalDate;
import java.util.UUID;

import Models.Budget;
import Models.Transaction;

import static Controllers.UserController.currentUser;

/**
 * Provides static methods to manage budgets for the currently logged-in user in
 * the Finance Tracker System.
 * This controller supports creating, viewing, editing, deleting budgets, and
 * calculating budget progress
 * based on user transactions. All operations are performed on the budgets
 * associated with {@code currentUser}.
 *
 * <h2>Responsibilities</h2>
 * <ul>
 * <li>Create new budgets for specific categories and time periods.</li>
 * <li>Display all budgets belonging to the current user.</li>
 * <li>Edit budget details, including name, amount, date range, and
 * description.</li>
 * <li>Delete budgets by name.</li>
 * <li>Calculate and update budget progress by summing relevant transaction
 * amounts.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * BudgetController.createBudget("Groceries", "August Groceries", 500.0, LocalDate.of(2025, 8, 1),
 *         LocalDate.of(2025, 8, 31), "Monthly groceries");
 * BudgetController.viewBudgets();
 * BudgetController.editBudget("August Groceries", "September Groceries", 600.0, LocalDate.of(2025, 9, 1),
 *         LocalDate.of(2025, 9, 30), "Monthly groceries");
 * BudgetController.deleteBudget("September Groceries");
 * BudgetController.calculateBudgetProgressByTransactions();
 * </pre>
 *
 * <h2>Dependencies</h2>
 * <ul>
 * <li>{@link Models.Budget} - The budget model being managed.</li>
 * <li>{@link Models.Transaction} - Used for calculating budget progress.</li>
 * <li>{@link Controllers.UserController} - For accessing {@code currentUser}
 * and saving user details.</li>
 * <li>{@link Controllers.CategoryController} - For category lookup by
 * name.</li>
 * </ul>
 *
 * <h2>Thread Safety</h2>
 * <p>
 * This class is <b>not thread-safe</b>. All operations assume single-threaded
 * access to {@code currentUser}.
 * If used in a multi-threaded environment, external synchronization is
 * required.
 * </p>
 *
 * <h2>Validation</h2>
 * <p>
 * This class performs basic validation (e.g., existence of category, budget,
 * and user).
 * Further validation (such as date range correctness or duplicate budget names)
 * should be handled externally.
 * </p>
 *
 * <h2>Extensibility</h2>
 * <p>
 * Additional budget operations (such as alerts, status, or reporting) can be
 * added as needed.
 * </p>
 *
 * @author Finance Tracker System
 * @version 1.0
 */
public class BudgetController {

    /**
     * Creates a new budget for the current user under the specified category.
     * The budget is added to the user's budget list and persisted.
     *
     * @param categoryName Name of the category for the budget.
     * @param budgetName   Name of the new budget.
     * @param budgetAmount Amount allocated for the budget.
     * @param startDate    Start date of the budget period.
     * @param endDate      End date of the budget period.
     * @param description  Description of the budget.
     * @return true if the budget is created and user details are saved
     *         successfully; false otherwise.
     */
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

    /**
     * Displays all budgets belonging to the current user.
     * Prints "No budgets found." if there are no budgets.
     * Each budget is displayed using its {@link Budget#toString()} representation.
     */
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

    /**
     * Deletes a budget with the specified name from the current user's budget list.
     * If the budget is found and deleted, user details are persisted.
     *
     * @param budgetName Name of the budget to delete.
     * @return true if the budget is deleted and user details are saved
     *         successfully; false otherwise.
     */
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

    /**
     * Edits the details of an existing budget.
     * If the budget is found, updates its name, amount, date range, and
     * description, then persists user details.
     *
     * @param budgetName      Name of the budget to edit.
     * @param newBudgetName   New name for the budget.
     * @param newBudgetAmount New amount for the budget.
     * @param newStartDate    New start date for the budget.
     * @param newEndDate      New end date for the budget.
     * @param newDescription  New description for the budget.
     * @return true if the budget is updated and user details are saved
     *         successfully; false otherwise.
     */
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

    /**
     * Calculates the progress of each budget by summing the amounts of transactions
     * that match the budget's category and fall within the budget's date range.
     * Updates each budget's progress amount accordingly.
     * <p>
     * This method iterates through all budgets of the current user, and for each
     * budget,
     * it sums the amounts of all transactions that:
     * <ul>
     * <li>Have a category ID matching the budget's category ID.</li>
     * <li>Have a transaction date after the budget's start date and before the
     * budget's end date.</li>
     * </ul>
     * The resulting sum is set as the budget's progress amount.
     * </p>
     */
    public static void calculateBudgetProgressByTransactions() {
        if (currentUser != null) {
            for (Budget budget : currentUser.getBudgets()) {
                double totalSpent = 0;
                for (Transaction transaction : currentUser.getTransactions()) {
                    if (transaction.getCategoryId().equals(budget.getCategoryId()) &&
                            transaction.getTransactionDate().isAfter(budget.getStartDate()) &&
                            transaction.getTransactionDate().isBefore(budget.getEndDate())) {
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
