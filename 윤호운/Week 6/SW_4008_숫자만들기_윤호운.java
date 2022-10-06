import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4008_숫자만들기_윤호운 {
	static int n;
	static int[] num;
	static int max ;
	static int min ;
	
	public static int solve(int[] output) {
		int ans = num[0];
		for(int i=0;i<n-1;i++) {
			// + - * /
			switch(output[i]) {
			
			case 0:
				ans+=num[i+1];
				break;
			case 1:
				ans-=num[i+1];
				break;
			case 2:
				ans*=num[i+1];
				break;
			case 3:
				ans/=num[i+1];
				break;
			}
		}
		return ans;
	}
	//중복순열
	public static void perm(int[] output, int depth,int[] used) {
		if(depth==n-1) {
			int ans = solve(output);
			min = Math.min(ans, min);
			max = Math.max(ans, max);
			return;
		}
		
		// + - * /
		for(int i=0;i<4;i++) {
			if(used[i]>0) {
				output[depth] = i;
				used[i]--;
				perm(output,depth+1,used);
				used[i]++;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		//Testcase 
		int tc=Integer.parseInt(br.readLine());
	    StringBuffer sb=new StringBuffer();
	    // 연산자 우선순위 고려 X
	    // 왼쪽부터 오른쪽으로
	    for(int t=1;t<=tc;t++){
	    	n = Integer.parseInt(br.readLine());
	    	int[] oper = new int[4];
	    	num = new int[n];
	    	max = Integer.MIN_VALUE;
	    	min = Integer.MAX_VALUE;
	    	
	    	StringTokenizer st=new StringTokenizer( br.readLine());
	    	// + - * /
	    	for(int i=0;i<4;i++) {
	    		oper[i]=Integer.parseInt(st.nextToken());
	    	}
	    	st=new StringTokenizer( br.readLine());
	    	for(int i=0;i<n;i++) {
	    		num[i]=Integer.parseInt(st.nextToken());
	    	}
	    	
	    	perm(new int[n-1],0,oper);

	    	int answer = max-min;
	    	sb.append("#"+t+" "+answer+"\n");
	    }
	    
	    System.out.println(sb);
	   
	    

	}
}
