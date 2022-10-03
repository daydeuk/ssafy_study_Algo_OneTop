import java.util.HashMap;
import java.util.Map;
class Solution {
    public int[] solution(String[] id, String[] report, int k) {
        Map<String,Integer> map= new HashMap<>();
	for(int i=0;i<id.length;i++) {
		map.put(id[i], i);
	}
	
	int [][] arr=new int[id.length][id.length]; //신고 배열
	for(int i=0;i<report.length;i++) {
		String[] s=report[i].split(" ");
		int rep=map.get(s[0]);                  //신고자
		int user=map.get(s[1]);                 //신고받은 유저
		arr[rep][user]=1;
	}
	int [] recnt=new int[id.length];            //신고받은 횟수 저장
	for(int i=0;i<id.length;i++) {
		int cnt=0;
		for(int j=0;j<id.length;j++) {
			if(arr[j][i]==1) {
				cnt++;
			}
		}
		recnt[i]=cnt;
		
	}
	int [] answer = new int [id.length];
	for(int i=0;i<id.length;i++) {
		for(int j=0;j<id.length;j++) {
			if(recnt[j]>=k) {           // 신고 받은 횟수가 k 이상
				if(arr[i][j]>0) {       // 신고자 일 경우
					answer[i]++;
				}
			}
		}
	}
       
        return answer;
    }
}