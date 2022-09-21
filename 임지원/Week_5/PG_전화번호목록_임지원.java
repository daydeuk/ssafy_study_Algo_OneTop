import java.util.HashMap;
import java.util.Arrays;
import java.util.Map.Entry;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true; 
        Arrays.sort(phone_book);
        HashMap<Integer, String> map = new HashMap<>(phone_book.length);       // 번호를 Value에
        
        for(int i = 0; i < phone_book.length; i++){
            map.put(i, phone_book[i]);
        }
        
        for (Entry<Integer, String> e : map.entrySet() ) {
            // 마지막 Key값이면 비교대상 없으므로 false
            if(e.getKey() == map.size()-1) break; 
              
            if((map.get(e.getKey() + 1)).startsWith(e.getValue())){
                answer = false;
                break;
            } 
        }
        return answer;
    }
}