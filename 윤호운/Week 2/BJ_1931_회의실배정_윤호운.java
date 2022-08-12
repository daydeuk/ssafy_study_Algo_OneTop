
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 회의실 배정
 * https://www.acmicpc.net/problem/1931
 * 겹치지 않게 회의실 사용할수있는 최대개수?!
 * => 끝나는 순으로 오름차순-> 시작시간 오름차순 정렬 하기
 * => 배열 처음부터 돌면서 이전회의 끝나는시간 < 지금회의 시작시간 인지 확인하면서 맞으면 개수+1
 * @author HOWOOON
 *
 */

// 회의시작시간, 회의 종료시간 남은 회의 클래스
class Meeting implements Comparable<Meeting> {
	int start;
	int finish;
	
	Meeting(int start,int finish){
		this.start=start;
		this.finish=finish;
	}
	
	//끝나는 순 오름차순정렬, 시작시간 오름차순 정렬
	@Override
	public int compareTo(Meeting o) {
		if(this.finish==o.finish)
			return this.start-o.start; // 시작시간이랑 끝나는 시간 같은 회의 처리해주기위해서 시작시간도 고려해줘야댐
		return this.finish-o.finish;
	}
	
	
}
public class BJ_1931_회의실배정_윤호운 {
	public static void main(String[] args) throws Exception {
			BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
			
			int n = Integer.parseInt(br.readLine());
			
			Meeting[] arr =new Meeting[n];
			
			for(int i =0 ; i<n;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[i]=new Meeting(a,b);
			}
			//회의들 끝나는 순으로 정렬
			Arrays.sort(arr);
			
			int ans=0;
			int f=-1;
			
			for(int i=0;i<n;i++) {
				//회의 시작시간이 이전 회의 끝나는시간보다 크거나 같은지 확인
				if(arr[i].start>=f) {
					ans++; //맞다면 회의개수+1
				    f = arr[i].finish; //회의끝나는 시간 업데이트
				}
				
			}
			
			System.out.println(ans);
	}
}
