package Controllers;

import Models.enums.CategoryType;
import Models.Category;
import static Controllers.UserController.currentUser;

import java.util.UUID;

/**
 * Provides static methods to manage categories and subcategories for the
 * currently logged-in user
 * in the Finance Tracker System. This controller supports adding, viewing,
 * editing, deleting categories
 * and subcategories, and retrieving category information by name or ID.
 *
 * <h2>Responsibilities</h2>
 * <ul>
 * <li>Add new main categories and subcategories for the current user.</li>
 * <li>Display all categories (main and subcategories) belonging to the current
 * user.</li>
 * <li>Edit category details, including name and type.</li>
 * <li>Delete categories, updating subcategory relationships as needed.</li>
 * <li>Retrieve category UUIDs and types by name or ID for cross-referencing in
 * transactions and budgets.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * CategoryController.addCategory(CategoryType.EXPENSE, "Groceries");
 * CategoryController.addSubCategory(CategoryType.EXPENSE, "Vegetables", "Groceries");
 * CategoryController.viewCategories();
 * CategoryController.editCategory("Groceries", "Food", CategoryType.EXPENSE);
 * CategoryController.deleteCategory("Food");
 * UUID id = CategoryController.getCategoryIdByName("Vegetables");
 * CategoryType type = CategoryController.getCategoryTypeById(id);
 * </pre>
 *
 * <h2>Dependencies</h2>
 * <ul>
 * <li>{@link Models.Category} - The category model being managed.</li>
 * <li>{@link Models.enums.CategoryType} - Enum for category type (INCOME,
 * EXPENSE).</li>
 * <li>{@link Controllers.UserController} - For accessing {@code currentUser}
 * and saving user details.</li>
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
 * This class performs basic validation (e.g., existence of category, parent
 * category, and user).
 * Further validation (such as name uniqueness or input format) should be
 * handled externally.
 * </p>
 *
 * <h2>Extensibility</h2>
 * <p>
 * Additional category operations (such as color, icon, or audit logging) can be
 * added as needed.
 * </p>
 *
 * @author Finance Tracker System
 * @version 1.0
 */
public class CategoryController {

    /**
     * Adds a new main category for the current user.
     * The category is added to the user's category list and persisted.
     *
     * @param categoryType The type of the category (e.g., INCOME, EXPENSE).
     * @param categoryName The name of the new category.
     * @return true if the category is added and user details are saved
     *         successfully; false otherwise.
     */
    public static boolean addCategory(CategoryType categoryType, String categoryName) {
        if (currentUser != null) {
            Category newCategory = new Category(categoryType, categoryName, false, null);
            currentUser.getCategories().add(newCategory);
            if (UserController.saveUsersDetails()) {
                System.out.println("Category added successfully!");
                return true;
            } else {
                System.out.println("Failed to save user details after adding category.");
                return false;
            }
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Adds a new subcategory under a specified parent category for the current
     * user.
     * The subcategory is linked to its parent and persisted.
     *
     * @param categoryType       The type of the subcategory.
     * @param categoryName       The name of the new subcategory.
     * @param parentCategoryName The name of the parent category.
     * @return true if the subcategory is added and user details are saved
     *         successfully; false otherwise.
     */
    public static boolean addSubCategory(CategoryType categoryType, String categoryName, String parentCategoryName) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(parentCategoryName)) {
                    Category newSubCategory = new Category(categoryType, categoryName, true, category);
                    currentUser.getCategories().add(newSubCategory);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Subcategory added successfully under " + parentCategoryName);
                        return true;
                    } else {
                        System.out.println("Failed to save user details after adding subcategory.");
                        return false;
                    }
                }
            }
            System.out.println("Parent category not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Displays all categories belonging to the current user.
     * Prints "No categories found." if there are no categories.
     * Each category is displayed using its {@link Category#toString()}
     * representation.
     */
    public static void viewCategories() {
        if (currentUser != null) {
            if (currentUser.getCategories().isEmpty()) {
                System.out.println("No categories found.");
            } else {
                System.out.println("User Categories:");
                for (Category category : currentUser.getCategories()) {
                    System.out.println(category);
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Edits the name and type of an existing category.
     * If the category is found, updates its name and type, then persists user
     * details.
     *
     * @param categoryName    The name of the category to edit.
     * @param newCategoryName The new name for the category.
     * @param newCategoryType The new type for the category.
     * @return true if the category is updated and user details are saved
     *         successfully; false otherwise.
     */
    public static boolean editCategory(String categoryName, String newCategoryName, CategoryType newCategoryType) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(categoryName)) {
                    category.setCategoryName(newCategoryName);
                    category.setCategoryType(newCategoryType);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Category edited successfully.");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after editing category.");
                        return false;
                    }
                }
            }
            System.out.println("Category not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Deletes a category with the specified name from the current user's category
     * list.
     * If the category is a main category, its subcategories will have their parent
     * set to null
     * and will be marked as main categories. The category is then removed and user
     * details persisted.
     *
     * @param categoryName The name of the category to delete.
     * @return true if the category is deleted and user details are saved
     *         successfully; false otherwise.
     */
    public static boolean deleteCategory(String categoryName) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(categoryName)) {
                    // If it's a main category, update subcategories
                    if (!category.isSubCategory()) {
                        for (Category subCategory : currentUser.getCategories()) {
                            if (subCategory.getparentCategory() != null
                                    && subCategory.getparentCategory().getCategoryName().equals(categoryName)) {
                                subCategory.setparentCategory(null);
                                subCategory.setSubCategory(false);
                            }
                        }
                    }
                    currentUser.getCategories().remove(category);

                    if (UserController.saveUsersDetails()) {
                        System.out.println("Category deleted successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after deleting category.");
                        return false;
                    }
                }
            }
            System.out.println("Category not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Retrieves the unique ID (UUID) of a category by its name.
     * Useful for cross-referencing categories in transactions and budgets.
     *
     * @param categoryName The name of the category.
     * @return UUID of the category if found; null otherwise.
     */
    public static UUID getCategoryIdByName(String categoryName) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(categoryName)) {
                    return category.getCategoryId();
                }
            }
            System.out.println("Category not found.");
            return null;
        } else {
            System.out.println("User not found.");
            return null;
        }
    }

    /**
     * Retrieves the type of a category by its name.
     * Useful for determining transaction or budget logic based on category type.
     *
     * @param categoryName The name of the category.
     * @return CategoryType of the category if found; null otherwise.
     */
    public static CategoryType getCategoryTypeByName(String categoryName) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(categoryName)) {
                    return category.getCategoryType();
                }
            }
            System.out.println("Category not found.");
            return null;
        } else {
            System.out.println("User not found.");
            return null;
        }
    }

    /**
     * Retrieves the type of a category by its unique ID.
     * Useful for determining transaction or budget logic based on category type.
     *
     * @param categoryId The UUID of the category.
     * @return CategoryType of the category if found; null otherwise.
     */
    public static CategoryType getCategoryTypeById(UUID categoryId) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryId().equals(categoryId)) {
                    return category.getCategoryType();
                }
            }
            System.out.println("Category not found.");
            return null;
        } else {
            System.out.println("User not found.");
            return null;
        }
    }
}