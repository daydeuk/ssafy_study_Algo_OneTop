import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_1931_회의실배정_신대득 {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][2]; // 0은 시작시간, 1은 끝 시간
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			map[n][0] = Integer.parseInt(st.nextToken());
			map[n][1] = Integer.parseInt(st.nextToken());
		}
		// 끝나는 시간을 기준으로 정렬.
		Arrays.sort(map, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				}
				return Integer.compare(o1[1], o2[1]);
			}
		});
		int count = 0;
		int endTime = -1;
		for (int i = 0; i < N; i++) {
			// 아직 안끝났으면 continue
			if (map[i][0] < endTime) {
				continue;
			} else { // 끝났으면 다음 회의로
				count++;
				endTime = map[i][1];
			}
		}
		System.out.printf("%d", count);
	}

}
