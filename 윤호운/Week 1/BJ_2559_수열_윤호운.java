
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class  BJ_2559_수열_윤호운 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    StringBuffer sb = new StringBuffer();
	    
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int n = Integer.parseInt(st.nextToken()); //온도 측정한 전체 날짜 수
	    int k = Integer.parseInt(st.nextToken()); // 연속적인 날짜 수
	    
	    st = new StringTokenizer(br.readLine());
	    int[] sum = new int[n+1];
	    for(int i=1;i<=n;i++) {
	    	sum[i]=sum[i-1]+Integer.parseInt(st.nextToken());
	    }
	    
	    int max =Integer.MIN_VALUE;
	    int ans =0;
	    for(int i=1;i<=n-k+1;i++) {
	    	ans = sum[i+k-1]-sum[i-1];
	    	if(ans>max)
	    		max=ans;
	    }
	    System.out.println(max);
//	    

	}
}