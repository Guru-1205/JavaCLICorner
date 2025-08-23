package FinanceTrackerSystem.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a financial transaction in the Finance Tracker System.
 * Transactions record income or expense events, and are linked to accounts and
 * categories.
 * Each transaction has a unique identifier, a category, an account, a name, a
 * date,
 * a description, and an amount.
 *
 * <h2>Fields</h2>
 * <ul>
 * <li><b>transactionId</b>: Unique identifier for the transaction, generated
 * upon creation.</li>
 * <li><b>categoryId</b>: UUID of the category associated with this transaction
 * (e.g., "Groceries", "Salary").</li>
 * <li><b>accountId</b>: UUID of the account associated with this transaction
 * (e.g., "Savings", "Checking").</li>
 * <li><b>TransactionName</b>: Title or name of the transaction (e.g.,
 * "Supermarket Purchase", "Monthly Salary").</li>
 * <li><b>transactionDate</b>: Date when the transaction occurred.</li>
 * <li><b>description</b>: Additional details or notes about the
 * transaction.</li>
 * <li><b>amount</b>: Amount of the transaction (positive for income, negative
 * for expense).</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 *   UUID groceriesCategoryId = ...;
 *   UUID savingsAccountId = ...;
 *   Transaction t = new Transaction(
 *       groceriesCategoryId,
 *       savingsAccountId,
 *       "Supermarket",
 *       LocalDate.of(2025, 8, 22),
 *       "Weekly grocery shopping",
 *       -75.50
 *   );
 *   double amt = t.getAmount();
 *   t.setDescription("Updated description");
 * </pre>
 *
 * <h2>Relationships</h2>
 * <ul>
 * <li>Associated with {@link FinanceTrackerSystem.Models.User} via the user's
 * transaction list.</li>
 * <li>Linked to {@link FinanceTrackerSystem.Models.Account} and
 * {@link FinanceTrackerSystem.Models.Category} by their
 * UUIDs.</li>
 * <li>May be used to update account balances and budget progress.</li>
 * </ul>
 *
 * <h2>Thread Safety</h2>
 * <p>
 * This class is <b>not thread-safe</b>. If instances are shared between
 * threads, external synchronization is required.
 * </p>
 *
 * <h2>Serialization</h2>
 * <p>
 * Implements {@link Serializable} for persistent storage and retrieval of
 * transaction data.
 * The {@code serialVersionUID} field ensures compatibility across versions.
 * </p>
 *
 * <h2>Validation</h2>
 * <p>
 * This class does not perform validation on field values. Validation should be
 * handled externally
 * (e.g., in controllers or input forms).
 * </p>
 *
 * <h2>Extensibility</h2>
 * <p>
 * Additional fields (such as tags, receipt images, or payment method) can be
 * added as needed for future requirements.
 * </p>
 *
 * @author Guru Charan
 * @version 1.0
 */
public class Transaction implements Serializable {
    /** Serialization version UID for compatibility. */
    private static final long serialVersionUID = 1L;

    /** Unique identifier for the transaction, generated upon creation. */
    private UUID transactionId;

    /** UUID of the category associated with this transaction. */
    private UUID categoryId;

    /** UUID of the account associated with this transaction. */
    private UUID accountId;

    /** Title or name of the transaction. */
    private String TransactionName;

    /** Date when the transaction occurred. */
    private LocalDate transactionDate;

    /** Additional details or notes about the transaction. */
    private String description;

    /** Amount of the transaction (positive for income, negative for expense). */
    private double amount;

    /**
     * Constructs a new Transaction with the specified details.
     * Generates a unique transaction ID.
     *
     * @param categoryId      UUID of the category associated with this transaction.
     * @param accountId       UUID of the account associated with this transaction.
     * @param transactionName Title or name of the transaction.
     * @param transactionDate Date when the transaction occurred.
     * @param description     Additional details or notes about the transaction.
     * @param amount          Amount of the transaction (positive for income,
     *                        negative for expense).
     */
    public Transaction(UUID categoryId, UUID accountId, String transactionName, LocalDate transactionDate,
            String description, double amount) {
        this.transactionId = UUID.randomUUID(); // Generate a unique ID for the transaction
        this.categoryId = categoryId;
        this.accountId = accountId;
        this.TransactionName = transactionName;
        this.transactionDate = transactionDate;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Returns the unique identifier for the transaction.
     *
     * @return UUID of the transaction.
     */
    public UUID getTransactionId() {
        return transactionId;
    }

    /**
     * Returns the UUID of the category associated with this transaction.
     *
     * @return UUID of the category.
     */
    public UUID getCategoryId() {
        return categoryId;
    }

    /**
     * Returns the UUID of the account associated with this transaction.
     *
     * @return UUID of the account.
     */
    public UUID getAccountId() {
        return accountId;
    }

    /**
     * Returns the title or name of the transaction.
     *
     * @return Transaction name.
     */
    public String getTransactionName() {
        return TransactionName;
    }

    /**
     * Sets the title or name of the transaction.
     *
     * @param transactionName New transaction name.
     */
    public void setTransactionName(String transactionName) {
        this.TransactionName = transactionName;
    }

    /**
     * Returns the date when the transaction occurred.
     *
     * @return Transaction date.
     */
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the date when the transaction occurred.
     *
     * @param transactionDate New transaction date.
     */
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Returns additional details or notes about the transaction.
     *
     * @return Transaction description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets additional details or notes about the transaction.
     *
     * @param description New transaction description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the amount of the transaction (positive for income, negative for
     * expense).
     *
     * @return Transaction amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount New transaction amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns a detailed string representation of the transaction, including all
     * fields.
     *
     * @return String representation of the transaction.
     */
    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + "\n" +
                "Category ID: " + categoryId + "\n" +
                "Account ID: " + accountId + "\n" +
                "Transaction Name: " + TransactionName + "\n" +
                "Transaction Date: " + transactionDate + "\n" +
                "Description: " + description + "\n" +
                "Amount: " + amount;
    }

}
