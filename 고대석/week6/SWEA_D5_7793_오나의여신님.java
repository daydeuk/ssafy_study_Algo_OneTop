package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 악마의 손아귀 : 매 초마다 상하좌우 인접해있는 영역들을 부식시키며 확장
 * 수연이는 지은 여신님에게 최소 시간으로 이동
 */
public class SWEA_D5_7793_오나의여신님 {

	static int N, M;
	static char[][] map;
	// 상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			
			Queue<Point> devil = new LinkedList<>();
			Queue<Point> sooyeon = new LinkedList<>();
			
			for (int r = 0; r < N; r++) {
				String s = br.readLine();
				for (int c = 0; c < M; c++) {
					map[r][c] = s.charAt(c);
					if(map[r][c] == '*') devil.offer(new Point(r, c));
					else if(map[r][c] == 'S') sooyeon.offer(new Point(r, c));
				}
			}
			
			int ans = 0;
			boolean finish = false;
			Point p;
			int len;
			
			while(true) {
				// 악마 이동
				len = devil.size();
				for (int i = 0; i < len; i++) {
					p = devil.poll();
					int r = p.r;
					int c = p.c;
					for (int dir = 0; dir < 4; dir++) {
						int nr = r + dr[dir]; 
						int nc = c + dc[dir];
						// 배열 범위 안에 있고 평범한 지역이나 수연이면 스킬 확장
						if(isIn(nr, nc) && (map[nr][nc] == '.' || map[nr][nc] == 'S')) {
							map[nr][nc] = '*'; // 부식
							devil.offer(new Point(nr, nc));
						}
					}
				}
				// 수연 이동
				len = sooyeon.size();
				for (int i = 0; i < len; i++) {
					p = sooyeon.poll();
					int r = p.r;
					int c = p.c;
					// 지은이를 만났으면 종료
					if(map[r][c] == 'D') {
						finish = true;
						break;
					}
					for (int dir = 0; dir < 4; dir++) {
						int nr = r + dr[dir]; 
						int nc = c + dc[dir];
						// 배열 범위 안에 있고 평범한 지역이나 여신이면 이동
						if(isIn(nr, nc) && (map[nr][nc] == '.' || map[nr][nc] =='D')) {
							if(map[nr][nc] != 'D') map[nr][nc] = 'S';
							sooyeon.offer(new Point(nr, nc));
						}
					}
				}
				if(finish || sooyeon.isEmpty()) break;
				ans++;
			}
			
			if(!finish) System.out.println("#" + tc + " " + "GAME OVER");
			else System.out.println("#" + tc + " " + ans);
			
		}
	}

	private static boolean isIn(int nr, int nc) {
		return (nr >= 0 && nr < N && nc >= 0 && nc < M);
	}

}
