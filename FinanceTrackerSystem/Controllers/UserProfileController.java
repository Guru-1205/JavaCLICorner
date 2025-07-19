package Controllers;

import java.time.LocalDate;
import java.util.UUID;

import Models.UserProfile;
import static Controllers.UserController.currentUser;

public class UserProfileController {
    public static void viewProfile() {
        if (currentUser != null) {
            System.out.println("User Profile:");
            System.out.println(currentUser.getProfile().toString());
        } else {
            System.out.println("User not found.");
        }
    }

    public static void editProfile(String name, String address, String phoneNumber, LocalDate dob) {
        if (currentUser != null) {
            UserProfile profile = currentUser.getProfile();
            profile.setName(name);
            profile.setAddress(address);
            profile.setPhoneNumber(phoneNumber);
            profile.setDob(dob);
            if (UserController.saveUsersDetails()) {
                System.out.println("Profile updated successfully.");
            } else {
                System.out.println("Failed to update profile.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public static void deleteProfile() {
        if (currentUser != null) {
            UserController.users.remove(currentUser);
            if (UserController.saveUsersDetails()) {
                System.out.println("Profile deleted successfully.");
            } else {
                System.out.println("Failed to delete profile.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
}
