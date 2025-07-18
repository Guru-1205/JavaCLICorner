package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Models.enums.UserType;

public class User {
    private static final long serialVersionUID = 1L; // For serialization compatibility
    private UUID id;
    private UserType type; // User type field added
    private UserProfile profile;
    private List<Account> accounts;
    private List<Category> categories;
    // Maps parent category name to list of subcategory names
    private Map<String, List<String>> categoryMap;
    private List<Transaction> transactions;
    private List<Budget> budgets;

    // Constructor
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

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Map<String, List<String>> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(Map<String, List<String>> categoryMap) {
        this.categoryMap = categoryMap;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    // Method to display user information
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