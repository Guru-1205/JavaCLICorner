package Controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import Models.User;

public class AuthController {
    public static ArrayList<User> userList;
    private static final String USER_DATA_FILE_PATH = "E:\\CODE GALLATA\\OVERALL NOTES\\LONG CODING & DESIGN PATTERNS\\SAMPLE CODE\\NumberConverterSystem\\Files\\User.txt";

    static {
        loadUserList();
    }

    public static boolean saveUserList() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE_PATH))) {
            oos.writeObject(userList);
            return true;
        } catch (Exception e) {
            System.out.println("There was a problem while saving the user file: " + e.getMessage());
            return false;
        }
    }

    public static void loadUserList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE_PATH))) {
            userList = (ArrayList<User>) ois.readObject();
        } catch (Exception e) {
            userList = new ArrayList<>(); // fallback to empty list
            System.out.println("There was a problem while loading the user file.");
        }
    }

    public static int createUser(String username, String password) {
        if (username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
            int id = new Random().nextInt();
            userList.add(new User(id, username, password));
            if (!saveUserList()) {
                userList.removeIf(user -> user.username.equals(username));
            }
            return id;
        }
        return -1;
    }

    public static int verifyUser(String username, String password) {
        for (User user : userList) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user.id;
            }
        }
        return -1;
    }
}
