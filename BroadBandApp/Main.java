import java.util.Scanner;

import Controller.PlanController;
import Controller.UserController;
import FinanceTrackerSystem.Models.User;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 for login");
        System.out.println("Enter 2 for register");
        System.out.println("Enter 3 for exit");
        int x = sc.nextInt();
        sc.nextLine();
        switch (x) {
            case 1 -> Main.LoginMenu();
            case 2 -> Main.RegisterMenu();
            case 3 -> {
                System.out.println("exiting from the application");
                sc.close();
                return;
            }
        }
        sc.close();

    }

    public static void LoginMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username :");
        String uname = sc.nextLine();
        System.out.println("Enter the password :");
        String upass = sc.nextLine();
        int uId = UserController.LoginUser(uname, upass);
        if (uId != -1) {
            System.out.println("Login Success");
            MainMenu(uId);
        } else {
            System.out.println("Login failed ...!!!\n Please try again with different username or password");
            for (int i = 0; i < 3; i++) {
                Main.LoginMenu();
            }
            System.out.println("Attempts over... please try again after sometime");
            return;

        }
    }

    public static void RegisterMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username :");
        String uname = sc.nextLine();
        System.out.println("Enter the password :");
        String upass = sc.nextLine();
        System.out.println("Enter the mobile number :");
        String mobile = sc.nextLine();
        System.out.println("Enter the address :");
        String address = sc.nextLine();
        // constraint -> uname and upass -> already user exist??
        int uId = UserController.LoginUser(uname, upass);
        if (uId != -1) {
            System.out.println(
                    "Someone exist with these credentials ... so please enter different username and password");
            for (int i = 0; i < 3; i++) {
                Main.RegisterMenu();
            }
            System.out.println("Attempts over.... Please try again after sometime");
            return;
        }
        uId = UserController.AddUser(uname, upass, address, mobile);
        MainMenu(uId);
    }

    public static void MainMenu(int uId) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 1 for Listing all the plans with details");
            System.out.println("Enter 2 for Subcribing to a plan");
            System.out.println("Enter 3 for Cancel / Upgrade / Downgrade plan");
            System.out.println("Enter 4 for Providing Feedback and Rating");
            System.out.println("Enter 5 for Listing all the Feedbacks");
            System.out.println("Enter 6 for Your Subscription history");
            System.out.println("Enter 7 for Updating your account details");
            int x = sc.nextInt();
            sc.nextLine();
            switch (x) {
                case 1 -> PlanController.ListAllPlans();
                case 2 -> {
                    System.out.println("Listing all the plans details");
                    PlanController.ListAllPlans();
                    System.out.println("Please select the appropriate plan id for subcription...");
                    int p = sc.nextInt();
                    sc.nextLine();
                    PlanController.SubcribePlan(uId, p);
                }

                case 3 -> {
                    if (UserController.RetreiveUser(uId).cpIdGetter() != null) {
                        System.out.println("Enter 1/2/3 for cancel/upgrade/downgrade");
                        int e = sc.nextInt();
                        sc.nextLine();
                        switch (e) {
                            case 1 -> PlanController.CancelPlan(uId);
                            case 2 -> PlanController.UpgradePlan(uId);
                            case 3 -> PlanController.DowngradePlan(uId);
                        }
                    } else {
                        System.out.println("You dont have any current plan to cancel/upgrade/downgrade");
                    }
                }
                case 4 -> {
                    System.out.println("Enter your valuable feedback :");
                    String f = sc.nextLine();
                    UserController.ProvideFeedback(uId, f);
                }
                case 5 -> UserController.ListAllFeedback();
                case 6 -> UserController.SubscriptionHistory(uId);
                case 7 -> {
                    UserController.displayUser(uId);
                    System.out.println("Enter the username :");
                    String name = sc.nextLine();
                    System.out.println("Enter the password :");
                    String password = sc.nextLine();
                    System.out.println("Enter the mobile number :");
                    String mobile = sc.nextLine();
                    System.out.println("Enter the address :");
                    String address = sc.nextLine();
                    UserController.UpdateUser(uId, name, password, address, mobile);
                }
                default -> System.out.println("Please enter a valid number");
            }

        }
    }
}