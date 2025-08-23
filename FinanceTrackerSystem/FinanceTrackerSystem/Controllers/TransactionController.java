package FinanceTrackerSystem.Controllers;

import static FinanceTrackerSystem.Controllers.UserController.currentUser;

import java.time.LocalDate;
import java.util.UUID;

import FinanceTrackerSystem.Models.Account;
import FinanceTrackerSystem.Models.Transaction;
import FinanceTrackerSystem.Models.enums.CategoryType;

/**
 * Provides static methods to manage transactions for the currently logged-in
 * user
 * in the Finance Tracker System. This controller supports adding, viewing,
 * editing,
 * and deleting transactions, and ensures that account balances and budget
 * progress
 * are updated accordingly.
 *
 * <h2>Responsibilities</h2>
 * <ul>
 * <li>Add new transactions for the current user, updating account balances and
 * budget progress.</li>
 * <li>Display all transactions belonging to the current user.</li>
 * <li>Edit transaction details, including title, date, and description.</li>
 * <li>Delete transactions by ID, updating account balances and budget
 * progress.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * TransactionController.addTransaction("Salary", 5000.0, "Savings", "Income", LocalDate.now(), "Monthly salary");
 * TransactionController.viewTransactions();
 * TransactionController.editTransaction(transactionId, "Updated Title", LocalDate.now(), "Updated description");
 * TransactionController.deleteTransaction(transactionId);
 * </pre>
 *
 * <h2>Dependencies</h2>
 * <ul>
 * <li>{@link FinanceTrackerSystem.Models.Transaction} - The transaction model
 * being managed.</li>
 * <li>{@link FinanceTrackerSystem.Models.Account} - Used for updating account
 * balances.</li>
 * <li>{@link Models.enums.CategoryType} - Enum for transaction type (INCOME,
 * EXPENSE).</li>
 * <li>{@link FinanceTrackerSystem.Controllers.UserController} - For accessing
 * {@code currentUser}
 * and saving user details.</li>
 * <li>{@link FinanceTrackerSystem.Controllers.CategoryController} - For
 * category lookup and type
 * determination.</li>
 * <li>{@link Controllers.AccountController} - For account lookup by name.</li>
 * <li>{@link FinanceTrackerSystem.Controllers.BudgetController} - For updating
 * budget progress after
 * transaction changes.</li>
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
 * This class performs basic validation (e.g., existence of user, account,
 * category, and transaction).
 * Further validation (such as input format or duplicate transactions) should be
 * handled externally.
 * </p>
 *
 * <h2>Extensibility</h2>
 * <p>
 * Additional transaction operations (such as tags, receipt images, or audit
 * logging) can be added as needed.
 * </p>
 *
 * @author Guru Charan
 * @version 1.0
 */
public class TransactionController {

    /**
     * Adds a new transaction for the current user, updates the relevant account
     * balance,
     * and recalculates budget progress.
     * <p>
     * The transaction is created and added to the user's transaction list. The
     * account balance
     * is updated based on the transaction type (income increases, expense
     * decreases).
     * Budget progress is recalculated to reflect the new transaction.
     * </p>
     *
     * @param title        Title of the transaction.
     * @param amount       Amount of the transaction.
     * @param accountName  Name of the account associated with the transaction.
     * @param categoryName Name of the category associated with the transaction.
     * @param date         Date of the transaction.
     * @param description  Description of the transaction.
     * @return true if the transaction is added and user details are saved
     *         successfully; false otherwise.
     */
    public static boolean addTransaction(String title, double amount, String accountName, String categoryName,
            LocalDate date, String description) {
        if (UserController.currentUser != null) {
            UUID categoryId = CategoryController.getCategoryIdByName(categoryName);
            UUID accountId = AccountController.getAccountIdByName(accountName);
            Transaction newTransaction = new Transaction(categoryId, accountId, title, date, description, amount);
            currentUser.getTransactions().add(newTransaction);
            CategoryType categoryType = CategoryController.getCategoryTypeByName(categoryName);
            for (Account account : currentUser.getAccounts()) {
                if (account.getAccountId().equals(accountId)) {
                    if (categoryType == CategoryType.EXPENSE) {
                        account.setBalance(account.getBalance() - amount);
                    } else if (categoryType == CategoryType.INCOME) {
                        account.setBalance(account.getBalance() + amount);
                    }
                }
            }
            BudgetController.calculateBudgetProgressByTransactions();
            if (UserController.saveUsersDetails()) {
                System.out.println("Transaction added successfully!");
                return true;
            } else {
                System.out.println("Failed to save user details after adding transaction.");
                return false;
            }
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Displays all transactions belonging to the current user.
     * Prints "No transactions found." if there are no transactions.
     * Each transaction is displayed using its {@link Transaction#toString()}
     * representation.
     */
    public static void viewTransactions() {
        if (currentUser != null) {
            if (currentUser.getTransactions().isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                System.out.println("User Transactions:");
                for (Transaction transaction : currentUser.getTransactions()) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Edits the title, date, and description of an existing transaction.
     * If the transaction is found, updates its fields and persists user details.
     *
     * @param transactionId UUID of the transaction to edit.
     * @param title         New title for the transaction.
     * @param date          New date for the transaction.
     * @param description   New description for the transaction.
     * @return true if the transaction is updated and user details are saved
     *         successfully; false otherwise.
     */
    public static boolean editTransaction(UUID transactionId, String title, LocalDate date, String description) {
        if (currentUser != null) {
            for (Transaction transaction : currentUser.getTransactions()) {
                if (transaction.getTransactionId().equals(transactionId)) {
                    transaction.setTransactionName(title);
                    transaction.setTransactionDate(date);
                    transaction.setDescription(description);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Transaction updated successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after updating transaction.");
                        return false;
                    }
                }
            }
            System.out.println("Transaction not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Deletes a transaction with the specified ID from the current user's
     * transaction list,
     * and updates the relevant account balance accordingly.
     * <p>
     * If the transaction is found and deleted, the account balance is reverted
     * based on the transaction type.
     * For expenses, the amount is added back; for income, the amount is subtracted.
     * </p>
     *
     * @param transactionId UUID of the transaction to delete.
     * @return true if the transaction is deleted and user details are saved
     *         successfully; false otherwise.
     */
    public static boolean deleteTransaction(UUID transactionId) {
        if (currentUser != null) {
            for (Transaction transaction : currentUser.getTransactions()) {
                if (transaction.getTransactionId().equals(transactionId)) {
                    currentUser.getTransactions().remove(transaction);
                    for (Account account : currentUser.getAccounts()) {
                        if (account.getAccountId().equals(transaction.getAccountId())) {
                            CategoryType categoryType = CategoryController
                                    .getCategoryTypeById(transaction.getCategoryId());
                            if (categoryType == CategoryType.EXPENSE) {
                                account.setBalance(account.getBalance() + transaction.getAmount());
                            } else if (categoryType == CategoryType.INCOME) {
                                account.setBalance(account.getBalance() - transaction.getAmount());
                            }
                        }
                    }
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Transaction deleted successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after deleting transaction.");
                        return false;
                    }
                }
            }
            System.out.println("Transaction not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }

    }
}