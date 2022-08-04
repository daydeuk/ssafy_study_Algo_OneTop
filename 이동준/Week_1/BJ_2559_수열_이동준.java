import java.util.Scanner;

/**
 * BJ_2559_수열
 * 연속적인 정수가 들어간 배열이 주어진다.
 * 
 * 입력: 
 * 1. N: 온도를 측정한 전체 날짜 수, K: 합을 구하기 위한 연속날짜 수
 * 2. 배열에 들어갈 온도 데이터
 * 
 * 출력:
 * 온도의 수열에서 연속적인 K일의 온도 합이 최대가 되는 값을 구하라.
 * 
 * 풀이 방식:
 * 
 * 범위에 대한 값을 구하는 문제여서 누적 배열을 하나 만듭니다.
 * 그리고 N 배열의 크기만큼 사이즈 K단계로 읽어 MAX를 구합니다.
 * 
 * @author djunnni
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int arr[] = new int [N + 1];
		int accumulate[] = new int [N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
			accumulate[i] = accumulate[i - 1] + arr[i];
		}
		
		int answer = Integer.MIN_VALUE;
		
		int idx = 1;
		while(true) {
			int next = idx + K - 1;
			if(next > N) {
				break;
			}
			int sum = accumulate[next] - accumulate[idx - 1];
			if(sum > answer) {
				answer = sum;
			}
			idx++;
		}
		
		System.out.println(answer);
	}

}