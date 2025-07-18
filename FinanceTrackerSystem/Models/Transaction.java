package Models;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private static final long serialVersionUID = 1L; // For serialization compatibility
    private UUID transactionId, categoryId, accountId;
    private String TransactionName;
    private LocalDate transactionDate;
    private String description;
    private double amount;

    // Parameterized constructor
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

    // Getter for transactionId
    public UUID getTransactionId() {
        return transactionId;
    }

    // Getter for categoryId
    public UUID getCategoryId() {
        return categoryId;
    }

    // Getter for accountId
    public UUID getAccountId() {
        return accountId;
    }

    // Getter for transactionName
    public String getTransactionName() {
        return TransactionName;
    }

    // Setter for transactionName
    public void setTransactionName(String transactionName) {
        this.TransactionName = transactionName;
    }

    // Getter for transactionDate
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    // Setter for transactionDate
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for amount
    public double getAmount() {
        return amount;
    }

    // Setter for amount
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Method to display transaction information
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
