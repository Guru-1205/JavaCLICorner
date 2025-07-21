package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Budget implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization compatibility
    private UUID budgetId, categoryId;
    private String budgetName;
    private double budgetAmount;
    private double progressAmount;
    private String description;
    private LocalDate startDate, endDate;
    private List<Transaction> transactions;

    // Parameterized constructor
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

    // Getter for budgetId
    public UUID getBudgetId() {
        return budgetId;
    }

    // Getter for categoryId
    public UUID getCategoryId() {
        return categoryId;
    }

    // Getter for budgetName
    public String getBudgetName() {
        return budgetName;
    }

    // Setter for budgetName
    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    // Getter for budgetAmount
    public double getBudgetAmount() {
        return budgetAmount;
    }

    // Setter for budgetAmount
    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    // Getter for progressAmount
    public double getProgressAmount() {
        return progressAmount;
    }

    // Setter for progressAmount
    public void setProgressAmount(double progressAmount) {
        this.progressAmount = progressAmount;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for startDate
    public LocalDate getStartDate() {
        return startDate;
    }

    // Setter for startDate
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    // Getter for endDate
    public LocalDate getEndDate() {
        return endDate;
    }

    // Setter for endDate
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // Getter for transactions
    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Method to display budget information
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
