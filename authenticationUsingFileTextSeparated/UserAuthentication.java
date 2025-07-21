package authenticationUsingFileTextSeparated;

import authenticationUsingFileTextSeparated.enums.UserType;
import java.io.*;
import java.util.Scanner;

public class UserAuthentication {
    private static final String USERS_FILE = "E:/JAVA PT/Basics_Of_OOPs/JavaCLICorner/authentication/files/users.txt";

    public static UserType getUserType(int userTypeChoice) {
        switch (userTypeChoice) {
            case 1:
                return UserType.DOCTOR;
            case 2:
                return UserType.PATIENT;
            default:
                System.out.println("Invalid usertype...");
                return null; // or throw an exception or return a default UserType
        }
    }

    public static boolean login(String name, String password, UserType type) throws IOException {
        File file = new File(USERS_FILE);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String arr[] = line.split(",");
                if (arr[0].equals(name) && arr[1].equals(password) && arr[2].equals(type.name())) {
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("User file not found: " + e.getMessage());
            return false;
        }
    }

    public static boolean register(String name, String password, UserType type) throws IOException {
        try (FileWriter writer = new FileWriter(USERS_FILE, true)) {
            writer.write(name + "," + password + "," + type.name() + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
}
