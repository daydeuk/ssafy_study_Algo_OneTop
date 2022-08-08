import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_9461_파도반수열_신대득 {
	/**
	 * 피보나치 수열 처럼 접근했다. N= (N-3) + (N-2) 라는 점화식으로
	 * 동적 계획법 (dp 를 사용해서 연산한 결과를 저장하면서 진행 하도록 함)
	 */
	public static long[] arr;

	public static long pado_fibo(int N) {
		if (N <= 3) {
			return 1;
		} else {
			if (arr[N] > 0) { // 이미 구했던 값이면 그대로 리턴
				return arr[N];
			}
			arr[N] = pado_fibo(N - 3) + pado_fibo(N - 2); // 구하지 못한 값이면 재귀로 구해내서 arr에 저장
			return arr[N]; // arr 반환
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		arr = new long[101]; // N의 범위 1~ 100
		int TC = Integer.parseInt(in.readLine());
		// 인덱스 1, 2, 3은 모두 1
		arr[1] = 1;
		arr[2] = 1;
		arr[3] = 1;
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(in.readLine());
			long result = pado_fibo(N);
			System.out.printf("%d\n", result);
		}

	}
}
