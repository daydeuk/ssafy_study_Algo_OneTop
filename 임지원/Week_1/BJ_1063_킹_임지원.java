package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1063_킹_임지원 {
	
	static int kx, ky;	// 킹 위치
	static int sx, sy;	// 돌 위치
	static int[][] map = new int[8][8];					// king 1, stone 2, empty 0
	static int[] dy= {0, 0, -1, 1, 1, 1, -1, -1};		//R, L, B, T, RT, LT, RB, LB
	static int[] dx= {1, -1, 0, 0, 1, -1, 1, -1};
	
	public static int direction(String s) {
		int dir = 0;
		
		switch(s) {
			case "R":
				dir = 0;
				break;
			case "L":
				dir = 1;
				break;
			case "B":
				dir= 2;
				break;
			case "T":
				dir = 3;
				break;
			case "RT":
				dir = 4;
				break;
			case "LT":
				dir = 5;
				break;
			case "RB":
				dir = 6;
				break;
			case "LB":
				dir = 7;
				break;
		}
		
		return dir;
	}
	
	public static void move (String s, int x, int y, int state) {
		
		int dir = direction(s);
		
		int nextX = x + dx[dir];
		int nextY = y + dy[dir];
		
		// 경계 벗어남
		if(nextX < 0 || nextX >= 8 || nextY < 0 || nextY >= 8) return;
		
		//돌 이동
		if(state == 2) {
			map[sx][sy] = 0;
			// 돌 위치 업데이트
			sx = nextX;
			sy = nextY;
			map[sx][sy] = 2;
			return;
		}
		
		// 돌과 같은 곳으로 이동할 때 
		if(map[nextX][nextY] == 2) {
			move(s, nextX, nextY, 2);	// 돌을 킹이 움직인 방향과 같은 방향으로 한 칸 이동
			
			//돌이 경계 벗어났는지 확인
			if(map[nextX][nextY] == 0) {
				map[kx][ky] = 0;
				kx = nextX;
				ky = nextY;
				map[kx][ky] = 1;			// 후에 킹도 이동
			}
			return;
		}

		//킹 이동
		map[kx][ky] = 0;
		// 킹 위치 업데이트
		kx = nextX;
		ky = nextY;
		map[kx][ky] = 1;

		return;		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String king = st.nextToken();		//킹 위치
		kx = king.charAt(0) - 'A';
		ky = king.charAt(1) - '1';
		map[kx][ky] = 1;
		
		String stone = st.nextToken();		//돌 위치
		sx = stone.charAt(0) - 'A';
		sy = stone.charAt(1) - '1';
		map[sx][sy] = 2;
		
		int N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			move(s, kx, ky, 1);
		}

		System.out.println((char)(kx + 'A') + "" + (ky + 1));		//*입력받을 때 -1해줘서 +1해주기
		System.out.println((char)(sx + 'A') + "" + (sy + 1));
	}
}