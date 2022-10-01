import java.util.*;

/**
 * 주차요금계산
 * 2022_KAKAO_BLIND_RECRUITMENT
 * 
 * @author djunnni
 */
class 주차요금계산 {
    class Car implements Comparable<Car> {
        String num;
        int cost;

        public Car(String num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "[num : " + num + ", cost : " + cost + "]";
        }

        @Override
        public int compareTo(Car o1) {
            return num.compareTo(o1.num);
        }
    }

    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> costMap = new HashMap();
        HashMap<String, Integer> accumulMap = new HashMap();
        HashMap<String, Integer> inTimeMap = new HashMap();
        for (String record : records) {
            String[] data = record.split(" ");

            int time = parseTime(data[0]);
            String carNum = data[1];
            boolean isIn = parseIn(data[2]);

            if (isIn) {
                costMap.put(carNum, 0); // 기초요금 0원 세팅해두기
                inTimeMap.put(carNum, time); // 진입시간 저장
            } else {
                // 누적 : 누적 + (퇴장시간 - 진입시간)
                accumulMap.put(carNum, accumulMap.getOrDefault(carNum, 0) + time - inTimeMap.get(carNum));
                inTimeMap.remove(carNum);
            }
        }

        // 퇴장안한 차량들 정리
        Iterator iter = inTimeMap.keySet().iterator();
        while (iter.hasNext()) {
            String carNum = (String) iter.next();
            accumulMap.put(carNum, accumulMap.getOrDefault(carNum, 0) + parseTime("23:59") - inTimeMap.get(carNum));
        }

        iter = accumulMap.keySet().iterator();

        while (iter.hasNext()) {
            String carNum = (String) iter.next();
            int time = accumulMap.get(carNum);

            // 기본시간보다 같거나 작을 경우 기본요금 세팅
            int addFee = 0;
            if (time > fees[0]) {
                addFee = ((int) Math.ceil((time - fees[0]) / (double) fees[2])) * fees[3];
            }

            costMap.put(carNum, fees[1] + addFee);
        }

        ArrayList<Car> list = new ArrayList();
        iter = costMap.keySet().iterator();
        while (iter.hasNext()) {
            String carNum = (String) iter.next();
            list.add(new Car(carNum, costMap.get(carNum)));
        }

        Collections.sort(list);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).cost;
        }
        return answer;
    }

    public int parseTime(String time) {
        String data[] = time.split(":");

        int hour = Integer.parseInt(data[0]);
        int minute = Integer.parseInt(data[1]);

        return hour * 60 + minute;
    }

    public boolean parseIn(String bool) {
        return "IN".equals(bool);
    }
}