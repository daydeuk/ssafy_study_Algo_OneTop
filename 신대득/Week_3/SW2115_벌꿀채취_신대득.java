import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2115_벌꿀채취_신대득 {

	static int N, M, C;
	static int[] person1;
	static int[] person2;
	static int[][] honey;
	static int maxResult; // 제일 많은 벌꿀을 담은 양
	static int maxHoney; // 두 사람의 꿀을 합한 최대의 꿀

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // input
		StringBuilder sb = new StringBuilder(); // output 
		int TC = Integer.parseInt(in.readLine()); // 테케 입력
		for (int tc = 1; tc <= TC; tc++) { // 테케만큼 반복
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 벌통의 크기
			M = Integer.parseInt(st.nextToken()); // 벌통의 개수
			C = Integer.parseInt(st.nextToken()); // 한사람이 채취할 수 있는 꿀의 최대 양
			person1 = new int[M]; // 사람 1이 선택 할 꿀통 M개
			person2 = new int[M]; // 사람 2가 선택 할 꿀통 M개
			// 꿀통 배열 입력
			honey = new int[N + 1][N + 1];
			for (int r = 1; r <= N; r++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int c = 1; c <= N; c++) {
					honey[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			// 사람이 1이 선택했던 꿀통인지 확인하는 배열
			boolean[][] selected = new boolean[N + 1][N + 1];
			maxHoney = 0;
			selectHoney(0, selected); // 꿀통 선택하러 떠남
			sb.append("#").append(tc).append(" ").append(maxHoney).append("\n");
		}
		System.out.printf(sb.toString());
	}

	// 꿀통 선택 cur 0이면 사람1 선택, cur 1이면 사람2 서냍ㄱ
	public static void selectHoney(int cur, boolean[][] selected) {
		if (cur == 2) {
			boolean[] subset = new boolean[M];
			int resultSum = 0;
			// 사람 1의 꿀통 중 C를 넘지 않은 최대의 값 계산
			maxResult = 0;
			culc(person1, subset, 0, 0, 0);
			resultSum += maxResult;
			
			// 사람 2의 꿀통 중 C를 넘지 않은 최대의 값 계산
			subset = new boolean[M];
			maxResult = 0;
			culc(person2, subset, 0, 0, 0);
			
			// 사람 1, 2의 결과의 합의 최대를 따로 저장
			resultSum += maxResult;
			maxHoney = Math.max(maxHoney, resultSum);
			return;
		}
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N - M + 1; c++) {
				// 만약 사람 1이 선택 한 꿀통인지 검사하는 부분
				boolean flag=false;
				for(int i=0;i<M;i++) {
				if (selected[r][c+i] == true)
					flag=true;
				}
				if(flag)
					continue;
				
				// 꿀통 선택
				for (int i = 0; i < M; i++) {
					selected[r][c + i] = true;
					if (cur == 0)
						person1[i] = honey[r][c + i];
					if (cur == 1)
						person2[i] = honey[r][c + i];
				}
				
				// 선택 한 후 다음 사람 꿀통을 선택하러 재귀
				selectHoney(cur + 1, selected);
				
				// 꿀통 선택 해제
				for (int i = 0; i < M; i++) {
					selected[r][c + i] = false;
				}
			}
		}
	}

	// 선택한 꿀통 중 담을 수 있는 꿀의 양
	// 부분집합을 이용해서 꿀통 중 선택
	public static void culc(int[] person, boolean[] subset, int cur, int sum, int start) {
		int result = 0;
		for (int i = 0; i < M; i++) {
			if (subset[i]) { // 선택된 꿀통이면
				result += person[i] * person[i]; // 제곱한 값을 더해준다.
			}
		}
		maxResult = Math.max(maxResult, result);
		
		// 만약 M개 만큼 선택했다면 return
		if (cur == M) {
			return;
		}

		// 부분집합을 구하는 부분
		for (int i = start; i < M; i++) {
			if (subset[i] == true)
				continue;
			if (sum + person[i] <= C) {
				subset[i] = true;
				culc(person, subset, cur + 1, sum + person[i], i + 1);
				subset[i] = false;
			}
		}
	}

}
