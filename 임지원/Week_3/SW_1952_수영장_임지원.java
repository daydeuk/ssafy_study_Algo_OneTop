package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 수영장
 * : 가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾고, 그 비용을 정답으로 출력
 * 
 * 풀이
 * : DP
 * : 월 단위로 그때 가장 적은 비용으로 이용하는 법 저장 
 */
public class SW_1952_수영장_임지원 {

	static int[] m = new int[13];
	static int day, month1, month3, year;
	
	public static void main(String[] args) throws IOException {

	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringTokenizer st;
	      StringBuilder sb = new StringBuilder();
	      
	      int tc = Integer.parseInt(br.readLine());
	      
	      for(int t = 1; t <= tc; t++) {
	    	  
	    	  // 1일 이용권의 요금, 1달 이용권의 요금, 3달 이용권의 요금, 1년 이용권의 요금
	    	  st = new StringTokenizer(br.readLine());
	    	  day = Integer.parseInt(st.nextToken());
	    	  month1 = Integer.parseInt(st.nextToken());
	    	  month3 = Integer.parseInt(st.nextToken());
	    	  year = Integer.parseInt(st.nextToken());
	    	  
	    	  st = new StringTokenizer(br.readLine());
	    	  
	    	  // 1월부터 12월까지의 이용 계획 입력받기 
	    	  for(int i = 1; i < 13; i++) {
	    		  m[i]=Integer.parseInt(st.nextToken());
	    	  }
	    	  
	    	  // 월 단위로 그때 가장 적은 비용으로 이용하는 법
	    	  int cost[] = new int[13];
	    	  
	    	  /*
	    	   * 1월 : (1월이용계획 * 1일이용금액)  vs 1달권
	    	   * 2월 : (1월까지 비용  + 2월이용계획*1일이용금액)  vs 1월까지 비용 + 1달권
	    	   * 3월 ~ : (직적달까지 비용 + 현재월이용계획+1일이용금액) vs (전월까지 비용 + 1달권) vs (3달전까지 비용 + 3달권)
	    	   * 12월 : 위3가지 vs 1년권
	    	   */
	    	  for (int i = 1; i < 13; i++) {
				int p1 = day * m[i] + cost[i-1];
				int p2 = month1 + cost[i-1];
				int p3 = Integer.MAX_VALUE;
				int p4 = Integer.MAX_VALUE;
				
				if(i >= 3) {
					p3 = month3 + cost[i-3];
				}
				if(i == 12) {
					p4 = year;
				}
				
				cost[i] = Math.min(p1, Math.min(p2, Math.min(p3, p4)));
			}
	    	  
	    	  System.out.println("#" + t + " " + cost[12]);
	      }
		
	}

}
