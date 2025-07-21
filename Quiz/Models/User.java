package Models;

import java.io.Serializable;

public class User implements Serializable {
    private static final long SerialVersionUID = 10l;
    public String uid, uname, upass, utype;

    public User(String uid, String uname, String upass, String utype) {
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;
        this.utype = utype;
    }

}
