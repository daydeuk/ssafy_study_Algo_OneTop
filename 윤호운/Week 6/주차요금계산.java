import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
            Map<String,String> map = new HashMap<String,String>(); //차번호, 입차시간
	        Map<String,Integer> ans_map = new HashMap<String,Integer>(); //차번호, 주차요금
	        for(String rec : records){
	            StringTokenizer st = new StringTokenizer(rec);
	            String time = st.nextToken();
	            String car = st.nextToken();
	            String state = st.nextToken();
	            
	            //차가없다면
	            if(state.equals("IN") && !map.containsKey(car)){
	                map.put(car,time);
	            }
	            //입차한상태라면
	            else{
	                String in_time = map.get(car);
	                
	                int in_hour = Integer.parseInt(in_time.substring(0,2));
	                int in_min = Integer.parseInt(in_time.substring(3));
	                
	                int out_hour = Integer.parseInt(time.substring(0,2));
	                int out_min = Integer.parseInt(time.substring(3));
	                
                    //주차시간
	                int total_min = (out_hour-(in_hour+1))*60 + (60-in_min) + out_min;
	                
	                
	                if(ans_map.containsKey(car)){
                        //누적주차시간구하기
	                    int sum = ans_map.get(car)+total_min ;
	                    ans_map.put(car,sum);
	                }
	                else{
	                    ans_map.put(car,total_min );
	                }
	                
	                map.remove(car);
	                
	                
	            }
	        }
	        
	        // 출차 안한 차 처리
	        for(String car : map.keySet()){
	            
	             String in_time = map.get(car);
	                
	                int in_hour = Integer.parseInt(in_time.substring(0,2));
	                int in_min = Integer.parseInt(in_time.substring(3));
	                
	                int out_hour = 23;
	                int out_min = 59;
	                
	                int total_min = (out_hour-(in_hour+1))*60 + (60-in_min) + out_min;
	                
	                
	                if(ans_map.containsKey(car)){
	                    int sum = ans_map.get(car)+total_min ;
	                    ans_map.put(car,sum);
	                }
	                else{
	                    ans_map.put(car,total_min );
	                }
	                
	        }
	        
	        // 누적시간 => 주차요금 계산
	        for(String car : ans_map.keySet()) {
	        	int total_min = ans_map.get(car);
	        	
                int pay = 0;

                if(total_min<= fees[0]){
                    pay = fees[1];
                } else{
                    pay = fees[1]+((int)(Math.ceil((total_min-fees[0])/(double)fees[2])) * fees[3]);
                   
                }
                ans_map.put(car, pay);
	        }
	        
	        // 차번호 순으로 정렬
	        Set carSet = ans_map.keySet();
	        List<String> carList = new ArrayList(carSet);
	        Collections.sort(carList);
	        
	        int[] answer = new int[carList.size()];
	        
           // 정답 할당
	        int i=0;
	        for(String car : carList){
	            answer[i++] = ans_map.get(car);
	        }
	        

	        return answer;
    }
}
