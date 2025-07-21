import java.util.Scanner;

import Controller.ServiceController;
import Controller.UserController;

public class Main {
    public static void main(String s[]) {
        System.out.println("Enter 1 for Login");
        System.out.println("Enter 2 for Register");
        System.out.println("Enter 3 for exit");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        switch (x) {
            case 1:
                Main.LoginMenu();
                break;
            case 2:
                Main.RegisterMenu();
                break;
            case 3:
                return;

        }
    }

    public static void LoginMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Name");
        String name = sc.nextLine();
        System.out.println("Enter the password");
        String password = sc.nextLine();
        int uid = UserController.loginUser(name, password);
        if (uid == -1) {
            System.out.println("User doesnt exist");
            return;
        }
        Main.ServiceMenu(uid);
    }

    public static void RegisterMenu() {
        // name,mobile,location,password
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Name");
        String name = sc.nextLine();
        System.out.println("Enter the mobile");
        String mobile = sc.nextLine();
        System.out.println("Enter the Location - City");
        String city = sc.nextLine();
        System.out.println("Enter the password");
        String password = sc.nextLine();
        // same name and password already someone existing ahh check pannalam
        int uid = UserController.registerUser(name, mobile, city, password);
        Main.ServiceMenu(uid);
    }

    public static void ServiceMenu(int uid) {
        System.out.println("Enter 1 for View all the service provider");
        System.out.println("Enter 2 for Book a Service Provider");
        System.out.println("Enter 3 for Displaying the reviews of Service provider (specific domain)");
        System.out.println("Enter 4 for view Ratings and Feedbacks");
        System.out.println("Enter 5 for searching the service provider by location");
        System.out.println("Enter 6 for checking the history of the user");
        System.out.println("Enter 7 for providing feedback");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        sc.nextLine();
        int sid = -1;
        switch (x) {
            case 1 -> ServiceController.viewAllServiceProvider();
            case 2 -> {
                ServiceController.viewAllServiceProvider();
                sid = sc.nextInt();
                ServiceController.bookServiceProvider(uid, sid);
            }
            case 5 -> {
                String loc = sc.nextLine();
                ServiceController.searchByLocation(loc);
            }
            case 7 -> {
                System.out.println("Provide Feedback....");
                String f = sc.nextLine();
                UserController.provideFeedback(uid, sid, f);
            }
        }
    }
}