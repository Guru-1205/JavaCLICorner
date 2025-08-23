package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import FinanceTrackerSystem.Models.User;

public class PlanController {
    // plan -> name,id,price ,duration,speed limit
    public static List<HashMap<String, String>> AoP = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static {
        LoadPlans();
    }

    public static void LoadPlans() {
        for (int i = 1; i <= 10; i++) {
            HashMap<String, String> plan = new HashMap<>();
            plan.put("name", "Plan " + i);
            plan.put("id", String.valueOf(i));
            plan.put("price", String.valueOf(100 + i * 10)); // Example price: 110, 120, ...
            plan.put("duration", String.valueOf(30 + i * 5)); // Example duration: 35, 40, ...
            plan.put("speed_limit", String.valueOf(50 + i * 10) + " Mbps"); // Example speed: 60 Mbps, 70 Mbps, ...

            AoP.add(plan);
        }
    }

    public static void ListAllPlans() {
        for (HashMap<String, String> plan : AoP) {
            System.out.println(plan);
        }
    }

    public static void SubcribePlan(int uId, int p) {
        User u = UserController.RetreiveUser(uId);
        u.cpIdSetter(String.valueOf(p));
        UserController.saveUserFile();
        System.out.printf("Current plan of %s is %s", u.nameGetter(), u.cpIdGetter());
    }

    public static void CancelPlan(int uId) {
        User u = UserController.RetreiveUser(uId);
        u.history.add(u.cpIdGetter());
        u.cpIdSetter(null);
        UserController.saveUserFile();
        System.out.printf("Current plan of %s is %s", u.nameGetter(), u.cpIdGetter());
        System.out.println(" Cancelled successfully");
    }

    public static void UpgradePlan(int uId) {
        User u = UserController.RetreiveUser(uId);
        String cpId = u.cpIdGetter();
        int cpPrice = 0;
        for (HashMap<String, String> plan : AoP) {
            if (cpId.equals(plan.get("id"))) {
                cpPrice = Integer.parseInt(plan.get("price"));
                break;
            }
        }
        if (cpPrice != 0) {
            for (HashMap<String, String> plan : AoP) {
                if (cpPrice < Integer.parseInt(plan.get("price"))) {
                    System.out.println(plan);
                }
            }
            System.out.println("Enter the corresponding plan id from the above list to upgrade....");
            int x = sc.nextInt();
            sc.nextLine();
            u.history.add(u.cpIdGetter());
            u.cpIdSetter(String.valueOf(x));
            UserController.saveUserFile();
            System.out.printf("The current plan of %s is %s", u.nameGetter(), u.cpIdGetter());
            System.out.println("Upgraded the plan successfully");
        }
    }

    public static void DowngradePlan(int uId) {
        User u = UserController.RetreiveUser(uId);
        String cpId = u.cpIdGetter();
        int cpPrice = 0;
        for (HashMap<String, String> plan : AoP) {
            if (cpId.equals(plan.get("id"))) {
                cpPrice = Integer.parseInt(plan.get("price"));
                break;
            }
        }
        if (cpPrice != 0) {
            for (HashMap<String, String> plan : AoP) {
                if (cpPrice > Integer.parseInt(plan.get("price"))) {
                    System.out.println(plan);
                }
            }
            System.out.println("Enter the corresponding plan id from the above list to upgrade....");
            int x = sc.nextInt();
            sc.nextLine();
            u.history.add(u.cpIdGetter());
            u.cpIdSetter(String.valueOf(x));
            UserController.saveUserFile();
            System.out.printf("The current plan of %s is %s", u.nameGetter(), u.cpIdGetter());
            System.out.println("Upgraded the plan successfully");
        }
    }

}
