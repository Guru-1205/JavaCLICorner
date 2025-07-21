package crudOperations;

import crudOperations.models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserAuthentication {
    private static final String USERS_FILE = "E:/JAVA PT/Basics_Of_OOPs/JavaCLICorner/crudOperations/files/users.txt";
    private static List<User> users = new ArrayList<>();

    static {
        loadUsers();
    }

    private static void loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (List<User>) ois.readObject();
        } catch (EOFException e) {
            // Ignore, file is empty
        } catch (FileNotFoundException e) {
            System.out.println("User file not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
    }

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error writing users: " + e.getMessage());
        }
    }

    public static boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean register(String username, String password) {
        User newUser = new User(username, password);
        if (users.contains(newUser)) {
            System.out.println("User already exists.");
            return false;
        }
        users.add(newUser);
        saveUsers();
        return true;
    }
}
