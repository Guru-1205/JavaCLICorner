package Controllers;

import java.time.LocalDate;
import java.util.UUID;

import Models.Account;
import Models.Transaction;
import Models.enums.CategoryType;

import static Controllers.UserController.currentUser;

public class TransactionController {

    public static boolean addTransaction(String title, double amount, String accountName, String categoryName,
            LocalDate date, String description) {
        if (UserController.currentUser != null) {
            UUID categoryId = CategoryController.getCategoryIdByName(categoryName);
            UUID accountId = AccountController.getAccountIdByName(accountName);
            Transaction newTransaction = new Transaction(categoryId, accountId, title, date, description, amount);
            currentUser.getTransactions().add(newTransaction);
            CategoryType categoryType = CategoryController.getCategoryTypeByName(categoryName);
            for (Account account : currentUser.getAccounts()) {
                if (account.getAccountId().equals(accountId)) {
                    if (categoryType == CategoryType.EXPENSE) {
                        account.setBalance(account.getBalance() - amount);
                    } else if (categoryType == CategoryType.INCOME) {
                        account.setBalance(account.getBalance() + amount);
                    }
                }
            }
            if (UserController.saveUsersDetails()) {
                System.out.println("Transaction added successfully!");
                return true;
            } else {
                System.out.println("Failed to save user details after adding transaction.");
                return false;
            }
        } else {
            System.out.println("User not found.");
            return false;
        }

    }

    public static void viewTransactions() {
        if (currentUser != null) {
            if (currentUser.getTransactions().isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                System.out.println("User Transactions:");
                for (Transaction transaction : currentUser.getTransactions()) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public static boolean editTransaction(UUID transactionId, String title, LocalDate date, String description) {
        if (currentUser != null) {
            for (Transaction transaction : currentUser.getTransactions()) {
                if (transaction.getTransactionId().equals(transactionId)) {
                    transaction.setTransactionName(title);
                    transaction.setTransactionDate(date);
                    transaction.setDescription(description);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Transaction updated successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after updating transaction.");
                        return false;
                    }
                }
            }
            System.out.println("Transaction not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    public static boolean deleteTransaction(UUID transactionId) {
        if (currentUser != null) {
            for (Transaction transaction : currentUser.getTransactions()) {
                if (transaction.getTransactionId().equals(transactionId)) {
                    currentUser.getTransactions().remove(transaction);
                    for (Account account : currentUser.getAccounts()) {
                        if (account.getAccountId().equals(transaction.getAccountId())) {
                            CategoryType categoryType = CategoryController
                                    .getCategoryTypeById(transaction.getCategoryId());
                            if (categoryType == CategoryType.EXPENSE) {
                                account.setBalance(account.getBalance() + transaction.getAmount());
                            } else if (categoryType == CategoryType.INCOME) {
                                account.setBalance(account.getBalance() - transaction.getAmount());
                            }
                        }
                    }
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Transaction deleted successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after deleting transaction.");
                        return false;
                    }
                }
            }
            System.out.println("Transaction not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }

    }
}