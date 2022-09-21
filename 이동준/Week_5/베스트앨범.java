import java.lang.Comparable;
import java.util.*;

class Solution {
    class Song implements Comparable<Song> {
        String genre;
        int no;
        int play;
        
        public Song(int no, String genre, int play) {
            this.no = no;
            this.genre = genre;
            this.play = play;
        }
        
        @Override
        public int compareTo(Song s1) {
            return play == s1.play ? Integer.compare(no, s1.no) : Integer.compare(s1.play, play);
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap();
        HashMap<String, List<Song>> songMap = new HashMap();
        
        for(int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
            List<Song> list = songMap.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Song(i, genres[i], plays[i]));
            songMap.put(genres[i], list);
        }
        
        // 장르별 정렬(속한 노래가 많이 재생된 순)
        List<String> keyList = new ArrayList<>(map.keySet());
        keyList.sort((s1, s2) -> map.get(s2).compareTo(map.get(s1)));
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(String key : keyList) {
            List<Song> l = songMap.get(key);
            Collections.sort(l);
            
            for(int i = 0; i < 2; i++) {
                if(l.size() <= i) {
                    continue;
                }
                answer.add(l.get(i).no);
                
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}