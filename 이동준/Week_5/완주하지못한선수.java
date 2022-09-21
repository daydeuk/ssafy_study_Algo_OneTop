import java.util.HashMap;
import java.util.Iterator;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        for (String c : completion) {
            int cnt = map.getOrDefault(c, 0) - 1;
            if (cnt > 0)
                map.put(c, cnt);
            else if (cnt == 0)
                map.remove(c);
        }

        Iterator iter = map.keySet().iterator();

        String answer = "";
        while (iter.hasNext()) {
            answer = (String) iter.next();
        }
        return answer;
    }
}