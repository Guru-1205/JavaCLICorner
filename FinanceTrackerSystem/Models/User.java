package Models;

import java.util.List;
import java.util.Map;

import Models.enums.UserType;

public class User {

    private int id;
    private UserType type; // User type field added
    private UserProfile profile;
    private List<Account> accounts;
    private List<Category> categories;
    // Maps parent category name to list of subcategory names
    private Map<String, List<String>> categoryMap;
    private List<Transaction> transactions;
    private List<Budget> budgets;

    // Constructor
    public User(int id, UserType type, UserProfile profile, List<Account> accounts, List<Category> categories,
            Map<String, List<String>> categoryMap, List<Transaction> transactions, List<Budget> budgets) {
        this.id = id;
        this.type = type;
        this.profile = profile;
        this.accounts = accounts;
        this.categories = categories;
        this.categoryMap = categoryMap;
        this.transactions = transactions;
        this.budgets = budgets;
    }

    // Getters and Setters
    public int getId() {
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