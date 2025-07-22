package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Models.Feedback;
import Models.User;

public class UserController {
    public static List<User> AoU = new ArrayList<User>();
    public static List<Feedback> AoF = new ArrayList<Feedback>();
    public static final String USER_FILE = "E:/JAVA PT/Basics_Of_OOPs/OnDemandApp/Files/User.txt";
    static {
        loadFile();
    }

    public static void saveFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE));
            oos.writeObject(AoU);
            oos.close();
        } catch (Exception e) {
            System.out.println("Some exception raised during the saving of file");
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE));
            AoU = (List<User>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Some exception raised during the loading of file");
        }
    }

    public static User FetchUser(int uid) {
        for (User u : AoU) {
            if (uid == u.idGetter())
                return u;
        }
        return null;
    }

    public static int loginUser(String name, String password) {
        for (User i : AoU) {
            if (i.nameGetter().equals(name) && i.passwordGetter().equals(password)) {
                return i.idGetter();
            }
        }
        return -1;
    }

    public static int registerUser(String name, String mobile, String city, String password) {
        AoU.add(new User(AoU.size() + 1, name, password, password, mobile));
        UserController.saveFile();
        System.out.println("User Added Successfully");
        return AoU.size() + 1;
    }

    public static void provideFeedback(int uid, int sid, String feedback) {
        AoF.add(new Feedback(AoF.size() + 1, uid, sid, feedback));
    }
}
