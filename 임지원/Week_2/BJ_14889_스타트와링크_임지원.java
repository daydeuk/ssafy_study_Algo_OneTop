package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 스타트와 링크
 * 팀의 능력치의 차이의 최솟값을 출력
 * 
 * N명 중 N/2명 조합으로 뽑기
 * 각 팀 능력치 더해주고 -> 능력치 차이 최솟값 출력
 * 
 * 팀1: 팀원 1,2 -> 능력치 = arr[1][2] + arr[2][1]
 * 팀2: 팀원 3,4 -> 능력치 = arr[3][4] + arr[4][3]
 * 
 */

public class BJ_14889_스타트와링크_임지원 {

	static int sum1, sum2;
	static int[][] map;
	static int n;
	static boolean[] picked;			
	static int ans = Integer.MAX_VALUE;
	static ArrayList<Integer> team1;	
	static ArrayList<Integer> team2;

	// 재료 뽑기 : 조합
	private static void comb(int pick, int cnt, int start) {

		// 재료 다 뽑은 경우
		if(pick == cnt) {						
			team1  = new ArrayList<>();
			team2  = new ArrayList<>();
			
			// 방문 팀과 방문x팀 나누기
			for (int i = 0; i < picked.length; i++) {
				if(picked[i]) {
					team1.add(i);
				}
				else {
					team2.add(i);
				}
			}
			
			sum();										// 각 팀 능력치 합 구하기
			int rslt = Math.abs(sum1-sum2);				// 두 팀의 능력치의 차이
			
			ans = rslt < ans ? rslt : ans;
		}
		else {
			for (int i = start; i < n; i++) {
				if(!picked[i]) {
					picked[i] = true;
					comb(pick, cnt+1, i+1);
					picked[i] = false;
				}
			}
		}
		
	}

	private static void sum() {
		sum1 = 0; sum2 = 0;
		
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				if(i != j) {
					sum1 += (map[team1.get(i)][team1.get(j)]);
					sum2 += (map[team2.get(i)][team2.get(j)]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
			
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		picked = new boolean[n];
			
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb( n/2, 0, 0);				//뽑아야하는 개수, 뽑은 수, 기준
			
		System.out.println(ans);
	}

}
