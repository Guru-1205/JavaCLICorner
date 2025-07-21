package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long SerialVersionUID = 899;
    private String uname, upassword, ulocation, umobile;
    private ArrayList<Integer> history = new ArrayList<>();
    private int uid, csid;

    public User(int uid, String uname, String upassword, String ulocation, String umobile) {
        this.uid = uid;
        this.uname = uname;
        this.umobile = umobile;
        this.ulocation = ulocation;
        this.upassword = upassword;
    }

    public int idGetter() {
        return uid;
    }

    public String nameGetter() {
        return uname;
    }

    public String passwordGetter() {
        return upassword;
    }

    public void csidSetter(int csid) {
        this.csid = csid;
    }

    public void historySetter(int csid) {
        this.history.add(csid);
    }

    public ArrayList<Integer> historyGetter() {
        return history;
    }
}
