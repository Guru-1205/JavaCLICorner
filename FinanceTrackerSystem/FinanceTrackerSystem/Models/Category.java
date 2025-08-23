package FinanceTrackerSystem.Models;

import java.io.Serializable;
import java.util.UUID;

import FinanceTrackerSystem.Models.enums.CategoryType;

/**
 * Represents a category or subcategory for transactions and budgets in the
 * Finance Tracker System.
 * Categories help organize transactions and budgets by type (e.g., INCOME,
 * EXPENSE), and may be structured
 * hierarchically via parent-child relationships.
 *
 * <h2>Fields</h2>
 * <ul>
 * <li><b>categoryId</b>: Unique identifier for the category, generated upon
 * creation.</li>
 * <li><b>categoryType</b>: Type of the category, defined by
 * {@link Models.enums.CategoryType} (INCOME or EXPENSE).</li>
 * <li><b>categoryName</b>: Name of the category (e.g., "Groceries",
 * "Salary").</li>
 * <li><b>isSubCategory</b>: Indicates if this category is a subcategory (true)
 * or a main category (false).</li>
 * <li><b>parentCategory</b>: Reference to the parent category if this is a
 * subcategory; null otherwise.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * Category groceries = new Category(CategoryType.EXPENSE, "Groceries", false, null);
 * Category organic = new Category(CategoryType.EXPENSE, "Organic", true, groceries);
 * boolean isSub = organic.isSubCategory(); // true
 * String parentName = organic.getparentCategory().getCategoryName(); // "Groceries"
 * </pre>
 *
 * <h2>Relationships</h2>
 * <ul>
 * <li>Associated with {@link FinanceTrackerSystem.Models.User} via the user's
 * category list.</li>
 * <li>Referenced by {@link FinanceTrackerSystem.Models.Transaction} and
 * {@link FinanceTrackerSystem.Models.Budget} for
 * categorization.</li>
 * <li>Subcategories reference their parent category for hierarchical
 * organization.</li>
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
 * category data.
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
 * Additional fields (such as color, icon, or description) can be added as
 * needed for future requirements.
 * </p>
 *
 * @author Guru Charan
 * @version 1.0
 */
public class Category implements Serializable {
    /** Serialization version UID for compatibility. */
    private static final long serialVersionUID = 1L;

    /** Unique identifier for the category, generated upon creation. */
    private UUID categoryId;

    /** Type of the category (INCOME or EXPENSE). */
    private CategoryType categoryType;

    /** Name of the category. */
    private String categoryName;

    /** Indicates if this category is a subcategory. */
    private boolean isSubCategory;

    /**
     * Reference to the parent category if this is a subcategory; null otherwise.
     */
    private Category parentCategory;

    /**
     * Constructs a new Category with the specified details.
     * Generates a unique category ID.
     *
     * @param categoryType   Type of the category (INCOME or EXPENSE).
     * @param categoryName   Name of the category.
     * @param isSubCategory  True if this is a subcategory; false if main category.
     * @param parentCategory Reference to the parent category if subcategory; null
     *                       otherwise.
     */
    public Category(CategoryType categoryType, String categoryName, boolean isSubCategory,
            Category parentCategory) {
        this.categoryId = UUID.randomUUID();
        this.categoryType = categoryType;
        this.categoryName = categoryName;
        this.isSubCategory = isSubCategory;
        this.parentCategory = parentCategory;
    }

    /**
     * Returns the unique identifier for the category.
     *
     * @return UUID of the category.
     */
    public UUID getCategoryId() {
        return categoryId;
    }

    /**
     * Returns the type of the category (INCOME or EXPENSE).
     *
     * @return CategoryType of the category.
     */
    public CategoryType getCategoryType() {
        return categoryType;
    }

    /**
     * Sets the type of the category.
     *
     * @param categoryType New type for the category.
     */
    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * Returns the name of the category.
     *
     * @return Name of the category.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the name of the category.
     *
     * @param categoryName New name for the category.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Returns true if this category is a subcategory; false if main category.
     *
     * @return true if subcategory, false otherwise.
     */
    public boolean isSubCategory() {
        return isSubCategory;
    }

    /**
     * Sets whether this category is a subcategory.
     *
     * @param isSubCategory true if subcategory, false if main category.
     */
    public void setSubCategory(boolean isSubCategory) {
        this.isSubCategory = isSubCategory;
    }

    /**
     * Returns the parent category if this is a subcategory; null otherwise.
     *
     * @return Parent Category object or null.
     */
    public Category getparentCategory() {
        return parentCategory;
    }

    /**
     * Sets the parent category for this subcategory.
     *
     * @param parentCategory Parent Category object.
     */
    public void setparentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    /**
     * Returns a detailed string representation of the category, including all
     * fields.
     *
     * @return String representation of the category.
     */
    @Override
    public String toString() {
        return "Category ID: " + categoryId + "\n" +
                "Category Type: " + categoryType + "\n" +
                "Category Name: " + categoryName + "\n" +
                "Is Sub-Category: " + isSubCategory + "\n" +
                "Parent Category Name: " + (parentCategory == null ? "No parent category found"
                        : parentCategory.getCategoryName());
    }

}
