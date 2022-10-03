import java.util.*;

class Solution {
    static int end_time = 23 * 60 + 59;
        
    public int[] solution(int[] fees, String[] records) {
        StringTokenizer st;

        double d_time = fees[0];
        int d_fee = fees[1];
        int u_time = fees[2];
        int u_money = fees[3];

        // 차량의 소요시간
        Map<String, Integer> w_time = new HashMap<>();

        // 주차장 맵
        Map<String, Integer> p_lot = new HashMap<>();

        // 총 소모비용
        Map<String, Integer> total_fee = new TreeMap<>();

        for (String r : records) {
            st = new StringTokenizer(r);
            String[] time = st.nextToken().split(":");
            int h = Integer.parseInt(time[0]) * 60;
            int m = Integer.parseInt(time[1]);
            int total = h + m;

            String c_num = st.nextToken();
            String IO = st.nextToken();

            // 입차
            if (IO.equals("IN")) {
                p_lot.put(c_num, total);
            } else { // 출차
                // 주차 요금이 없는 경우
                if (w_time.get(c_num) == null) {
                    w_time.put(c_num, Math.abs(total - p_lot.get(c_num)));
                    p_lot.remove(c_num); // 파킹맵에서 제거
                } else { // 주차 요금이 있는 경우
                    w_time.put(c_num, w_time.get(c_num) + Math.abs(total - p_lot.get(c_num)));
                    p_lot.remove(c_num); // 파킹맵에서 제거
                }
            }

//            System.out.println(c_num + ", " + p_lot.get(c_num));
//            System.out.println(c_num + ", " + w_time.get(c_num));
//            System.out.println();
        }

        for (String c_num : p_lot.keySet()) {
            int left_time = Math.abs(p_lot.get(c_num) - end_time);

            if (w_time.get(c_num) == null) { // 주차 요금이 없는 경우
                w_time.put(c_num, left_time);
            } else { // 주차 요금이 있는 경우
                w_time.put(c_num, w_time.get(c_num) + left_time);
            }
        }

        for (String c_num : w_time.keySet()) {
            int get_fee = (int) (d_fee + (Math.ceil((w_time.get(c_num) - d_time) / u_time)) * u_money);
            if (get_fee <= d_fee) {
                total_fee.put(c_num, d_fee);
            } else {
                total_fee.put(c_num, get_fee);
            }
        }

        int[] result = new int[total_fee.size()];
        int idx = 0;
        
        for (Integer value : total_fee.values()) {
            result[idx++] = value;
        }
        
        return result;
    }
}
