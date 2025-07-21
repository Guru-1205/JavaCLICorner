package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Quiz implements Serializable {
    private static final long SerialVersionUID = 10l;
    public String qname;
    public HashMap<String, ArrayList<String>> qns = new HashMap<>();
    public ArrayList<String> ans = new ArrayList<>();
    public HashMap<String, String> deptYear = new HashMap<>();
    public HashMap<String, ArrayList<String>> marks = new HashMap<>();
}
