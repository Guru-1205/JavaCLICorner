package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import Models.Quiz;

import Models.User;

public class UserController {

    public static ArrayList<User> AoU = new ArrayList<User>();
    public static ArrayList<Quiz> AoQ = new ArrayList<Quiz>();
    public static String USER_FILE = "D:\\Quiz\\files\\User.txt";
    public static String QUIZ_FILE = "D:\\Quiz\\files\\Quiz.txt";
    static {
        UserController.loadUserFile();
        UserController.loadQuizFile();
    }

    @SuppressWarnings("unchecked")
    public static void loadUserFile() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(USER_FILE));
            AoU = (ArrayList<User>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error while loading the file");
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadQuizFile() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(QUIZ_FILE));
            AoQ = (ArrayList<Quiz>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error while loading the file");
        }
    }

    public static void saveUserFile() {

        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(USER_FILE));
            oos.writeObject(AoU);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error while saving the file");
        }

    }

    public static void saveQuizFile() {

        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(QUIZ_FILE));
            oos.writeObject(AoQ);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error while saving the file");
        }

    }

    public static String RegisterUser(String uname, String upass, String utype) {
        AoU.add(new User(Integer.toString(AoU.size() + 1), uname, upass, utype));
        UserController.saveUserFile();
        return Integer.toString(AoU.size() + 1);
    }

    public static String LoginUser(String uname, String upass, String utype) {
        for (User u : AoU) {
            if (u.uname.equals(uname) && u.upass.equals(upass) && u.utype.equals(utype)) {
                return u.uid;
            }
        }
        return Integer.toString(-1);
    }

    public static void CreateQuiz(int c) {
        Quiz q = new Quiz();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Quiz name:");
        String qname = sc.nextLine();
        q.qname = qname;
        for (int i = 0; i < c; i++) {
            System.out.println("enter the question :");
            String qn = sc.nextLine();
            System.out.println("Enter the options :");
            String op1 = sc.nextLine();
            String op2 = sc.nextLine();
            String op3 = sc.nextLine();
            String op4 = sc.nextLine();
            System.out.println("enter the answer option :");
            String ans = sc.nextLine();
            q.qns.put(qn, new ArrayList<>(Arrays.asList(op1, op2, op3, op4)));
            q.ans.add(ans);
        }
        AoQ.add(q);
        for (Quiz q1 : AoQ) {
            System.out.println(q1.qname);
        }
        UserController.saveQuizFile();
    }

    public static void DisplayAllQuiz() {
        for (Quiz q : AoQ) {
            System.out.println(q.qname);
        }
    }

    public static void DisplayQuiz(int n) {
        Quiz q = AoQ.get(n);
        for (Map.Entry<String, ArrayList<String>> qn : q.qns.entrySet()) {
            System.out.println(qn.getKey());
            for (String op : qn.getValue()) {
                System.out.println(op);
            }
            System.out.println();
        }
    }

    public static void ScheduleQuiz(String uid, int i, String dname, String year) {
        Quiz q = AoQ.get(i);
        q.deptYear.put(uid, dname + " " + year);
        UserController.saveQuizFile();
    }

    public static int testQuiz(String dname, String year) {
        for (Quiz q : AoQ) {
            for (Map.Entry<String, String> qn : q.deptYear.entrySet()) {
                String arr[] = qn.getValue().split(" ");
                if (arr[0].equals(dname) && arr[1].equals(year)) {
                    return displayQuizStudentView(q);
                }
            }
        }
        return -1;
    }

    public static int displayQuizStudentView(Quiz q) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> optArray = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> qn : q.qns.entrySet()) {
            System.out.println(qn.getKey());
            for (String op : qn.getValue()) {
                System.out.println(op);
            }
            System.out.println("Select an option");
            String opt = sc.nextLine();
            optArray.add(opt);
            System.out.println();
        }
        return evaluateQuiz(q, optArray);
    }

    public static int evaluateQuiz(Quiz q, ArrayList<String> optArray) {
        int totalMarks = 0;
        for (int i = 0; i < optArray.size(); i++) {
            if (optArray.get(i).equals(q.ans.get(i))) {
                totalMarks++;
            }
        }
        return totalMarks;
    }

    public static void AssignMarks(String dname, String year, String uid, int mark) {
        for (Quiz q : AoQ) {
            for (Map.Entry<String, String> qn : q.deptYear.entrySet()) {
                String[] arr = qn.getValue().split(" ");
                String tid = qn.getKey();
                if (arr[0].equals(dname) && arr[1].equals(year)) {
                    ArrayList<String> marksList = q.marks.getOrDefault(tid, new ArrayList<>());
                    marksList.add(uid + " " + mark);
                    q.marks.put(tid, marksList);
                }
            }
        }
        UserController.saveQuizFile();

    }

}
