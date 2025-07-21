import java.util.Scanner;

import Controller.UserController;

public class Main {
    public static void main(String[] args) {
        System.out.println("1 for Register");
        System.out.println("2 for Login");
        System.out.println("3 for exit");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        sc.nextLine();
        switch (x) {
            case 1 -> Main.RegisterMenu();
            case 2 -> Main.LoginMenu();
            case 3 -> {
                return;
            }
        }
    }

    public static void RegisterMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter User name");
        String uname = sc.nextLine();
        System.out.println("Enter User password");
        String upass = sc.nextLine();
        System.out.println("Enter User type");
        String utype = sc.nextLine();
        String uid = UserController.RegisterUser(uname, upass, utype);
        switch (utype) {
            case "ADMIN" -> adminMenu(uid);
            case "TEACHER" -> teacherMenu(uid);
            case "STUDENT" -> studentMenu(uid);
            default -> System.out.println("invalid user type");
        }
    }

    public static void LoginMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter User name");
        String uname = sc.nextLine();
        System.out.println("Enter User password");
        String upass = sc.nextLine();
        System.out.println("Enter User type");
        String utype = sc.nextLine();
        String uid = UserController.LoginUser(uname, upass, utype);
        switch (utype) {
            case "ADMIN" -> adminMenu(uid);
            case "TEACHER" -> teacherMenu(uid);
            case "STUDENT" -> studentMenu(uid);
            default -> System.out.println("invalid user type");
        }
    }

    public static void adminMenu(String uid) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 for Creating the quiz");
        System.out.println("Enter 2 for viewing the Quiz");
        int x = sc.nextInt();
        sc.nextLine();
        switch (x) {
            case 1 -> {
                int c = sc.nextInt();
                sc.nextLine();
                UserController.CreateQuiz(c);
            }
            case 2 -> {
                UserController.DisplayAllQuiz();
                System.out.println("Enter the quiz number");
                int a = sc.nextInt();
                sc.nextLine();
                UserController.DisplayQuiz(a);
            }
        }
    }

    public static void teacherMenu(String uid) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 for Scheduling the quiz");
        int x = sc.nextInt();
        sc.nextLine();
        switch (x) {
            case 1 -> {
                UserController.DisplayAllQuiz();
                System.out.println("Enter the quiz number");
                int a = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the dept name");
                String dname = sc.nextLine();
                System.out.println("Enter the year");
                String year = sc.nextLine();

                UserController.ScheduleQuiz(uid, a, dname, year);
            }
        }
    }

    public static void studentMenu(String uid) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the dept name");
        String dname = sc.nextLine();
        System.out.println("Enter the year");
        String year = sc.nextLine();
        int marks = UserController.testQuiz(dname, year);
        System.out.println(marks);
        UserController.AssignMarks(dname, year, uid, marks);
    }
}
