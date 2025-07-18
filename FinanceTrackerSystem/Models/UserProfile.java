package Models;

import java.time.LocalDate;

public class UserProfile {
    private static final long serialVersionUID = 1L; // For serialization compatibility
    private String name, address, phoneNumber;
    private LocalDate dob;

    // parameterized constructor
    public UserProfile(String name, String address, String phoneNumber, LocalDate dob) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter for phoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter for dob
    public LocalDate getDob() {
        return dob;
    }

    // Setter for dob
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    // Method to display user profile information
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Date of Birth: " + dob;
    }
}
