package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 입력되는 온도의 수열에서 연속적인 K일의 온도의 합이 최대가 되는 값 출력

public class BJ_2559_수열_임지원 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    int n = Integer.parseInt(st.nextToken());	// 온도를 측정한 전체 날짜의 수
	    int k = Integer.parseInt(st.nextToken());	// 연속적인 K일
	    
	    int[] temp = new int[n];
	    int ans = Integer.MIN_VALUE;	    
	    
	    st = new StringTokenizer(br.readLine());
	    for (int i = 0; i < n; i++) {
			temp[i] = Integer.parseInt(st.nextToken());
		}
	    
	    for (int i = 0; i < n-k+1; i++) {
	    	int sum = 0;
			for (int j = i; j < i+k; j++) {	// K일의 온도의 합
				sum += temp[j];
			}
			ans = ans > sum ? ans : sum;
		}
	    
	    System.out.println(ans);
	}
	
	

}
