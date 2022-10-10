import java.awt.Point;
import java.io.*;
import java.util.*;

public class SW_7793_오나의여신님 {

	static int N, M;
	static char[][] map;
	static Queue<Point> devilList;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			Point startPoint = null;
			Point endPoint = null;
			devilList = new LinkedList<>();

			// 입력
			for (int r = 0; r < N; r++) {
				char[] input = in.readLine().toCharArray();
				for (int c = 0; c < M; c++) {
					map[r][c] = input[c];
					if (map[r][c] == 'D')
						endPoint = new Point(r, c);
					else if (map[r][c] == 'S')
						startPoint = new Point(r, c);
					else if (map[r][c] == '*')
						devilList.add(new Point(r, c));
				}
			}
			sb.append("#").append(tc).append(" ");
			int result = bfs(startPoint, endPoint);
			if (result == -1)
				sb.append("GAME OVER");
			else
				sb.append(result);
			sb.append("\n");
		}
		System.out.printf(sb.toString());

	}

	public static int bfs(Point startPoint, Point endPoint) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(startPoint);
		boolean[][] visited = new boolean[N][M];
		visited[startPoint.x][startPoint.y] = true;

		int time = 1;
		while (!queue.isEmpty()) {
			// 1초 후 악마의 위치
			int devilSize = devilList.size();
			for (int i = 0; i < devilSize; i++) {
				Point curDevil = devilList.poll();
				int curDevilR = curDevil.x;
				int curDevilC = curDevil.y;
				for (int d = 0; d < 4; d++) {
					int nextDevilR = curDevilR + dr[d];
					int nextDevilC = curDevilC + dc[d];
					if (check(nextDevilR, nextDevilC)) {
						if (map[nextDevilR][nextDevilC] != '*' && map[nextDevilR][nextDevilC] != 'D') {
							map[nextDevilR][nextDevilC] = '*';
							devilList.offer(new Point(nextDevilR, nextDevilC));
						}
					}
				}
			}
			// 1초 후의 수연의 위치 움직이기
			int queSize = queue.size();
			for (int q = 0; q < queSize; q++) {
				Point curPoint = queue.poll();

				for (int j = 0; j < 4; j++) {
					int nextR = curPoint.x + dr[j];
					int nextC = curPoint.y + dc[j];
					if (check(nextR, nextC)) {
						if (map[nextR][nextC] == '*')
							continue;
						if (map[nextR][nextC] == '.' || map[nextR][nextC] == 'D') {
							if (!visited[nextR][nextC]) {
								visited[nextR][nextC] = true;
								queue.add(new Point(nextR, nextC));
								if (nextR == endPoint.x && nextC == endPoint.y)
									return time;
							}
						}
					}
				}
			}
			time++;
		}
		return -1;

	}

	public static boolean check(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M)
			return false;
		if (map[r][c] == 'X')
			return false;
		return true;
	}
}
