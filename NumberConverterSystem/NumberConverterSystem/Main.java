package NumberConverterSystem;

import java.util.Scanner;

import NumberConverterSystem.Controllers.UserController;
import NumberConverterSystem.Controllers.ConverterController;
import NumberConverterSystem.Controllers.FileController;
import NumberConverterSystem.Controllers.QuizController;
import NumberConverterSystem.Controllers.StatsController;

/**
 * The {@code Main} class serves as the entry point for the Number Converter
 * System application.
 * It provides the main user interface for interacting with the system via the
 * command line,
 * including user authentication, registration, number base conversion,
 * statistics, batch processing,
 * exporting, and quiz features.
 * <p>
 * This class orchestrates the flow of the application, presenting menus and
 * handling user input
 * to invoke the appropriate controller methods. It manages session state, user
 * authentication,
 * and feature navigation, ensuring a seamless user experience.
 * </p>
 *
 * <p>
 * <b>Features:</b>
 * <ul>
 * <li>Interactive main menu for login, registration, and exit.</li>
 * <li>User authentication and session management.</li>
 * <li>Feature menu for conversion, statistics, undo, batch processing,
 * exporting, and quizzes.</li>
 * <li>Graceful handling of invalid input and error conditions.</li>
 * <li>Integration with all major controllers for business logic.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Usage Example:</b>
 * 
 * <pre>
 *   // Start the application
 *   java NumberConverterSystem.Main
 *
 *   // User navigates menus, performs conversions, views stats, exports data, and takes quizzes.
 * </pre>
 * </p>
 *
 * <p>
 * <b>Menu Flow:</b>
 * <ol>
 * <li>Main Menu: Login, Register, Exit</li>
 * <li>Login/Register: Authenticate or create user, then enter Feature Menu</li>
 * <li>Feature Menu:
 * <ul>
 * <li>Convert numbers between bases</li>
 * <li>View current session statistics</li>
 * <li>View historical statistics</li>
 * <li>Undo last conversion</li>
 * <li>Batch process conversions from file</li>
 * <li>Export session conversions</li>
 * <li>Export historical conversions</li>
 * <li>Take a conversion quiz</li>
 * <li>Exit session (save and logout)</li>
 * </ul>
 * </li>
 * </ol>
 * </p>
 *
 * <p>
 * <b>Error Handling:</b>
 * <ul>
 * <li>Invalid menu options prompt the user to retry.</li>
 * <li>Failed user creation or login displays informative messages.</li>
 * <li>All controller operations are wrapped with feedback for success or
 * failure.</li>
 * </ul>
 * </p>
 *
 * @author Guru Charan K S
 * @version 1.0
 * @since 2025-08-26
 */
public class Main {
    /**
     * The shared {@link Scanner} instance for reading user input from the command
     * line.
     * <p>
     * Used throughout the application for all interactive prompts.
     * </p>
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * The main entry point for the Number Converter System application.
     * <p>
     * Invokes the main menu and begins the interactive session.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        showMainMenu();
    }

    /**
     * Displays the main menu and handles user navigation for login, registration,
     * or exit.
     * <p>
     * Presents options to the user and processes their selection. On successful
     * login or registration,
     * transitions to the feature menu for further operations.
     * </p>
     * <p>
     * <b>Menu Options:</b>
     * <ul>
     * <li>1: Login</li>
     * <li>2: Register</li>
     * <li>3: Exit</li>
     * </ul>
     * </p>
     * <p>
     * Invalid selections prompt the user to retry.
     * </p>
     */
    public static void showMainMenu() {
        System.out.print("\nWelcome to Number Converter System!!!");
        while (true) {
            System.out.print("\nEnter 1 for Login");
            System.out.print("\nEnter 2 for Register");
            System.out.println("\nEnter 3 for Exit");

            int userOption = Integer.parseInt(scanner.nextLine());
            switch (userOption) {
                case 1 -> showLoginMenu();
                case 2 -> showRegisterMenu();
                case 3 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid Option Selected");
            }
        }
    }

    /**
     * Handles user registration by prompting for username and password, creating a
     * new user,
     * and transitioning to the feature menu upon success.
     * <p>
     * If registration fails due to invalid input, an error message is displayed and
     * the user
     * is returned to the main menu.
     * </p>
     */
    public static void showRegisterMenu() {
        System.out.print("\nEnter the Username: ");
        String username = scanner.nextLine();

        System.out.print("\nEnter the Password: ");
        String password = scanner.nextLine();

        int userId = UserController.createUser(username, password);
        if (userId == -1) {
            System.out.println("Creation of user failed due to invalid inputs!");
            return;
        }

        showFeatureMenu(userId);
    }

