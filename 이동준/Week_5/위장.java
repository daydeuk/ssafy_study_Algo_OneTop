import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        
        for(String[] clo : clothes) {
            
            ArrayList<String> list = map.getOrDefault(clo[1], new ArrayList<>());
            list.add(clo[0]);
            map.put(clo[1], list);
        }
        
        int answer = 1;
        
        Iterator iter = map.keySet().iterator();
        while(iter.hasNext()) {
            answer *= (map.get(iter.next()).size() + 1);
        }
        
        return answer - 1;
    }
}
