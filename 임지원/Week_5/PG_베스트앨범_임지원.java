import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        // 각 장를별 재생횟수 저장할 해시맵
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++) {
        	map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
    
        //  각 장르별 재생횟수 내림차순 정렬하기 위한 리스트
        ArrayList<String> topGenre = new ArrayList<>();
        for(String key : map.keySet()) {
        	topGenre.add(key);
        }
        
        // 각 장르별 재생횟수(value) 내림차순 정렬
        Collections.sort(topGenre, (o1, o2) -> map.get(o2) - map.get(o1));
        
        // 각 장르별 재생횟수가 가장 많은 두곡 담을 리스트
        ArrayList<Integer> rslt = new ArrayList<>();
        
        for(int i = 0; i < topGenre.size(); i++) {
        	String g = topGenre.get(i);
        	
        	// 해당 장르에서 play 횟수가 가장 큰 노래의 인덱스
        	int max = 0;
        	int idx1 = -1;
        	for(int j = 0; j < genres.length; j++) {
        		if(g.equals(genres[j]) && plays[j] > max) {
        			max = plays[j];
        			idx1 = j;
        		}
        	}
        	
        	// 해당 장르에서 play 횟수가 두번째로 큰 노래의 인덱스
        	max = 0;
        	int idx2 = -1;           	
        	for(int j = 0; j < genres.length; j++) {
        		if(g.equals(genres[j]) && plays[j] > max && j != idx1) {
        			max = plays[j];
        			idx2 = j;
        		}
        	} 
        	
        	rslt.add(idx1);
        	// 장르 당 하나의 노래만 있는 경우
        	if(idx2 >= 0) rslt.add(idx2);
        	
        }
        
        // 리스트 rslt를 배열 answer로 변환
        answer = new int[rslt.size()];
        for(int i = 0; i < rslt.size(); i++) {
        	answer[i] = rslt.get(i);
        }
        
		return answer;  
    }
}