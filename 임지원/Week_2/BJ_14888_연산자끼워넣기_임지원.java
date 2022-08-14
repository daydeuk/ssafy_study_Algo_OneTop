package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 수 순서 그대로
 * 연산자들(+*-/)순서 조합하면서 
 * 결과가 가장최대인것 과 최소인것 출력 
 * 
 * 순열 벡트래킹
 * 
 * 연산법
 * - 연산자 우선순위 무시하고 앞에서부터 진행
 * - 나눗셈: 정수 나눗셈 -> 몫만 취하기 
 * 			음수 / 양수 : 음수를 양수로 바꾸고 결과의 몫을 음수로 바꾸기
 *
 */
public class BJ_14888_연산자끼워넣기_임지원 {

	static int n;
	static int[] num;				// 입력된 숫자 저장	
	static char[] op;				// 각 연산자 저장
	static int max = Integer.MIN_VALUE;		
	static int min = Integer.MAX_VALUE;
	static boolean[] check;			// op에 저장된 연산자 뽑음 여부
	static char[] answer;			// 뽑은 연사자 저장 배열
	
	// 연산자에 따라 계산 후 결과값 반환
	private static int calc(int n1, int n2, char op) {
		int rslt = 0;
		
		switch(op) {
			case '+':
				rslt = n1 + n2;
				break;
			case '-':
				rslt = n1 - n2;
				break;
			case '*':
				rslt = n1 * n2;
				break;
			case '/':
				if(n1 < 0) {
					n1 = -n1;
					rslt = n1 / n2;
					rslt = -rslt;			// *** 부호 붙이기
				}
				else {
					rslt = n1 / n2;
				}
				break;
				
		}
		
		return rslt;
	}
	
	private static void pickOp(int pick, int picked) {
		if(pick == picked) {		// 연산자 다 뽑은 경우			
			int rslt = calc(num[0], num[1], answer[0]);
			for (int i = 2; i < num.length; i++) {				// num.length까지 계산
				rslt = calc(rslt, num[i], answer[i-1]);			// answer[i-1]
			}
			
			max = rslt > max ? rslt : max;
			min = rslt < min ? rslt : min;
			
			return;
		}
		
		for (int i = 0; i < op.length; i++) {
			if(!check[i]) {						// 아직 방문안했으면
				check[i] = true;
				answer[picked] = op[i];
				pickOp(pick, picked+1);		
				check[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n];			// 입력된 숫자 저장할 배열
		op = new char[n-1];			// 연산자 저장할 배열
		check = new boolean[n-1];	// op에 저장된 연산자 뽑음 여부	
		answer = new char[n-1];		// 뽑은 연산자 저장할 배열
		
		// 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {			
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			char operator = 0;
			switch(i) {
				case 0:
					operator = '+';
					break;
				case 1:
					operator = '-';
					break;
				case 2:
					operator = '*';
					break;
				case 3:
					operator = '/';
					break;
			}
			for (int j = 0; j < cnt; j++) {
				op[idx++] = operator;
			}
		}
		
		// 순열 : 연산자 뽑기
		pickOp(n-1, 0);				// 뽑아야하는 연산자 개수, 뽑은 개수
		
		System.out.println(max + " \n" + min);		// 계산 결과 중 최댓값과 최소값을 출력
		
	}
}
