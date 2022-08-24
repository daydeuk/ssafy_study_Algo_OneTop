import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 수영장
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq
 * @author 허운
 *
 */

// 달을 기준으로 dfs로 회원권사용의 경우마다 합을 구하고 비교해서 최솟값을 구한다
public class SW_1952_수영장_윤호운 {
	static int[] plan = new int[13];
	static int d,m_1,m_3,y;
	static boolean[] visited = new boolean[13];
	static int ans;
	
	public static void dfs(int month,int sum) {
		//이번달이 12월이 넘어가면 최종금액을 ans와 비교한후 탈출!
		if(month>12) {
			if(sum<ans)
				ans=sum;
			return;
		}
		//sum이 ans보다 커지면 더 재귀 돌 필요없음 탈출!
		if(sum>ans) {
			return;
		}

		//요번달에
		//1일권사용하는 경우
		dfs(month+1,sum+(d*plan[month]));
		//한달권사용하는경우
		dfs(month+1,sum+m_1);
		//세달권사용하는경우
		dfs(month+3,sum+m_3);

	}
	public static void main(String[] args) throws IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringBuilder sb = new StringBuilder();
	      int tc = Integer.parseInt(br.readLine());
	      for(int t=1;t<=tc;t++) {
	    	  StringTokenizer st = new StringTokenizer(br.readLine());
	    	  d = Integer.parseInt(st.nextToken());
	    	  m_1 = Integer.parseInt(st.nextToken());
	    	  m_3 = Integer.parseInt(st.nextToken());
	    	  y = Integer.parseInt(st.nextToken());
	    	  
	    	  st = new StringTokenizer(br.readLine());
	    	  //입력
	    	  for(int i=1;i<13;i++) {
	    		  plan[i]=Integer.parseInt(st.nextToken());
	    	  }
	    	  //일단 ans에 1년회원권가격으로 초기화
	    	  ans =y;
	    	  dfs(1,0); //dfs(현재달,누적금액) ->현재달:1월,누적금액:0 로 시작
		      sb.append("#"+t+" "+ans+"\n");
	      }

	      System.out.println(sb);
	          

	}
}
