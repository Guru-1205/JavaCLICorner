import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Prescription; // Update with your actual package name
import MedicalRecordManagement; // Update with your actual package name
import MedicalRecord; // Update with your actual package name

enum UserType {
    DOCTOR,
    PATIENT
}

class UserAuthentication {
    private static final String USERS_FILE = "E:/JAVA PT/Basics_Of_OOPs/DoctorPatientApp/userDetails.txt";

    public static void registerUser(String name, String password, UserType userType) throws IOException {
        try (FileWriter writer = new FileWriter(new File(USERS_FILE), true)) {
            writer.write(name + "," + password + "," + userType + "\n");
        } catch (Exception e) {
            System.out.println("Exception found: " + e.getMessage());
            System.out.println("User not registered...");
        }
        System.out.println("User registered successfully...!!!");
    }

    public static boolean loginUser(String name, String password) throws IOException {
        try (Scanner sc = new Scanner(new File(USERS_FILE))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(name) && parts[1].equals(password)) {
                    System.out.println("Logged in successfully...!!!");
                    return true;
                }
            }
            System.out.println("No such user found with given login credentials");
        } catch (Exception e) {
            System.out.println("Exception found: " + e.getMessage());
        }
        return false;
    }

    public static UserType getUserType(String name, String password) throws IOException {
        try (Scanner sc = new Scanner(new File(USERS_FILE))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(name) && parts[1].equals(password)) {
                    return UserType.valueOf(parts[2]);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception found: " + e.getMessage());
        }
        return null;
    }
}

class Appointment implements Serializable {
    private String docname, patname, date, time, request;

    public Appointment(String docname, String patname, String date, String time, String request) {
        this.docname = docname;
        this.patname = patname;
        this.date = date;
        this.time = time;
        this.request = request;
    }

    public String getDocname() {
        return docname;
    }

    public String getPatname() {
        return patname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Doctor Name : " + docname + " Patient Name : " + patname + " Date : " + date + " Time : " + time
                + " Request : " + request;
    }
}

class AppointmentManagement {
    public static List<Appointment> appointmentList = new ArrayList<>();
    private static final String SCHEDULE_DETAILS = "E:/JAVA PT/Basics_Of_OOPs/DoctorPatientApp/scheduleDetails.txt";

