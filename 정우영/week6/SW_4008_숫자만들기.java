import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	static int [] number,operation,op_num;
	static int N;
	static String s;
	static int min;
	static int max=Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		int tc= Integer.parseInt(in.readLine());
		for(int t=0;t<tc;t++) {
			N=Integer.parseInt(in.readLine());
			StringTokenizer st= new StringTokenizer(in.readLine());
			op_num=new int[4];
			for(int i=0;i<4;i++) {
				op_num[i]=Integer.parseInt(st.nextToken());
			}
			
			min=Integer.MAX_VALUE;
			max=Integer.MIN_VALUE;
			operation=new int[N-1];
			
			number = new int[N];
			st=new StringTokenizer(in.readLine());
			for(int i=0;i<N;i++) {
				number[i]=Integer.parseInt(st.nextToken());
			}
			
			perm(0);
			System.out.println("#"+(t+1)+" "+(max-min));
		}
	}
	static void perm(int d) {
		if(d==N-1) {
			int ans=cal();
//			for(int i=0;i<N-1;i++) {
//				System.out.print(operation[i]+" ");
//			}
//			System.out.println();
			max=Math.max(ans, max);
			min=Math.min(ans, min);
			return;
		}
		for(int i=0;i<4;i++) {
			if(op_num[i]>0) {
			operation[d]=i;
			op_num[i]--;
			perm(d+1);
			op_num[i]++;
			}
		}
	}
	static int cal() {
		int ans=number[0];
		
		for(int i=0;i<N-1;i++) {
			switch(operation[i]) {
			case 0:
				ans+=number[i+1];
				break;
			case 1:
				ans-=number[i+1];
				break;
			case 2:
				ans*=number[i+1];
				break;
			case 3:
				ans/=number[i+1];
				break;	
			}
		}
		return ans;
	}
	

}
