import java.util.*;

/*
효율성
hm.getOrDefault(p,0) 
*/

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = ""; 
        Map<String, Integer> hm = new HashMap<>();
        
        // 참여한 선수들의 이름이 담긴 배열 participant
        for(String p : participant){
	          // hm.getOrDefault(p,0) : HashMap hm에 p라는 키의 값 반환, 없으면 0 반환 (동명이인 카운트위해)
            hm.put(p, hm.getOrDefault(p,0) + 1);
        }
        
        // 완주한 선수들의 이름이 담긴 배열 completion
        for(String c : completion){
            hm.put(c, hm.get(c)-1);
        }
        
        // 완주를 못한 한선수
        for(String key : hm.keySet()){
            if(hm.get(key) != 0){
                answer = key;
            }
        }
        
        return answer;
    }

}