package FinanceTrackerSystem.Models.enums;

/**
 * Enum representing the type of a category in the Finance Tracker System.
 * Used to distinguish between income and expense categories.
 *
 * <p>
 * Typical Usage:
 * 
 * <pre>
 * CategoryType type = CategoryType.INCOME;
 * </pre>
 * </p>
 *
 * <p>
 * Relationships:
 * <ul>
 * <li>Used in {@link FinanceTrackerSystem.Models.Category} to specify category
 * type.</li>
 * <li>Referenced in transaction and budget logic for type-based
 * operations.</li>
 * </ul>
 * </p>
 */
public enum CategoryType {
    /** Category for income transactions (e.g., salary, interest). */
    INCOME,
    /** Category for expense transactions (e.g., groceries, rent). */
    EXPENSE
}
