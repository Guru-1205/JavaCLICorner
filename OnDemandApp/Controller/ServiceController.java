package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.User;

public class ServiceController {
    // service -> sid,sname,scategory,slocation,smobile,savail;
    public static List<HashMap<String, String>> AoSP = new ArrayList<>();
    static {
        loadServices();
    }

    public static void loadServices() {
        HashMap<String, String> hmp = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hmp.put("id", String.valueOf(i + 1));
            hmp.put("name", "abcd" + (i + 1));
            hmp.put("category", "Home cleaning");
            hmp.put("location", "madurai" + (i + 1));
            hmp.put("avail", "9.00 to 18.00");
            AoSP.add(hmp);
        }
    }

    public static void bookServiceProvider(int uid, int sid) {
        User u = UserController.FetchUser(uid);
        u.csidSetter(sid);
        u.historySetter(sid);
    }

    public static void displayHistory(int uid) {
        User u = UserController.FetchUser(uid);
        ArrayList<Integer> hist = u.historyGetter();
        for (Integer h : hist) {
            System.out.println(h);
        }
    }

    public static void viewAllServiceProvider() {
        for (HashMap<String, String> item : AoSP) {
            System.out.println(item.get("id"));
            System.out.print(item.get("name") + " ");
            System.out.print(item.get("category"));
        }
        System.out.println();
    }

    public static void searchByLocation(String loc) {
        for (HashMap<String, String> item : AoSP) {
            if (item.get("location").equals(loc)) {
                System.out.println(item.get("name"));
                System.out.println(item.get("category"));
            }
        }
    }
}
