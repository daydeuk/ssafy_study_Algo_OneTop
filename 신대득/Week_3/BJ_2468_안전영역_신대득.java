import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468_안전영역_신대득 {

	static int N;
	
	// 4방탐색 상하좌우
	static int[] dx= {0,0,-1,1};
	static int[] dy= {-1,1,0,0};
	static int[][] curMap;
	static int maxIndex;
	static int resultCount;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // N입력
		// (1 ~ N) * (1 ~ N)의 맵 만들고 입력
		int[][] map =new int[N+1][N+1];
		maxIndex=-1;
		for(int r=1;r<=N;r++) {
			StringTokenizer st = new StringTokenizer(in.readLine()," ");
			for(int c=1;c<=N;c++) {
				map[r][c]=Integer.parseInt(st.nextToken());
				if(maxIndex<map[r][c])
					maxIndex=map[r][c];
			}
		}
		resultCount=0;
		for(int i=0;i<maxIndex;i++) {
			recur(map,i);
		}
		System.out.printf("%d", resultCount);
	}
	public static void recur(int[][] map, int cur) {
		// 기저조건
		/*
		if(cur==maxIndex) {
			return;
		}
		*/
		curMap= new int[N+1][N+1];
		for(int r=1;r<=N;r++) {
			for(int c=1;c<=N;c++) {
				// 현재 물의 높이보다 작거나 같으면 복사하지않는다.
				if(map[r][c]<=cur)
					curMap[r][c]=0;
				else
					curMap[r][c]=map[r][c];
			}
		}
		int count=0;
		boolean[][] visited= new boolean[N+1][N+1];
		for(int r=1;r<=N;r++) {
			for(int c=1;c<=N;c++) {
				if(visited[r][c]==true)
					continue;
				if(curMap[r][c]!=0) {
				// 값이 존재하는 곳을 찾으면 그곳을 기준으로 bfs를 통해서 인접 값들 다 0으로 바꾸고 counting
				count++;
				bfs(r,c, visited);
				}
			}
		}
		resultCount=Integer.max(resultCount, count);
		//recur(map,cur+1); // 다음 높이로 떠남
	}
	
	// bfs를 통해 
	public static void bfs(int r, int c, boolean[][] visited) {
		Point p= new Point(r,c);
		Queue<Point> que= new LinkedList<Point>();
		que.add(p);
		curMap[r][c]=0;
		visited[r][c]=true;
		while(!que.isEmpty()) {
			Point tempP= que.poll();
			int tempR=tempP.x;
			int tempC=tempP.y;
			for(int i=0;i<4;i++) {
				if(check(tempR+dy[i], tempC+dx[i])) {
					if(curMap[tempR+dy[i]][tempC+dx[i]]==0)
						continue;
					if(!visited[tempR+dy[i]][tempC+dx[i]]) {
						que.add(new Point(tempR+dy[i], tempC+dx[i]));
						visited[tempR+dy[i]][tempC+dx[i]]=true;
						curMap[tempR+dy[i]][tempC+dx[i]]=0;
					}
				}
			}
		}
	}
	// r,c 위치가 맵 안에 있는지 확인하는 메서드
	public static boolean check(int r, int c) {
		if(r<1 || r>N || c<1 || c>N)
			return false;
		return true;
	}
}