    @SuppressWarnings("unchecked")
    public static void loadAppointmentDetails() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCHEDULE_DETAILS))) {
            appointmentList = (List<Appointment>) ois.readObject();
        } catch (EOFException e) {
            System.out.println("End of the file reached");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static {
        try {
            loadAppointmentDetails();
        } catch (EOFException e) {
            System.out.println("End of the file reached");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAppointmentDetails() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCHEDULE_DETAILS))) {
            oos.writeObject(appointmentList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void schedule(String docname) throws IOException {
        if (appointmentList.size() == 0)
            return;
        Scanner sc = new Scanner(System.in);
        int index = 0;
        System.out.println("Select the appointment to be scheduled");
        for (Appointment al : appointmentList) {
            if (al.getDocname().equals(docname)) {
                System.out.println((index + 1) + ". " + al);
            }
            index++;
        }
        int x = sc.nextInt();
        sc.nextLine(); // Consume the newline
        Appointment apt = appointmentList.get(x - 1);
        apt.setRequest("Accepted");
        saveAppointmentDetails();

    }

    public static void viewschedule(String docname) {
        if (appointmentList.size() != 0) {
            for (Appointment al : appointmentList) {
                if (docname.equals(al.getDocname()))
                    System.out.println(al);
            }
        }
    }

    public static void reschedule(String docname) throws IOException {
        Scanner sc = new Scanner(System.in);
        int index = 0;
        if (appointmentList.size() != 0) {
            for (Appointment al : appointmentList) {
                if (docname.equals(al.getDocname()) && al.getRequest().equals("Accepted")) {
                    System.out.println((index + 1) + ". " + al);
                }
                index++;
            }
            int x = sc.nextInt();
            sc.nextLine(); // Consume the newline
            Appointment apt = appointmentList.get(x - 1);
            System.out.println("Enter the rescheduled time and date :");
            String reqtime = sc.next();
            String reqdate = sc.next();
            sc.nextLine(); // Consume the newline
            apt.setDate(reqdate);
            apt.setTime(reqtime);
            saveAppointmentDetails();
        }
    }

    public static void cancelschedule(String docname) throws IOException {
        int index = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println(appointmentList.size());
        if (appointmentList.size() != 0) {
            for (Appointment al : appointmentList) {
                if (docname.equals(al.getDocname()) && al.getRequest().equals("Accepted")) {
                    System.out.println((index + 1) + ". " + al);
                }
                index++;
            }

            int x = sc.nextInt();
            sc.nextLine(); // Consume the newline
            appointmentList.remove(x - 1);
            System.out.println("Cancelled the requested appointment...");
            saveAppointmentDetails();
        }
    }

    public static boolean requestschedule(String docname, String patname, String date, String time) throws IOException {
        if (appointmentList.size() != 0) {
            for (Appointment al : appointmentList) {
                if (al.getDocname().equals(docname) && al.getDate().equals(date) && al.getTime().equals(time)) {
                    System.out.println("Already being booked by other patient");
                    return false;
                }
            }
        }
        Appointment apt = new Appointment(docname, patname, date, time, "requested");
        appointmentList.add(apt);
        saveAppointmentDetails();
        return true;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int x = sc.nextInt();
            sc.nextLine(); // Consume the newline
            switch (x) {
                case 1 -> loginMenu();
                case 2 -> registerMenu();
                case 3 -> {
                    System.out.println("Exiting the application....");
                    return;
                }
                default -> System.out.println("Invalid choice... Please try again");
            }
        }
    }

    public static void loginMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name:");
        String name = sc.nextLine();
        System.out.println("Enter the password:");
        String password = sc.nextLine();
        if (UserAuthentication.loginUser(name, password)) {
            UserType userType = UserAuthentication.getUserType(name, password);
            if (userType == null) {
                System.out.println("Invalid user type...!!!");
                return;
            }
            switch (userType) {
                case DOCTOR -> doctorMenu(name);
                case PATIENT -> patientMenu(name);
                default -> System.out.println("Invalid option");
            }
        }
    }

    public static void registerMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name :");
        String name = sc.nextLine();
        System.out.println("Password :");
        String password = sc.nextLine();
        if (UserAuthentication.loginUser(name, password)) {
            System.out.println(
                    "User already exists with the given credentials... Please try again with different username and password.");
            return;
        }
        UserType userType = null;
        System.out.println("Enter 1 for registering as Doctor");
        System.out.println("Enter 2 for registering as Patient");
        int x = sc.nextInt();
        sc.nextLine(); // Consume the newline
        switch (x) {
            case 1 -> userType = UserType.DOCTOR;
            case 2 -> userType = UserType.PATIENT;
            default -> System.out.println("Invalid choice...");
        }
        UserAuthentication.registerUser(name, password, userType);
    }

    public static void doctorMenu(String name) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Schedule appointment");
            System.out.println("2. Re-schedule appointment");
            System.out.println("3. Cancel scheduled appointment");
            System.out.println("4. View scheduled appointment");
            System.out.println("5. Exit");
            System.out.println("6. Creation of Medical Record");
            System.out.println("7. Displaying the medical record");
            System.out.println("8. Creation of Prescription");
            System.out.println("9. Display Prescription");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline
            switch (choice) {
                case 1 -> AppointmentManagement.schedule(name);
                case 2 -> AppointmentManagement.reschedule(name);
                case 3 -> AppointmentManagement.cancelschedule(name);
                case 4 -> AppointmentManagement.viewschedule(name);
                case 5 -> {
                    System.out.println("Exiting doctor menu...");
                    return;
                }
                case 6 -> {
                    System.out.println("Enter the patient name : ");
                    String patname = sc.nextLine();
                    System.out.println(
                            "Enter the details of treatment plan, diagnosis, and medication for creating/updating medical record:");
                    String treatment = sc.nextLine();
                    System.out.println("Enter the diagnosis details :");
                    String diagnosis = sc.nextLine();
                    System.out.println("Enter the medication prescribed details :");
                    String medication = sc.nextLine();
                    MedicalRecordManagement.createRecord(name,
                            new MedicalRecord(name, patname, diagnosis, treatment, medication));
                }
                case 7 -> MedicalRecordManagement.displayMedicalRecord(name);
                case 8 -> {
                    System.out.println("Enter the patient name: ");
                    String patname = sc.nextLine();
                    Prescription.creation(name, patname);
                }
                case 9 -> {
                    System.out.println("Enter the patient name: ");
                    String patname = sc.nextLine();
                    Prescription.display(patname);
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }

        }

    }

    public static void patientMenu(String name) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Request for an appointment");
            System.out.println("2. Request for re-scheduling an appointment");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int x = sc.nextInt();
            sc.nextLine(); // Consume the newline
            switch (x) {
                case 1 -> {
                    System.out.println("Enter the doctor name : ");
                    String docname = sc.nextLine();
                    System.out.println("Enter the date and time for appointment :");
                    String date = sc.next();
                    String time = sc.next();
                    sc.nextLine(); // Consume the newline
                    AppointmentManagement.requestschedule(docname, name, date, time);
                }
                case 2 -> {
                    System.out.println("Enter the doctor name : ");
                    String docname = sc.nextLine();
                    System.out.println("Enter the date and time for appointment :");
                    String date = sc.next();
                    String time = sc.next();
                    sc.nextLine(); // Consume the newline
                    AppointmentManagement.requestschedule(docname, name, date, time);
                }
                case 3 -> {
                    System.out.println("Exiting patient menu...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }
}