    /**
     * Handles user login by prompting for username and password, verifying
     * credentials,
     * and transitioning to the feature menu upon success.
     * <p>
     * If login fails due to invalid credentials, an error message is displayed and
     * the user
     * is returned to the main menu.
     * </p>
     */
    public static void showLoginMenu() {
        System.out.print("\nEnter the Username: ");
        String username = scanner.nextLine();

        System.out.print("\nEnter the Password: ");
        String password = scanner.nextLine();

        int userId = UserController.verifyUser(username, password);
        if (userId == -1) {
            System.out.println("Login failed due to invalid credentials!");
            return;
        }

        showFeatureMenu(userId);
    }

    /**
     * Displays the feature menu for a logged-in user and handles all feature
     * operations.
     * <p>
     * Presents options for conversion, statistics, undo, batch processing,
     * exporting, and quizzes.
     * Processes user selections and invokes the corresponding controller methods.
     * <p>
     * <b>Feature Menu Options:</b>
     * <ul>
     * <li>1: Convert a number from source base to target base</li>
     * <li>2: View current session conversion statistics</li>
     * <li>3: View historical conversion statistics</li>
     * <li>4: Undo the last conversion in the current session</li>
     * <li>5: Batch process conversions from a file</li>
     * <li>6: Export current session conversions to a file</li>
     * <li>7: Export historical conversions to a file</li>
     * <li>8: Take a quiz on conversions</li>
     * <li>9: Exit the current session (save and logout)</li>
     * </ul>
     * </p>
     * <p>
     * Invalid selections are ignored. Exiting the session triggers saving of
     * session conversions
     * and displays session statistics before logout.
     * </p>
     *
     * @param userId The unique identifier of the logged-in user.
     */
    public static void showFeatureMenu(int userId) {
        while (true) {
            System.out.print("\nEnter 1 for Converting a number from the source base to target base");
            System.out.print("\nEnter 2 for Conversion Stats of the current session");
            System.out.print("\nEnter 3 for Conversion Stats of the history");
            System.out.print("\nEnter 4 for Undo the last conversion in the current session");
            System.out.print("\nEnter 5 for Processing a batch of conversions ( passed through file )");
            System.out.print("\nEnter 6 for Exporting the current session conversions");
            System.out.print("\nEnter 7 for Exporting the history of conversions");
            System.out.print("\nEnter 8 for Quiz on Conversions");
            System.out.print("\nEnter 9 for Exiting the current session");

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> {
                    System.out.print("\nEnter the Value for Conversion: ");
                    String inputValue = scanner.nextLine();
                    System.out.print("\nEnter the Source Base: ");
                    int sourceBase = scanner.nextInt();
                    System.out.print("\nEnter the Target Base: ");
                    int targetBase = scanner.nextInt();
                    scanner.nextLine();
                    String result = ConverterController.convertNumber(userId, inputValue, sourceBase, targetBase);
                    System.out.println(result);
                }
                case 2 -> {
                    System.out.println("Printing the Current Session Stats :");
                    StatsController.printCurrentSessionStats(userId);
                }
                case 3 -> {
                    System.out.println("Printing the History Stats :");
                    StatsController.printHistoryStats(userId);
                }
                case 4 -> {
                    if (ConverterController.undoLastConversion(userId)) {
                        System.out.println("Undo Operation has been successfully completed");
                    } else {
                        System.out.println("Undo Operation cannot be performed");
                    }
                }
                case 5 -> {
                    String filePath = scanner.nextLine();
                    FileController.batchProcessingThroughFile(userId, filePath);
                }

                case 6 -> {
                    if (FileController.exportCurrentSessionConversions(userId)) {
                        System.out.print("\nExporting to the File has been completed Successfully");
                    } else {
                        System.out.println("\nExporting Failed!!!");
                    }
                }

                case 7 -> {
                    if (FileController.exportHistoryConversionsByDate(userId)) {
                        System.out.print("\nExporting to the File has been completed Successfully");
                    } else {
                        System.out.println("\nExporting Failed!!!");
                    }
                }

                case 8 -> {
                    QuizController.startQuiz();
                }
                case 9 -> {
                    System.out.println("Printing the Current session conversions before logging out");
                    StatsController.printCurrentSessionStats(userId);
                    UserController.saveCurrentSessionConversions(userId);
                    System.out.println("Saved all the current session conversions");
                    System.out.println("Exiting the session");
                    return;
                }
            }
        }
    }
}
