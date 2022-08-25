import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 부분 집합 문제
 * 1. subset을 이용하여 선택된 그룹, 선택되지 않은 그룹으로 나눔
 * 2. 탐색 알고리즘으로 이동을 할 것 인데, 부분집합으로 선택된 길로만 갈 수 있게 해서 => 구한 subset의 길이만큼이 될 수 있는지 판별
 * 3. subset의 길이 만큼 됐을때, 선택되지 않은 그룹도 2조건을 만족한다면 각각의 인구의 총합 계산하여 차이 계산.
 */
public class BJ_17471_게리맨더링_신대득 {

	static int N;
	static int[] personSize; // 각 지역의 인구수를 저장한 배열
	static List<Integer>[] arrayList; // 인접리스트 저장
	static int resultCount;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // input
		N = Integer.parseInt(in.readLine()); // 구역의 개수 입력
		personSize = new int[N + 1]; // 구역의 인구를 저장할 배열
		StringTokenizer st = new StringTokenizer(in.readLine(), " "); // 두번째 줄 입력
		arrayList = new ArrayList[N + 1]; // 인접리스트 배열 할당
		for (int n = 1; n <= N; n++) {
			arrayList[n] = new ArrayList<>(); // 해당 구역 인접리스트 할당
			personSize[n] = Integer.parseInt(st.nextToken()); // 각 구역의 인구 입력
		}
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(in.readLine(), " ");// 3~ n+2 번째 줄 입력
			int inputSize = Integer.parseInt(st.nextToken()); // 인접한 구역 사이즈 입력
			for (int i = 0; i < inputSize; i++) {
				int input = Integer.parseInt(st.nextToken()); // 인접한 구역의 번호 입력
				// 인접리스트에 추가
				arrayList[n].add(input);
			}
		}
		boolean[] selected = new boolean[N + 1];
		resultCount = Integer.MAX_VALUE;
		subset(1, selected);
		if(resultCount==Integer.MAX_VALUE) {
			System.out.printf("%d",-1);
			return;
		}
		System.out.printf("%d", resultCount);
	}

	public static void subset(int index, boolean[] selected) {
		// N까지의 선택이 끝났다면 연결이 되는지 검사하러 탐색
		if (index == N + 1) {
			int size = 0;
			for (int i = 1; i <= N; i++)
				if (selected[i])
					size++; // 선택된 집합의 길이
			if (size == 0 || size == N)
				return;
			if (bfs(0, size, selected)) { // 만약 올바른 집합이면
				boolean[] selectedOther = new boolean[N + 1];
				for (int i = 1; i <= N; i++)
					selectedOther[i] = !selected[i];
				if (bfs(0, N-size, selectedOther)) {
					int aSum = 0;
					int bSum = 0;
					for (int i = 1; i <= N; i++) {
						if (selected[i])
							aSum += personSize[i];
						else
							bSum += personSize[i];
					}
					resultCount = Math.min(Math.abs(aSum - bSum), resultCount);
				}
			}
			return;
		}
		selected[index] = true;
		subset(index + 1, selected);
		selected[index] = false;
		subset(index + 1, selected);
	}

	public static boolean bfs(int cur, int size, boolean[] selected) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		// 제일 앞에 selected[i] true의 i를 start index로
		int start = 0;
		for (int i = 1; i <= N; i++) {
			if (selected[i]) {
				start = i;
				break;
			}
		}
		if(start==0)
			return false;
		queue.add(start);
		visited[start] = true;
		cur++;
		while (!queue.isEmpty()) {
			int temp = queue.poll();
			if (arrayList[temp] == null)
				continue;
			for (Integer i : arrayList[temp]) {
				if (selected[i]) { // 가야하는 곳 중 하나면
					if (!visited[i]) { // 아직 bfs로 안가본 곳이면
						queue.add(i);
						visited[i] = true;
						cur++;
					}
				}
			}
		}
		if (cur == size) {
			return true;
		}
		return false;
	}

}
