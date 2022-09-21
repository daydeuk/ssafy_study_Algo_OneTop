import java.util.*;
import java.io.*;


class Solution {
    // participant :참여자명단
    // completion: 완주자 명단
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String,Integer> p = new HashMap<String,Integer>();
        // 참여자 명단 순회하면서 이름-명수 로 key-value 셋팅
        for (String i : participant){
            if (p.containsKey(i))
                p.put(i,p.get(i)+1);
            else
                p.put(i,1);
        }
        
        // 완주자 명단 순회하면서 참여자명단에서 제거
        for (String i : completion){
            if(p.containsKey(i)){
                if(p.get(i)==1){
                    p.remove(i);
                }
                else
                    p.put(i,p.get(i)-1);
            }
        }
        
       
        for( String i : p.keySet()){
            answer=i;        }
        
        return answer;
    }
}
