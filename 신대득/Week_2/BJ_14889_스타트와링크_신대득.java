import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 결국 조합을 이용해서 순서에 상관없이 2/N 팀을 뽑아 그 팀들의 점수의 합을 각각 구해 차를 구한다.
 */
public class BJ_14889_스타트와링크_신대득 {

	public static int[][] map; // 능력치를 입력할 map
	public static int[][] scoreMap;
	public static int N;
	public static boolean[] selected;
	public static int	minResult;

	public static void combination(int cur, int start) {
		// 스타트 팀이 선택한 문자의 개수가 N/2이 되면
		if (cur == N / 2) {
			int sumA=0;
			int sumB=0;
			for (int r=1;r<=N;r++) {
				for(int c=r+1;c<=N;c++) {
					if(selected[r] && selected[c]) {
						sumA += scoreMap[r][c];
					}
					if(!selected[r] && !selected[c]) {
						sumB += scoreMap[r][c];
					}
				}
			}
			int result = Math.abs(sumA-sumB);
			if(result<minResult)
				minResult=result;
			return;
		}
		// N/2개를 선택하는 재귀
		for (int i=start; i<=N;i++) {
			selected[i]=true;
			combination(cur+1, i+1);
			selected[i]=false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N + 1][N + 1];
		scoreMap = new int[N + 1][N + 1]; // ij ji를 합한 점수를 저장할 배열
		for (int r = 1; r <= N; r++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 점수의 합 ( 우상단만 계산 )
		for (int r = 1; r <= N; r++) {
			for (int c = r + 1; c <= N; c++) {
				scoreMap[r][c] = map[r][c] + map[c][r];
			}
		}

		// 조합을 통해 N/2 팀 뽑기 N C (N/2)
		minResult=Integer.MAX_VALUE;
		selected = new boolean[N+1]; // 스타트팀이 선택할 문자는 true
		combination(0, 1); // 조합 시작
		System.out.printf("%d", minResult);
	}

}
