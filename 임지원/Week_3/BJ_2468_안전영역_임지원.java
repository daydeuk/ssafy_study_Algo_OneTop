import java.util.Scanner;
/*
* 물에 잠기지 않는 안전한 영역의 최대 개수를 출력
*/
public class BJ_2468_안전영역_임지원 {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int cnt = 0;

	public static void dfs(int x, int y, int h) {
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			// 경계선 체크
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
				continue;
			
			if(map[nextX][nextY] > h && visited[nextX][nextY] == false) {
				dfs(nextX, nextY, h);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int highestH = 0;
		int lowestH = 101;
		map = new int[N][N];
		visited = new boolean[N][N];
		int rslt = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				highestH = Math.max(map[i][j], highestH);
				lowestH = Math.min(map[i][j], lowestH );
			}
		}

		if(lowestH == highestH) {
			System.out.println(rslt);
		}
		else {
			lowestH = 0; // 아무 지역도 물에 잠기지 않을 수도 있다 (=비의 양이 0인 경우)

			//System.out.println(lowestH + "/" + highestH);
			while(lowestH <= highestH) {
				cnt = 0;
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] > lowestH && visited[i][j] == false) {
							cnt++;		// 안전영역 +1
							dfs(i, j, lowestH);
						}
					}
				}
				//System.out.println(cnt);
				rslt = Math.max(rslt, cnt);
				visited = new boolean[N][N];
				lowestH++;
			}
			System.out.println(rslt);
		}
	}
}
