package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long SerialVersionUID = 10l;
    private String uname, password, address, mobile, cpId;
    private int uId;
    public List<String> history = new ArrayList<String>();
    public List<String> feedback = new ArrayList<String>();

    public User(int uId, String uname, String password, String address, String mobile) {
        this.uId = uId;
        this.uname = uname;
        this.address = address;
        this.mobile = mobile;
        this.password = password;
    }

    public int idGetter() {
        return uId;
    }

    public String nameGetter() {
        return uname;
    }

    public String passwordGetter() {
        return password;
    }

    public String addressGetter() {
        return address;
    }

    public String mobileGetter() {
        return mobile;
    }

    public String cpIdGetter() {
        return cpId;
    }

    public void updateSetter(String name, String password, String address, String mobile) {
        this.uname = name;
        this.address = address;
        this.mobile = mobile;
        this.password = password;
    }

    public void cpIdSetter(String cpId) {
        this.cpId = cpId;
    }

    public String toString() {
        return "Name :" + this.uname + " address :" + this.address + "mobile :" + this.mobile;
    }
}

// user class version 10 -> 2 object -> file store
// i am making some changes in the user class version 10
// file 2 object version 10 retreive -> possible User array version 10
