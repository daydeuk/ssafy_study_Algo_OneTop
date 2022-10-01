import java.util.*;

/**
 * 신고결과받기
 * 2022_KAKAO_BLIND_RECRUITMENT
 * 
 * @author djunnni
 */
class 신고결과받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 신고메일 발송 count
        int[] answer = new int[id_list.length];
        // 메일 발송을 위한 index 저장
        HashMap<String, Integer> sendMailMap = new HashMap();
        for (int i = 0; i < id_list.length; i++) {
            sendMailMap.put(id_list[i], i);
        }

        // 신고맵 추가
        HashMap<String, Set<String>> singoMap = new HashMap();
        // 신고수 0으로 미리 세팅
        for (String id : id_list) {
            singoMap.put(id, new HashSet<>());
        }

        // 신고자 처리
        for (String r : report) {
            String[] data = r.split(" ");
            // data[0]가 data[1]을 신고했다.
            Set<String> set = singoMap.get(data[1]);
            set.add(data[0]);
        }

        Iterator iter = singoMap.keySet().iterator();
        while (iter.hasNext()) {
            Set<String> set = singoMap.get(iter.next());
            if (set.size() >= k) {
                Iterator _it = set.iterator();
                while (_it.hasNext()) {
                    answer[sendMailMap.get(_it.next())]++;
                }
            }
        }

        return answer;
    }
}