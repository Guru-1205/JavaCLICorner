package authenticationUsingFileClass;

import authenticationUsingFileClass.enums.UserType;

import java.io.IOException;
import java.util.Scanner;

public class Auth1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline left-over
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter valid username:");
                    String name = sc.nextLine();
                    System.out.println("Enter password:");
                    String password = sc.nextLine();
                    System.out.println("Enter 1 for doctor login");
                    System.out.println("Enter 2 for patient login");
                    int userTypeChoice = sc.nextInt();
                    sc.nextLine(); // Consume the newline left-over
                    UserType type = UserAuthentication.getUserType(userTypeChoice);
                    if (type == null) {
                        System.out.println("Invalid usertype choice.");
                        continue;
                    }
                    if (UserAuthentication.login(name, password, type)) {
                        System.out.println("Logged in successfully!!!");
                        // place the statements which need to be executed after logging in
                        return;
                    } else {
                        System.out.println("Incorrect username/password ... please try again");
                    }
                }
                case 2 -> {
                    System.out.println("Enter username for registration:");
                    String name = sc.nextLine();
                    System.out.println("Enter password:");
                    String password = sc.nextLine();
                    System.out.println("Enter 1 for doctor registration");
                    System.out.println("Enter 2 for patient registration");
                    int userTypeChoice = sc.nextInt();
                    sc.nextLine(); // Consume the newline left-over
                    UserType type = UserAuthentication.getUserType(userTypeChoice);
                    if (type == null) {
                        System.out.println("Invalid usertype choice.");
                        continue;
                    }
                    if (UserAuthentication.register(name, password, type)) {
                        System.out.println("Registered successfully");
                    } else {
                        System.out.println("Registration failed, please try again");
                    }
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
