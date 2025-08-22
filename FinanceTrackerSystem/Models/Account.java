package Models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a financial account belonging to a user in the Finance Tracker
 * System.
 * Each account has a unique identifier, a name, and a balance. Accounts are
 * used to
 * track funds and associate transactions, and are essential for managing
 * personal finance.
 *
 * <h2>Fields</h2>
 * <ul>
 * <li><b>accountId</b>: Unique identifier for the account, generated upon
 * creation using {@link UUID}.</li>
 * <li><b>accountName</b>: Name of the account (e.g., "Savings", "Checking").
 * Used for display and selection.</li>
 * <li><b>balance</b>: Current balance of the account. Updated by transactions
 * and transfers.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * Account savings = new Account("Savings", 1000.0);
 * savings.setBalance(1200.0);
 * String name = savings.getAccountName();
 * UUID id = savings.getAccountId();
 * </pre>
 *
 * <h2>Relationships</h2>
 * <ul>
 * <li>Associated with {@link Models.User} via the user's account list.</li>
 * <li>Referenced by {@link Models.Transaction} for transaction
 * source/destination.</li>
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
 * account data.
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
 * Additional fields (such as account type, currency, or status) can be added as
 * needed for future requirements.
 * </p>
 *
 * @author Finance Tracker System
 * @version 1.0
 */
public class Account implements Serializable {
    /** Serialization version UID for compatibility. */
    private static final long serialVersionUID = 1L;

    /** Name of the account (e.g., "Savings", "Checking"). */
    private String accountName;

    /** Current balance of the account. */
    private double balance;

    /** Unique identifier for the account, generated upon creation. */
    private UUID accountId;

    /**
     * Constructs a new Account with the specified name and initial balance.
     * Generates a unique UUID for the account.
     *
     * @param accountName Name of the account.
     * @param balance     Initial balance of the account.
     */
    public Account(String accountName, double balance) {
        this.accountId = UUID.randomUUID();
        this.accountName = accountName;
        this.balance = balance;
    }

    /**
     * Returns the unique identifier of the account.
     *
     * @return UUID of the account.
     */
    public UUID getAccountId() {
        return accountId;
    }

    /**
     * Returns the name of the account.
     *
     * @return Account name.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the name of the account.
     *
     * @param accountName New account name.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return Account balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account.
     *
     * @param balance New balance value.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns a string representation of the account, including its name and
     * balance.
     *
     * @return String representation of the account.
     */
    @Override
    public String toString() {
        return "Account Name: " + accountName + "\n" +
                "Balance: " + balance;
    }
}
