package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_모의_4008_숫자만들기 {

	static int N, oper[], operCnt[], num[], max, min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			operCnt = new int[4]; // 연산자 개수
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < operCnt.length; i++) {
				operCnt[i] = Integer.parseInt(st.nextToken());
			}
			
			num = new int[N]; // 수식에 사용되는 숫자
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			} // 입력 끝
			
			oper = new int[N-1]; // 숫자 사이에 들어갈 연산자
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			perm(0);
			
			sb.append("#" + tc + " " + (max - min) + "\n");
		}
		System.out.println(sb);
		
	}

	// 중복순열
	private static void perm(int n) {
		if(n == N-1) {
			int result = cal();
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(operCnt[i] > 0) {
				operCnt[i]--;
				oper[n] = i;
				perm(n+1);
				operCnt[i]++;
			}
		}
	}

	// 계산
	private static int cal() {
		int result = num[0]; // 맨 앞의 숫자
		int index = 1;
		
		// 모든 연산자들 계산
		for (int i = 0; i < N-1; i++) {
			switch(oper[i]) {
			case 0:
				result += num[index++];
				break;
			case 1:
				result -= num[index++];
				break;
			case 2:
				result *= num[index++];
				break;
			case 3:
				result /= num[index++];
				break;
			}
		}
		return result;
	}
}
