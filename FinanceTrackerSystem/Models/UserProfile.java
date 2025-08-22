package Models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents the personal profile details of a user in the Finance Tracker
 * System.
 * This class encapsulates identity and contact information, and is used for
 * authentication,
 * personalization, and reporting throughout the system.
 *
 * <h2>Fields</h2>
 * <ul>
 * <li><b>name</b>: The full name of the user. Used for identification and
 * display.</li>
 * <li><b>address</b>: The user's residential or mailing address. May be used
 * for correspondence or verification.</li>
 * <li><b>phoneNumber</b>: The user's contact phone number. Used for
 * communication and notifications.</li>
 * <li><b>dob</b>: The user's date of birth. Used for age verification,
 * personalization, and reporting.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * UserProfile profile = new UserProfile("Alice Smith", "123 Main St", "555-1234", LocalDate.of(1990, 1, 1));
 * profile.setAddress("456 Elm Ave");
 * String name = profile.getName();
 * LocalDate birthDate = profile.getDob();
 * </pre>
 *
 * <h2>Relationships</h2>
 * <ul>
 * <li>Contained in {@link Models.User} as the user's personal details.</li>
 * <li>May be used for authentication, reporting, and display in menus and
 * summaries.</li>
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
 * Implements {@link Serializable} to allow persistent storage and retrieval of
 * user profile data.
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
 * Additional fields (such as email, gender, or preferences) can be added as
 * needed for future requirements.
 * </p>
 *
 * @author Finance Tracker System
 * @version 1.0
 */
public class UserProfile implements Serializable {
    /** Serialization version UID for compatibility across versions. */
    private static final long serialVersionUID = 1L;

    /** The full name of the user. */
    private String name;

    /** The user's residential or mailing address. */
    private String address;

    /** The user's contact phone number. */
    private String phoneNumber;

    /** The user's date of birth. */
    private LocalDate dob;

    /**
     * Constructs a new UserProfile with the specified details.
     *
     * @param name        The full name of the user.
     * @param address     The user's address.
     * @param phoneNumber The user's phone number.
     * @param dob         The user's date of birth.
     */
    public UserProfile(String name, String address, String phoneNumber, LocalDate dob) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }

    /**
     * Returns the full name of the user.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the user.
     *
     * @param name The new name for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's address.
     *
     * @return The user's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the user's address.
     *
     * @param address The new address for the user.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the user's phone number.
     *
     * @return The user's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phoneNumber The new phone number for the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the user's date of birth.
     *
     * @return The user's date of birth.
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Sets the user's date of birth.
     *
     * @param dob The new date of birth for the user.
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Returns a string representation of the user profile, including all fields.
     *
     * @return String representation of the profile.
     */
    @Override
    public String toString() {
        return "UserProfile{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob=" + dob +
                '}';
    }
}
