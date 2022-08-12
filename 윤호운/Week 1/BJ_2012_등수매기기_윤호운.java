package ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 등수 매기기
 * https://www.acmicpc.net/problem/2012
 * @author HOWOON
 *
 */
public class BJ_2012_등수매기기_윤호운 {
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		long[] arr = new long[n+1]; //예상등수 목록
		for(int i=1;i<=n;i++) {
			arr[i]=Integer.parseInt(br.readLine()); 
		}
		Arrays.sort(arr); //오름차순 정렬 => index가 실제등수라고 하면, 예상등수에 가까운 순대로 정렬가능-> 불만도 최소
		long ans=0; //500,000 * 500,000=... 
		for(int i=1;i<=n;i++) {
			ans+=Math.abs(arr[i]-i); //불만도 합 구하기
		}
		System.out.println(ans);
	  
	}
}
