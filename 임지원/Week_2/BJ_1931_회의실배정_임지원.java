package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 회의실배정
 * (시작시작, 끝나는시간)
 * 회의시간 겹치지 않게 회의실 사용 시 사용할 수 있는 회의 최대개수 
 * 
 * 정렬법
 *  - 끝나는시간 올림
 *  - 시작시간 올림
 */
public class BJ_1931_회의실배정_임지원 {

	static int ans= 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		// 정렬 : 우선순위큐  (1. 끝나는시간 빠른 순  , 2. 시작시간 빠른 순 )
		Queue<int[]> pq = new PriorityQueue<>( new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {		
					return Integer.compare(o1[0], o2[0]);
				}
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start =  Integer.parseInt(st.nextToken());
			int end =  Integer.parseInt(st.nextToken());
			pq.offer(new int[] {start, end});
		}
		
//		System.out.println("-----정렬된 회의실------");
//		while(!pq.isEmpty()) {			
//			System.out.println(Arrays.toString(pq.poll()));
//		}
//		
		System.out.println("------------------------");
		System.out.println("이용가능= " + pq.peek()[0] + "," + pq.peek()[1]);
		
		int endTime = pq.poll()[1];
		while(!pq.isEmpty()) {
			int[] next = pq.poll();
			if(endTime <= next[0]) {
				ans++;
				System.out.println("이용한 회의실 = " + Arrays.toString(pq.poll()));
				endTime = next[1];
			}
		}
		System.out.println(ans);
	}

}
