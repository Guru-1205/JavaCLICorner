package Controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Models.User;
import Models.UserProfile;
import Models.enums.UserType;

public class UserController {
    public final static String USER_PROFILE_FILE = "E:\\CODE GALLATA\\OVERALL NOTES\\LONG CODING & DESIGN PATTERNS\\SAMPLE CODE\\FinanceTrackerSystem\\Files\\UsersDetails.txt";
    public static List<User> users = new ArrayList<>();
    public static User currentUser;

    static {
        UserController.loadUsersDetails();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    // for every user details modification, we are calling this method instead we
    // can call this method only when the user logs out
    public static boolean saveUsersDetails() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_PROFILE_FILE))) {
            oos.writeObject(users);
            return true;
        } catch (Exception e) {
            System.out.println("Error saving user details: " + e.getMessage());
            return false;
        }
    }

    public static void loadUsersDetails() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_PROFILE_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading user details: " + e.getMessage());
        }
    }

    public static UUID createUserProfile(String name, String address, String phoneNumber, LocalDate dob,
            UserType type) {
        User user = new User(new UserProfile(name, address, phoneNumber, dob), type);
        users.add(user);
        if (UserController.saveUsersDetails()) {
            System.out.println("User profile created successfully!");
            currentUser = user; // Set the current user
            return user.getId();
        } else {
            System.out.println("Failed to create user profile.");
            return null;
        }
    }

    public static UUID getUserIdByProfile(String name, LocalDate dob, UserType type) {
        for (User user : users) {
            if (user.getProfile().getName().equals(name) && user.getProfile().getDob().equals(dob)
                    && user.getType() == type) {
                System.out.println("Login successful for user: " + name);
                currentUser = user; // Set the current user
                return user.getId();
            }
        }
        System.out.println("Login failed. User not found.");
        return null;
    }

    public static User getUserById(UUID userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

}
