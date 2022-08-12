
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14888_연산자끼워넣기_윤호운 {
	static int[] arr;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void solve(int[] output) {
		int  result=arr[0];
		int idx=0;
		for(int i=1;i<arr.length;i++) {
			int oper = output[idx++];
			switch(oper) {
			case 0: //+
				result+=arr[i];
				break;
			case 1: //-
				result-=arr[i];
				break;
			case 2: //*
				result*=arr[i];
				break;
			case 3: // 나누깅
				result/=arr[i];
				break;
			}
		}
		if(result>max)
			max=result;
		if(result<min)
			min=result;
	}
	//모든 연산자 순서 구하기 => 순열
	public static void perm(int[] opers,int[]output,boolean[] visited, int depth,int n) {
		if(depth==n) {
		solve(output);	
		return;
		}
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				output[depth]=opers[i];
				perm(opers,output,visited,depth+1,n);
				visited[i]=false;
			}
		}
	}
	public static void main(String[] args) throws Exception {
		
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		//숫자 넣을 배열
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i =0 ; i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//연산자 넣을 
		// + - x /
		int[] opers = new int[n-1];
		int idx=0;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			int cnt=Integer.parseInt(st.nextToken());
			for(int j=0;j<cnt;j++) {
				opers[idx++]=i;
			}
		}
		perm(opers,new int[n-1],new boolean[n-1], 0,n-1);
		System.out.println(max+"\n"+min);
}
}
