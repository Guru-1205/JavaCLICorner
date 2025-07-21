package authenticationUsingFileClass;

import authenticationUsingFileClass.enums.UserType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserAuthentication {
    private static final String USERS_FILE = "E:/JAVA PT/Basics_Of_OOPs/JavaCLICorner/authenticationUsingFileClass/files/users.txt";
    private static List<User> users = new ArrayList<>();

    // Static block to load users from file
    static {
        loadUsers();
    }

    @SuppressWarnings("unchecked")
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

    public static boolean login(String name, String password, UserType type) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password) && user.getUserType() == type) {
                return true;
            }
        }
        return false;
    }

    public static boolean register(String name, String password, UserType type) {
        User newUser = new User(name, password, type);
        if (users.contains(newUser)) {
            System.out.println("User already exists.");
            return false;
        }
        users.add(newUser);
        saveUsers();
        return true;
    }
}
