package NumberConverterSystem.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The {@code User} class represents a registered user within the Number
 * Converter System.
 * It encapsulates all user-related information, including authentication
 * credentials,
 * session conversion data, undo operation tracking, and historical conversion
 * records.
 * <p>
 * This class is designed to be serializable, enabling persistent storage and
 * retrieval
 * of user data, including conversion histories and session activities. Each
 * user is
 * uniquely identified by an integer ID and maintains both current session and
 * historical
 * conversion data for detailed tracking and analysis.
 * </p>
 *
 * <p>
 * <b>Features:</b>
 * <ul>
 * <li>Stores unique user identification, username, and password for
 * authentication.</li>
 * <li>Tracks all conversions performed in the current session.</li>
 * <li>Records the number of undo operations performed in the current
 * session.</li>
 * <li>Maintains a history of all conversions, grouped by date, for long-term
 * tracking.</li>
 * <li>Implements {@link Serializable} for persistent storage and
 * retrieval.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 * // Create a new user
 * User user = new User(1, "alice", "password123");
 *
 * // Add a conversion to the current session
 * user.currentSessionConversions.add(new Conversion("101", 2, 10));
 *
 * // Increment undo count
 * user.undoCountInCurrentSession++;
 *
 * // Save session conversions to history for today
 * user.conversionHistory.put(LocalDate.now(), new ArrayList<>(user.currentSessionConversions));
 * </pre>
 * </p>
 *
 * @author Guru Charan K S
 * @version 1.0
 * @since 2025-08-26
 */
public class User implements Serializable {
    /**
     * The serial version UID for serialization compatibility.
     * <p>
     * This value should be updated if the class structure changes in a way that
     * affects serialization.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique integer identifier for the user.
     * <p>
     * This ID is assigned at user creation and is used for efficient lookup and
     * authentication.
     * </p>
     * <b>Example:</b> 1, 42, 1001
     */
    public int id;

    /**
     * The username associated with the user account.
     * <p>
     * This string is used for user authentication and display purposes.
     * </p>
     * <b>Example:</b> "alice", "bob"
     */
    public String username;

    /**
     * The password associated with the user account.
     * <p>
     * This string is used for user authentication. It should be stored securely in
     * production systems.
     * </p>
     * <b>Example:</b> "password123"
     */
    public String password;

    /**
     * The list of {@link Conversion} objects representing all conversions performed
     * in the current session.
     * <p>
     * This list is cleared when session data is saved to history or when a new
     * session begins.
     * </p>
     */
    public ArrayList<Conversion> currentSessionConversions;

    /**
     * The number of undo operations performed by the user in the current session.
     * <p>
     * This integer is incremented each time the user undoes a conversion during the
     * session.
     * </p>
     * <b>Example:</b> 0, 2, 5
     */
    public int undoCountInCurrentSession;

    /**
     * The historical record of all conversions performed by the user, grouped by
     * date.
     * <p>
     * This map associates each {@link LocalDate} with a list of {@link Conversion}
     * objects
     * performed on that date, enabling detailed tracking and reporting of user
     * activity over time.
     * </p>
     * <b>Example:</b> {2025-08-26=[Conversion1, Conversion2],
     * 2025-08-27=[Conversion3]}
     */
    public HashMap<LocalDate, ArrayList<Conversion>> conversionHistory;

    /**
     * Constructs a new {@code User} object with the specified ID, username, and
     * password.
     * <p>
     * Initializes the {@link #currentSessionConversions} as an empty list,
     * {@link #conversionHistory}
     * as an empty map, and {@link #undoCountInCurrentSession} to zero.
     * </p>
     *
     * @param id       The unique integer identifier for the user.
     * @param username The username for authentication and display.
     * @param password The password for authentication.
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.currentSessionConversions = new ArrayList<>();
        this.conversionHistory = new HashMap<>();
        this.undoCountInCurrentSession = 0;
    }
}
