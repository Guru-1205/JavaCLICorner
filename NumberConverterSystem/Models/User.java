package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public int id;
    public String username;
    public String password;
    public ArrayList<Conversion> currentSessionConversions;
    public int undoCountInCurrentSession;
    public HashMap<LocalDate, ArrayList<Conversion>> conversionHistory;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.currentSessionConversions = new ArrayList<>();
        this.conversionHistory = new HashMap<>();
        this.undoCountInCurrentSession = 0;
    }
}
