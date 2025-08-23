package FinanceTrackerSystem.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a budget set by a user for a specific category and time period in
 * the Finance Tracker System.
 * Budgets help users track and limit their spending or saving goals, and
 * monitor progress based on associated transactions.
 *
 * <h2>Fields</h2>
 * <ul>
 * <li><b>budgetId</b>: Unique identifier for the budget, generated upon
 * creation.</li>
 * <li><b>categoryId</b>: UUID of the category this budget is associated with
 * (e.g., "Groceries", "Transport").</li>
 * <li><b>budgetName</b>: Name of the budget (e.g., "Monthly Groceries",
 * "Vacation Savings").</li>
 * <li><b>budgetAmount</b>: Total amount allocated for the budget.</li>
 * <li><b>progressAmount</b>: Amount spent or saved so far, updated by
 * transactions.</li>
 * <li><b>description</b>: Description or notes about the budget.</li>
 * <li><b>startDate</b>: Start date of the budget period.</li>
 * <li><b>endDate</b>: End date of the budget period.</li>
 * <li><b>transactions</b>: List of {@link Transaction} objects associated with
 * this budget.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 *   Budget groceriesBudget = new Budget(
 *       groceriesCategoryId,
 *       "Groceries",
 *       500.0,
 *       LocalDate.of(2025, 8, 1),
 *       LocalDate.of(2025, 8, 31),
 *       "Monthly groceries budget"
 *   );
 *   groceriesBudget.setProgressAmount(250.0);
 *   groceriesBudget.getTransactions().add(new Transaction(...));
 * </pre>
 *
 * <h2>Relationships</h2>
 * <ul>
 * <li>Associated with {@link FinanceTrackerSystem.Models.User} via the user's
 * budget list.</li>
 * <li>Linked to a category via {@code categoryId}.</li>
 * <li>Progress is updated based on {@link Transaction} objects.</li>
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
 * budget data.
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
 * Additional fields (such as budget status, alerts, or tags) can be added as
 * needed for future requirements.
 * </p>
 *
 * @author Guru Charan
 * @version 1.0
 */
public class Budget implements Serializable {
    /** Serialization version UID for compatibility. */
    private static final long serialVersionUID = 1L;

    /** Unique identifier for the budget, generated upon creation. */
    private UUID budgetId;

    /** UUID of the category this budget is associated with. */
    private UUID categoryId;

    /** Name of the budget. */
    private String budgetName;

    /** Total amount allocated for the budget. */
    private double budgetAmount;

    /** Amount spent or saved so far, updated by transactions. */
    private double progressAmount;

    /** Description or notes about the budget. */
    private String description;

    /** Start date of the budget period. */
    private LocalDate startDate;

    /** End date of the budget period. */
    private LocalDate endDate;

    /** List of transactions associated with this budget. */
    private List<Transaction> transactions;

    /**
     * Constructs a new Budget with the specified details.
     * Generates a unique budget ID and initializes the transactions list.
     *
     * @param categoryId   UUID of the category this budget is associated with.
     * @param budgetName   Name of the budget.
     * @param budgetAmount Total amount allocated for the budget.
     * @param startDate    Start date of the budget period.
     * @param endDate      End date of the budget period.
     * @param description  Description or notes about the budget.
     */
    public Budget(UUID categoryId, String budgetName, double budgetAmount, LocalDate startDate, LocalDate endDate,
            String description) {
        this.budgetId = UUID.randomUUID(); // Generate a unique ID for the budget
        this.categoryId = categoryId;
        this.budgetName = budgetName;
        this.budgetAmount = budgetAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.progressAmount = 0.0; // Initialize progress amount to zero
        this.transactions = new ArrayList<>(); // Initialize the transactions list
    }

    /**
     * Returns the unique identifier for the budget.
     *
     * @return UUID of the budget.
     */
    public UUID getBudgetId() {
        return budgetId;
    }

    /**
     * Returns the UUID of the category this budget is associated with.
     *
     * @return UUID of the category.
     */
    public UUID getCategoryId() {
        return categoryId;
    }

    /**
     * Returns the name of the budget.
     *
     * @return Budget name.
     */
    public String getBudgetName() {
        return budgetName;
    }

    /**
     * Sets the name of the budget.
     *
     * @param budgetName New budget name.
     */
    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    /**
     * Returns the total amount allocated for the budget.
     *
     * @return Budget amount.
     */
    public double getBudgetAmount() {
        return budgetAmount;
    }

    /**
     * Sets the total amount allocated for the budget.
     *
     * @param budgetAmount New budget amount.
     */
    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    /**
     * Returns the amount spent or saved so far, updated by transactions.
     *
     * @return Progress amount.
     */
    public double getProgressAmount() {
        return progressAmount;
    }

    /**
     * Sets the amount spent or saved so far.
     *
     * @param progressAmount New progress amount.
     */
    public void setProgressAmount(double progressAmount) {
        this.progressAmount = progressAmount;
    }

    /**
     * Returns the description or notes about the budget.
     *
     * @return Budget description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description or notes about the budget.
     *
     * @param description New budget description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the start date of the budget period.
     *
     * @return Start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the budget period.
     *
     * @param startDate New start date.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the budget period.
     *
     * @return End date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the budget period.
     *
     * @param endDate New end date.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the list of transactions associated with this budget.
     *
     * @return List of Transaction objects.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Returns a detailed string representation of the budget, including all fields
     * except transactions.
     *
     * @return String representation of the budget.
     */
    @Override
    public String toString() {
        return "Budget ID: " + budgetId + "\n" +
                "Category ID: " + categoryId + "\n" +
                "Budget Name: " + budgetName + "\n" +
                "Budget Amount: " + budgetAmount + "\n" +
                "Progress Amount: " + progressAmount + "\n" +
                "Description: " + description + "\n" +
                "Start Date: " + startDate + "\n" +
                "End Date: " + endDate;
    }

}
