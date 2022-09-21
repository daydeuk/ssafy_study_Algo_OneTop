import java.util.*;
import java.util.Map.Entry;
class PlayInfo implements Comparable<PlayInfo>{
    public int number;
    public int play;
    
     PlayInfo(int number,int play){
        this.number=number;
        this.play=play;
  
    }
    
    public int compareTo(PlayInfo o){
        // 재생횟수 큰 순으로 정렬 (내림차순)
        // 재생횟수가 같으면 고유번호 작은순으로 (오름차순)
        if(o.play==this.play)
            return this.number-o.number;
        return o.play-this.play;
    }
}

    
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        
        /*
         속한 노래가 많이 재생된 장르 -> 많이재생된 노래 -> 고유번호
         장르 배열과, 노래 재생 횟수 담긴 배열이 주어짐
         
         해시맵 1
         키 : 장르
         값 : 총 재생 횟수
         
         해시맵 2
         키 : 장르 
         값 : 우선순위 큐 - 우선순위큐에는 재생횟수를 차례로 담는다 (정렬기준 : 내림차순)
         

        */
        
        
        HashMap <String,Integer> map1=new HashMap<String,Integer> ();
        HashMap <String, PriorityQueue> map2 = new HashMap<>();
        
        for (int i=0;i<genres.length;i++){
            String g = genres[i];
            // 맵1에 장르별로 재생횟수 누적해서 더하기
            map1.put(g, map1.getOrDefault(g,0)+plays[i]);
            
            //맵2에 장르별 우선순위큐에 재생횟수 더하기
            if(!map2.containsKey(g)){
                //초기 우선순위큐 셋팅
                map2.put(g,new PriorityQueue<PlayInfo>());
            }
            map2.get(g).offer(new PlayInfo(i,plays[i]));
            
            
        }

        
        List<String> genreList = new ArrayList<>(map1.keySet());
        
        // 누적 재생횟수 큰순으로 장르 정렬
        Collections.sort(genreList,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return map1.get(o2)-map1.get(o1);
			}
        	
        });
        
        // 장르별로 노래 두개 뽑기 (=우선순위 큐에서 두개뽑기)
        ArrayList<Integer> ans = new ArrayList<>();
        for(String genre:genreList) {
        	PriorityQueue<PlayInfo> pq = map2.get(genre);
        	
        	ans.add(pq.poll().number);
        	if(!pq.isEmpty())
        		ans.add(pq.poll().number);
        	
        }
        answer=new int[ans.size()];
        for (int i=0;i<ans.size();i++){
            answer[i]=ans.get(i);
        }

        return answer;
        
    }
}
