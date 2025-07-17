package Models;

import java.util.UUID;

public class Account {
    private String accountName;
    private double balance;
    private UUID accountId;

    // Parameterized constructor
    public Account(String accountName, double balance) {
        this.accountId = UUID.randomUUID(); // Generate a unique ID for the account
        this.accountName = accountName;
        this.balance = balance;
    }

    // Getter for accountId
    public UUID getAccountId() {
        return accountId;
    }

    // Getter for accountName
    public String getAccountName() {
        return accountName;
    }

    // Setter for accountName
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Setter for balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Method to display account information
    @Override
    public String toString() {
        return "Account Name: " + accountName + "\n" +
                "Balance: " + balance;
    }
}
