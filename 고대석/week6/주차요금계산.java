import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        // 출입 기록
        Map<String, String> parkRecord = new HashMap<>();
        
        // 주차 시간 기록
        Map<String, Integer> timeRecord = new HashMap<>();
        
        for(int i = 0; i < records.length; i++) {
            String time = records[i].split(" ")[0];
            String carNum = records[i].split(" ")[1];
            String inOut = records[i].split(" ")[2];
            
            if(inOut.equals("IN")) { // 입차
                parkRecord.put(carNum, time);
            } else { // 출차
                // 입차 시간
                String inTime = parkRecord.get(carNum);
                // 출차 시간
                String outTime = time;
                
                int inHour = Integer.parseInt(inTime.split(":")[0]);                
                int inMin = Integer.parseInt(inTime.split(":")[1]);
                int in = inHour * 60 + inMin;
                
                int outHour = Integer.parseInt(outTime.split(":")[0]);                
                int outMin = Integer.parseInt(outTime.split(":")[1]);
                int out = outHour * 60 + outMin;
                
                int parkTime = 0;
                
                if(timeRecord.containsKey(carNum)) { // 주차를 한 적이 있으면
                    parkTime = timeRecord.get(carNum); // 전의 주차 시간
                    timeRecord.put(carNum, parkTime + (out - in));
                } else { // 주차를 한 적이 없으면
                    parkTime = out - in;
                    timeRecord.put(carNum, parkTime);
                }
                
                parkRecord.remove(carNum); // 출차
            }
        }
        
        // 입차된 후에 출차된 내역이 없다면
        for(String key : parkRecord.keySet()) {
            String inTime = parkRecord.get(key);

            int inHour = Integer.parseInt(inTime.split(":")[0]);                
            int inMin = Integer.parseInt(inTime.split(":")[1]);
            int in = inHour * 60 + inMin;
            int out = 23 * 60 + 59; // 23:59에 출차

            int parkTime = 0;

            if(timeRecord.containsKey(key)) { // 주차를 한 적이 있으면
                parkTime = timeRecord.get(key); // 전의 주차 시간
                timeRecord.put(key, parkTime + (out - in));
            } else { // 주차를 한 적이 없으면
                parkTime = out - in;
                timeRecord.put(key, parkTime);
            }
            /*
            * 동시 수정 * : 프로그래밍에서 동시수정은 다른 작업이 이미 실행중일 때, 동시에 개체를 수정하는 것을 의미한다.
            자바에서는 다른 스레드가 컬렉션을 반복할 때, 컬렉션을 수정하면 일부 Iterator에서 이 동작을 감지해 ConcurrentModificationException 예외가 발생한다.
            */
            // parkRecord.remove(key); // 출차 <-- 
        }
        
        for(String key : timeRecord.keySet()) {
            int value = timeRecord.get(key);
            // 기본요금
            int fee;
            if(value <= fees[0]) {
                fee = fees[1];
            } else {
                // 단위 시간으로 나누어 떨어지지 않으면 올림
                fee = fees[1] + (int)Math.ceil((value - fees[0]) / (double)fees[2]) * fees[3];
            }
            timeRecord.put(key, fee);
        }
        
        // 차량 번호가 작은 자동차부터 정렬
        List<String> carList = new ArrayList<>(timeRecord.keySet());
        Collections.sort(carList);
        
        int[] answer = new int[timeRecord.size()];
        
        int index = 0;
        for (String key : carList) {
            answer[index++] = timeRecord.get(key);
        }
        
        return answer;
    }
}