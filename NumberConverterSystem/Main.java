import java.util.Scanner;

import Controllers.UserController;
import Controllers.ConverterController;
import Controllers.StatsController;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

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

    public static void showFeatureMenu(int userId) {
        while (true) {
            System.out.print("\nEnter 1 for Converting a number from the source base to target base");
            System.out.print("\nEnter 2 for Conversion Stats of the current session");
            System.out.print("\nEnter 3 for Conversion Stats of the history");
            System.out.println("\nEnter 4 for Undo the last conversion in the current session");
            System.out.print("\n Enter 9 for Exiting the current session");

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
