import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9461_파도반수열_윤호운 {
public static void main(String[] args) throws IOException {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    StringBuffer sb = new StringBuffer();
	    int tc = Integer.parseInt(br.readLine());
	    
	    for(int t=0;t<tc;t++) {
	    	int num = Integer.parseInt(br.readLine());
	    	long[] dp = new long[101]; //메모리 초과 확인하기~ int말고 long으로
	    	// 첫번째 dp 값 초기화
	    	dp[1]=1;
	    	dp[2]=1;
	    	dp[3]=1;
	    	dp[4]=2;
	    	dp[5]=2;
	    	dp[6]=3;
	    	dp[7]=4;
	    	dp[8]=5;
	    	dp[9]=7;
	    	dp[10]=9;
	    	
	    	//11 부터는 수열 규칙이 일정하다 => dp 메모이제이션
	    	for(int i=11;i<101;i++) {
	    		dp[i]=dp[i-1]+dp[i-5];
	    	}
	    	
	    	sb.append(dp[num]+"\n");
	    }
	    System.out.println(sb);
	   
	}
}
