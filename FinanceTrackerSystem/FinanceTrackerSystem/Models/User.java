package FinanceTrackerSystem.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import FinanceTrackerSystem.Models.enums.UserType;

/**
 * Represents a user in the Finance Tracker System.
 * Each user has a unique identifier, a user type, a personal profile, and
 * collections
 * for accounts, categories, subcategories, transactions, and budgets.
 * <p>
 * This class is central to the system, encapsulating all financial and personal
 * data
 * for a single user. It supports serialization for persistent storage.
 * </p>
 *
 * <h2>Fields</h2>
 * <ul>
 * <li><b>id</b>: Unique identifier for the user, generated upon creation.</li>
 * <li><b>type</b>: The user's role in the system (e.g., ADMIN, REGULAR).</li>
 * <li><b>profile</b>: Personal details of the user, such as name, address,
 * phone, and date of birth.</li>
 * <li><b>accounts</b>: List of {@link Account} objects representing the user's
 * financial accounts.</li>
 * <li><b>categories</b>: List of {@link Category} objects representing the
 * user's categories and subcategories.</li>
 * <li><b>categoryMap</b>: Map from parent category names to lists of
 * subcategory names for fast lookup and organization.</li>
 * <li><b>transactions</b>: List of {@link Transaction} objects representing all
 * financial transactions performed by the user.</li>
 * <li><b>budgets</b>: List of {@link Budget} objects representing the user's
 * budgets for various categories and periods.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 *   UserProfile profile = new UserProfile("Alice", "123 Main St", "555-1234", LocalDate.of(1990, 1, 1));
 *   User user = new User(profile, UserType.REGULAR);
 *   user.getAccounts().add(new Account("Savings", 1000.0));
 *   user.getCategories().add(new Category(CategoryType.EXPENSE, "Groceries", false, null));
 *   user.getBudgets().add(new Budget(...));
 *   user.getTransactions().add(new Transaction(...));
 * </pre>
 *
 * <h2>Relationships</h2>
 * <ul>
 * <li>Contained in system-wide user lists for authentication and data
 * management.</li>
 * <li>References {@link UserProfile}, {@link Account}, {@link Category},
 * {@link Transaction}, and {@link Budget}.</li>
 * <li>Category map provides a hierarchical structure for categories and
 * subcategories.</li>
 * </ul>
 *
 * <h2>Thread Safety</h2>
 * <p>
 * This class is <b>not thread-safe</b>. External synchronization is required if
 * instances are accessed by multiple threads.
 * </p>
 *
 * <h2>Serialization</h2>
 * <p>
 * Implements {@link Serializable} for persistent storage and retrieval of user
 * data.
 * </p>
 *
 * @author Guru Charan
 * @version 1.0
 */
public class User implements Serializable {
    /** Serialization version UID for compatibility. */
    private static final long serialVersionUID = 1L;

    /** Unique identifier for the user, generated upon creation. */
    private UUID id;

    /** The user's role in the system (e.g., ADMIN, REGULAR). */
    private UserType type;

    /** Personal profile details of the user. */
    private UserProfile profile;

    /** List of financial accounts owned by the user. */
    private List<Account> accounts;

    /** List of categories and subcategories created by the user. */
    private List<Category> categories;

    /**
     * Map from parent category names to lists of subcategory names.
     * Used for fast lookup and hierarchical organization of categories.
     * Example: {"Groceries": ["Vegetables", "Fruits"], "Transport": ["Bus",
     * "Taxi"]}
     */
    private Map<String, List<String>> categoryMap;

    /** List of all financial transactions performed by the user. */
    private List<Transaction> transactions;

    /** List of budgets set by the user for various categories and periods. */
    private List<Budget> budgets;

    /**
     * Constructs a new User with the specified profile and user type.
     * Initializes all collections to empty lists or maps.
     *
     * @param userProfile The personal profile details of the user.
     * @param type        The user's role in the system.
     */
    public User(UserProfile userProfile, UserType type) {
        this.id = UUID.randomUUID(); // Generate a unique ID for the user
        this.type = type;
        this.profile = userProfile;
        this.accounts = new ArrayList<>(); // Initialize accounts list
        this.categories = new ArrayList<>(); // Initialize categories list
        this.categoryMap = new HashMap<>(); // Initialize category map
        this.transactions = new ArrayList<>(); // Initialize transactions list
        this.budgets = new ArrayList<>(); // Initialize budgets list
    }

    /**
     * Returns the unique identifier for the user.
     *
     * @return UUID of the user.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the user's type (role).
     *
     * @return UserType of the user.
     */
    public UserType getType() {
        return type;
    }

    /**
     * Sets the user's type (role).
     *
     * @param type New user type.
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * Returns the user's personal profile details.
     *
     * @return UserProfile object.
     */
    public UserProfile getProfile() {
        return profile;
    }

    /**
     * Sets the user's personal profile details.
     *
     * @param profile New UserProfile object.
     */
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    /**
     * Returns the list of financial accounts owned by the user.
     *
     * @return List of Account objects.
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Sets the list of financial accounts owned by the user.
     *
     * @param accounts New list of Account objects.
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Returns the list of categories and subcategories created by the user.
     *
     * @return List of Category objects.
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the list of categories and subcategories created by the user.
     *
     * @param categories New list of Category objects.
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Returns the category map, which maps parent category names to lists of
     * subcategory names.
     *
     * @return Map from parent category names to subcategory name lists.
     */
    public Map<String, List<String>> getCategoryMap() {
        return categoryMap;
    }

    /**
     * Sets the category map, which maps parent category names to lists of
     * subcategory names.
     *
     * @param categoryMap New category map.
     */
    public void setCategoryMap(Map<String, List<String>> categoryMap) {
        this.categoryMap = categoryMap;
    }

    /**
     * Returns the list of all financial transactions performed by the user.
     *
     * @return List of Transaction objects.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Sets the list of all financial transactions performed by the user.
     *
     * @param transactions New list of Transaction objects.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Returns the list of budgets set by the user.
     *
     * @return List of Budget objects.
     */
    public List<Budget> getBudgets() {
        return budgets;
    }

    /**
     * Sets the list of budgets set by the user.
     *
     * @param budgets New list of Budget objects.
     */
    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    /**
     * Returns a detailed string representation of the user, including all fields
     * and collections.
     *
     * @return String representation of the user and their data.
     */
    @Override
    public String toString() {
        return "User ID: " + id + "\n" +
                "User Type: " + type + "\n" +
                "Profile: " + profile + "\n" +
                "Accounts: " + accounts + "\n" +
                "Categories: " + categories + "\n" +
                "Category Map: " + categoryMap + "\n" +
                "Transactions: " + transactions + "\n" +
                "Budgets: " + budgets;
    }
}