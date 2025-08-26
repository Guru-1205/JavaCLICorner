package NumberConverterSystem.Helpers;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommonHelper {
    public static HashMap<String, Integer> sortByValue(
            HashMap<String, Integer> hm, int top) {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
        Collections.sort(list, (i1, i2) -> i2.getValue().compareTo(i1.getValue()));
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            if (top-- == 0) {
                return temp;
            }
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
