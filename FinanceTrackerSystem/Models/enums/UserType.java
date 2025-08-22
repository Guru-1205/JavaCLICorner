package Models.enums;

/**
 * Enum representing the type of a user in the Finance Tracker System.
 * Used to distinguish between different user roles.
 *
 * <p>
 * Typical Usage:
 * 
 * <pre>
 * UserType type = UserType.ADMIN;
 * </pre>
 * </p>
 *
 * <p>
 * Relationships:
 * <ul>
 * <li>Used in {@link Models.User} to specify user type.</li>
 * <li>May be used for access control and feature restrictions.</li>
 * </ul>
 * </p>
 */
public enum UserType {
    /** Administrator user with elevated privileges. */
    ADMIN,
    /** Regular user with standard privileges. */
    REGULAR
}
