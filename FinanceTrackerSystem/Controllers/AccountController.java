package Controllers;

import java.util.List;
import java.util.UUID;

import Models.Account;
import static Controllers.UserController.currentUser;

/**
 * Provides static methods to manage user accounts in the Finance Tracker
 * System.
 * This controller interacts with the currently logged-in user
 * ({@code currentUser}) and
 * performs operations such as adding, viewing, editing, deleting accounts,
 * transferring funds,
 * and retrieving account IDs.
 *
 * <h2>Responsibilities</h2>
 * <ul>
 * <li>Create new accounts for users with specified names and initial
 * balances.</li>
 * <li>Display all accounts belonging to the current user.</li>
 * <li>Edit account details, including name and balance.</li>
 * <li>Delete accounts by name.</li>
 * <li>Transfer funds between accounts, ensuring sufficient balance and atomic
 * updates.</li>
 * <li>Retrieve account UUIDs by account name for cross-referencing in
 * transactions and budgets.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * AccountController.addAccount("Savings", 1000.0);
 * AccountController.viewAccounts();
 * AccountController.editAccount("Savings", "Emergency Fund", 1200.0);
 * AccountController.transferFunds("Savings", "Checking", 200.0);
 * UUID id = AccountController.getAccountIdByName("Checking");
 * </pre>
 *
 * <h2>Dependencies</h2>
 * <ul>
 * <li>{@link Models.Account} - The account model being managed.</li>
 * <li>{@link Models.User} - The user model, accessed via
 * {@code currentUser}.</li>
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
 * This class performs basic validation (e.g., account existence, sufficient
 * funds for transfers).
 * Further validation (such as name uniqueness or input format) should be
 * handled externally.
 * </p>
 *
 * <h2>Extensibility</h2>
 * <p>
 * Additional account operations (such as account type, currency, or audit
 * logging) can be added as needed.
 * </p>
 *
 * @author Finance Tracker System
 * @version 1.0
 */
public class AccountController {

    /**
     * Creates a new account for the current user with the specified name and
     * initial balance.
     * The account is added to the user's account list and persisted.
     *
     * @param accountName    Name of the new account.
     * @param initialBalance Initial balance for the account.
     * @return true if the account is added and user details are saved successfully;
     *         false otherwise.
     */
    public static boolean addAccount(String accountName, double initialBalance) {
        if (currentUser != null) {
            Account newAccount = new Account(accountName, initialBalance);
            currentUser.getAccounts().add(newAccount);
            if (UserController.saveUsersDetails()) {
                System.out.println("Account added successfully!");
                return true;
            } else {
                System.out.println("Failed to save user details after adding account.");
                return false;
            }
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Displays all accounts belonging to the current user.
     * Prints "No accounts found." if there are no accounts.
     * Each account is displayed using its {@link Account#toString()}
     * representation.
     */
    public static void viewAccounts() {
        if (currentUser != null) {
            if (currentUser.getAccounts().isEmpty()) {
                System.out.println("No accounts found.");
            } else {
                System.out.println("User Accounts:");
                for (Account account : currentUser.getAccounts()) {
                    System.out.println(account);
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Deletes an account with the specified name from the current user's account
     * list.
     * If the account is found and deleted, user details are persisted.
     *
     * @param accountName Name of the account to delete.
     * @return true if the account is deleted and user details are saved
     *         successfully; false otherwise.
     */
    public static boolean deleteAccount(String accountName) {
        if (currentUser != null) {
            List<Account> accounts = currentUser.getAccounts();
            for (Account account : accounts) {
                if (account.getAccountName().equals(accountName)) {
                    accounts.remove(account);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Account deleted successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after deleting account.");
                        return false;
                    }
                }
            }
            System.out.println("Account not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Edits the name and balance of an existing account.
     * If the account is found, updates its name and balance, then persists user
     * details.
     *
     * @param accountName    Name of the account to edit.
     * @param newAccountName New name for the account.
     * @param newBalance     New balance for the account.
     * @return true if the account is updated and user details are saved
     *         successfully; false otherwise.
     */
    public static boolean editAccount(String accountName, String newAccountName, double newBalance) {
        if (currentUser != null) {
            List<Account> accounts = currentUser.getAccounts();
            for (Account account : accounts) {
                if (account.getAccountName().equals(accountName)) {
                    account.setAccountName(newAccountName);
                    account.setBalance(newBalance);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Account updated successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after updating account.");
                        return false;
                    }
                }
            }
            System.out.println("Account not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Transfers funds from one account to another within the current user's
     * accounts.
     * Ensures both accounts exist and the source account has sufficient funds.
     * Updates balances atomically and persists user details.
     *
     * @param sourceAccountName      Name of the account to transfer funds from.
     * @param destinationAccountName Name of the account to transfer funds to.
     * @param amount                 Amount to transfer.
     * @return true if the transfer is successful and user details are saved; false
     *         otherwise.
     */
    public static boolean transferFunds(String sourceAccountName, String destinationAccountName, double amount) {
        if (currentUser != null) {
            Account sourceAccount = null;
            Account destinationAccount = null;

            for (Account account : currentUser.getAccounts()) {
                if (account.getAccountName().equals(sourceAccountName)) {
                    sourceAccount = account;
                } else if (account.getAccountName().equals(destinationAccountName)) {
                    destinationAccount = account;
                }
            }

            if (sourceAccount == null || destinationAccount == null) {
                System.out.println("Source or destination account not found.");
                return false;
            }

            if (sourceAccount.getBalance() < amount) {
                System.out.println("Insufficient funds in the source account.");
                return false;
            }

            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            destinationAccount.setBalance(destinationAccount.getBalance() + amount);

            if (UserController.saveUsersDetails()) {
                System.out.println("Funds transferred successfully!");
                return true;
            } else {
                System.out.println("Failed to save user details after transferring funds.");
                return false;
            }
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    /**
     * Retrieves the unique ID (UUID) of an account by its name.
     * Useful for cross-referencing accounts in transactions and budgets.
     *
     * @param accountName Name of the account.
     * @return UUID of the account if found; null otherwise.
     */
    public static UUID getAccountIdByName(String accountName) {
        if (currentUser != null) {
            for (Account account : currentUser.getAccounts()) {
                if (account.getAccountName().equals(accountName)) {
                    return account.getAccountId();
                }
            }
            System.out.println("Account not found.");
            return null;
        } else {
            System.out.println("User not found.");
            return null;
        }
    }

}
