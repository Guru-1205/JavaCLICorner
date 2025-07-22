package Models;

import java.io.Serializable;

public class Feedback implements Serializable {
    private static final long SerialVersionUID = 1;
    private int uid, sid, fid;
    private String feedback;

    public Feedback(int fid, int uid, int sid, String feedback) {
        this.fid = fid;
        this.uid = uid;
        this.sid = sid;
        this.feedback = feedback;
    }
}
