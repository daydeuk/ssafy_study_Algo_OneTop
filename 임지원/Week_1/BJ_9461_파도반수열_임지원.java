package week01;

import java.util.Scanner;

public class BJ_9461_파도반수열_임지원 {
/*
 * 파도반 수열
 * 
 * 1, 1, 1, 2, 2, 3, 4, 5, 7, 9
 * P(1) = 1
 * P(2) = 1
 * P(3) = 1
 * P(3) = 2 = (1+1) (x)
 * P(4) = 2 = (1+1)
 * P(5) = 3 = 1+2
 * P(6) = 4 = 2+2
 * P(7) = 5 = 2+3
 * P(8) = 7 = 3+4
 * 
 * 파도반 수열 P(N) = 나선에 있는 정삼각형의 변의 길이
 * p[n]의 최댓값이 int 초과(약20억)하므로 long타입 배열로 선언해야 
 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int[] p = new int[101];		// 파도반 수열 P(N) = 나선에 있는 정삼각형의 변의 길이
	
		p[1] = 1;
		p[2] = 1;
		p[3] = 1;		
				
		for (int i = 4; i < 101; i++) {
			p[i] = p[i-3] + p[i-2];
		}
		
		for (int i = 0; i < tc; i++) {
			int n = sc.nextInt();		// 1 ≤ N ≤ 100
			System.out.println(p[n]);
		}
		
	}

}
