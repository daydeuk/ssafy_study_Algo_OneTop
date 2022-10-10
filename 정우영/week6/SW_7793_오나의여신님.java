import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{
	static int N, M;
	static char[][] map;
	static boolean flag = false;
	static int min = Integer.MAX_VALUE;
	static Queue<Pair> devQ;
	static Queue<Pair> perQ;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(in.readLine());
		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			devQ = new LinkedList<>();
			perQ = new LinkedList<>();
			int x = 0;
			int y = 0;
			for (int i = 0; i < N; i++) {
				String s = in.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '*') {
						devQ.add(new Pair(j, i));
					} else if (map[i][j] == 'S') {
						perQ.add(new Pair(j, i));
					}

				}
			}
			int ans=move();
			System.out.print("#"+(t+1)+" ");
			System.out.println(ans==-1?"GAME OVER":ans);
		}

	}

	private static int move() {
		int depth = 1;
		while (true) {
			int size = devQ.size();
			while (size-- > 0) {
				int x = devQ.peek().x;
				int y = devQ.poll().y;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx >= 0 && ny >= 0 && nx < M && ny < N && (map[ny][nx] == '.' || map[ny][nx] == 'S')) {
						map[ny][nx] = '*';
						devQ.add(new Pair(nx, ny));
					}
				}

			}
			size=perQ.size();
			while(size-->0) {
				int x = perQ.peek().x;
				int y = perQ.poll().y;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx >= 0 && ny >= 0 && nx < M && ny < N ) {
						if(map[ny][nx]=='.') {
							map[ny][nx]='S';
							perQ.add(new Pair(nx,ny));
						}
						else if(map[ny][nx]=='D') {
							return depth;
						}
					}
				}
			}
			if(perQ.size()==0) {
				break;
			}
			depth++;
			
			

		}
		return -1;

	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	
	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}
