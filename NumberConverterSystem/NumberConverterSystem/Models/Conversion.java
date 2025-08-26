package NumberConverterSystem.Models;

import java.io.Serializable;

/**
 * The {@code Conversion} class represents a single number base conversion
 * operation performed by a user
 * within the Number Converter System. It encapsulates all relevant data for a
 * conversion, including the
 * input value, source and target bases, the conversion result, and any error
 * messages encountered during
 * the process.
 * <p>
 * This class is designed to be serializable, allowing conversion records to be
 * persisted to disk as part
 * of user session and history management. Each instance stores both the raw
 * input and the outcome of the
 * conversion, supporting detailed tracking and reporting of user activity.
 * </p>
 *
 * <p>
 * <b>Features:</b>
 * <ul>
 * <li>Stores the input value, source base, and target base for the
 * conversion.</li>
 * <li>Records the result of the conversion, or an error message if the
 * conversion failed.</li>
 * <li>Provides a formatted string representation for logging, display, or
 * export.</li>
 * <li>Implements {@link Serializable} for persistent storage and
 * retrieval.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 * // Create a new conversion record
 * Conversion c = new Conversion("101", 2, 10);
 * c.result = "5";
 *
 * // Print conversion details
 * System.out.println(c.toString());
 * </pre>
 * </p>
 *
 * @author Guru Charan K S
 * @version 1.0
 * @since 2025-08-26
 */
public class Conversion implements Serializable {
    /**
     * The serial version UID for serialization compatibility.
     * <p>
     * This value should be updated if the class structure changes in a way that
     * affects serialization.
     * </p>
     */
    private static final long serialVersionUID = 2L;

    /**
     * The string representation of the value to be converted.
     * <p>
     * This is the raw input provided by the user, in the format appropriate for the
     * {@link #sourceBase}.
     * </p>
     * <b>Example:</b> "101" (binary), "A3" (hexadecimal)
     */
    public String inputValue;

    /**
     * The result of the conversion operation.
     * <p>
     * This field is set after a successful conversion. If the conversion fails,
     * this field remains empty.
     * </p>
     * <b>Example:</b> "5" (decimal), "101" (binary)
     */
    public String result;

    /**
     * The error message generated during the conversion, if any.
     * <p>
     * If the conversion fails due to invalid input, unsupported base, or other
     * errors, this field contains
     * a descriptive error message. If the conversion succeeds, this field is empty.
     * </p>
     * <b>Example:</b> "Invalid input for base 2"
     */
    public String errorMessage;

    /**
     * The base in which the {@link #inputValue} is represented.
     * <p>
     * This integer specifies the source base for the conversion (e.g., 2 for
     * binary, 10 for decimal).
     * </p>
     */
    public int sourceBase;

    /**
     * The base to which the {@link #inputValue} should be converted.
     * <p>
     * This integer specifies the target base for the conversion (e.g., 10 for
     * decimal, 16 for hexadecimal).
     * </p>
     */
    public int targetBase;

    /**
     * Constructs a new {@code Conversion} object with the specified input value,
     * source base, and target base.
     * <p>
     * Initializes the {@link #errorMessage} and {@link #result} fields to empty
     * strings, indicating that
     * the conversion has not yet been performed or no error has occurred.
     * </p>
     *
     * @param inputValue The string representation of the value to be converted.
     * @param sourceBase The base in which the input value is represented.
     * @param targetBase The base to which the value should be converted.
     */
    public Conversion(String inputValue, int sourceBase, int targetBase) {
        this.inputValue = inputValue;
        this.sourceBase = sourceBase;
        this.targetBase = targetBase;
        this.errorMessage = "";
        this.result = "";
    }

    /**
     * Returns a formatted string representation of the conversion record.
     * <p>
     * The output includes the input value, source base, target base, result (or "No
     * Result" if empty),
     * and error message (or "No Error Message" if empty). This method is useful for
     * logging, display,
     * or exporting conversion data.
     * </p>
     *
     * <p>
     * <b>Example Output:</b>
     * 
     * <pre>
     * Value : 101 - Source Base : 2 - Target Base : 10 - Result : 5 - Error Message - No Error Message
     * </pre>
     * </p>
     *
     * @return A formatted string summarizing the conversion details.
     */
    @Override
    public String toString() {
        return String.format("Value : %s - Source Base : %d - Target Base : %d - Result : %s - Error Message - %s\n",
                inputValue, sourceBase, targetBase, (result.isEmpty() ? "No Result" : result),
                (errorMessage.isEmpty() ? "No Error Message" : errorMessage));
    }
}
