import java.io.*;
import java.util.*;


class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // id_list :이용자의 ID가 담긴 문자열 배열
        // report : 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 (이용자id 신고한id)
        // k : 정지되는 신고 횟수
        
        // 신고횟수에 제한 없음
        // 서로 다른 유저를 계속해서 신고할 수 있음
        // 한 유저를 여러번 신고할 수 있음 
        
        
        //key: 이용자 id / value: 신고한 id
        HashMap<String,HashSet<String>> map = new HashMap<>();
        
        //key: 신고된 id / value: 신고당한 횟수
        HashMap<String,Integer> count = new HashMap<String,Integer>();
        
        //정지된 id
        HashSet<String> out = new HashSet<String>();
        
        
        for (String temp : report){
            StringTokenizer st = new StringTokenizer(temp);
            String a = st.nextToken(); //신고한 사람
            String b = st.nextToken(); //신고당한 사람
            
            //신고한 사람의 맵 초기화
             if(!map.containsKey(a)){
                 map.put(a,new HashSet<String>());
                }
            
            //a가 b를 처음신고할때만
            if(!map.get(a).contains(b)){
            
                if(!count.containsKey(b))
                    count.put(b,1);
                else{
                    count.put(b,count.get(b)+1);
                    }

                map.get(a).add(b);

                //신고 당한 사람이 k 번이상 => 정지하기
                if(count.get(b)>=k)
                        out.add(b);

            }

        }
        
        int[] answer = new int[id_list.length]; 
        int i=0;
        
       
       for(String id: id_list){
           
           //신고한 적 있으면
           if (map.containsKey(id)){
             
               // 신고한 id들 목록(중복 없음)
               Iterator <String> iterset = map.get(id).iterator();
               while(iterset.hasNext()){
                   // 신고한 id들 중에 정지된 id가 있으면
                   if(out.contains(iterset.next())){
                       answer[i]++;
                   }
               }
               
           }
           
           i++;
         
       }
        

        
        return answer;
    }
}
