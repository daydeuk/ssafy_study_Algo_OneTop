import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Cell{
	//state: 1 비활성 , 2  활성 , 3 죽음
	int i,j,hp,time;
	
	public Cell(int i, int j, int hp, int time) {
		super();
		this.i = i;
		this.j = j;
		this.hp = hp;
		this.time = time;
	}

}

public class SW_줄기세포배양_윤호운 {
	static int N,M,K;
	static int resultCnt;
	static Cell[][] map;
	static Queue <Cell> q ;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	public static void solve(int hour) {

		int size = q.size();
		boolean[][] visited = new boolean[351][351];
		 for(int i=0;i<size;i++){
			Cell now = q.poll();

			int ni = now.i;
			int nj = now.j;
			int hp = now.hp;
			int time = now.time;

			Cell update = new Cell(ni,nj,hp,time+1);
			map[ni][nj] = update;
			
			//비활성상태일떄
			if(time<hp) {
				q.offer(update);
			}
			//활성상태일때
			else if(time<hp*2) {
				q.offer(update);
				for(int k=0;k<4;k++) {
					int ti= ni+di[k];
					int tj= nj+dj[k];
					Cell ncell = new Cell(ti,tj,hp,0);
					
					//맵에 아무것도 없으면
					if(map[ti][tj]==null) {
						visited[ti][tj]=true;
						map[ti][tj]=ncell;
					}
					//맵에 세포가 있으면
					else {
						//기존에 맵에 있던 셀
						Cell tcell = map[ti][tj];
						
						//동시에 들어온거면
						if(visited[ti][tj] && tcell.time==0) {
							//기존거보다 생명력 높으면
							if(tcell.hp<hp) {
								map[ti][tj]=ncell;
							}
								
						}
					}
					
				}

			}
			//죽었을떄
			else {
				//걍넘어가~
			}
		}
		 
		 for(int k=0;k<351;k++) {
			 for(int j=0;j<351;j++) {
				 if(visited[k][j]) {
					 q.offer(map[k][j]);
				 }
					
			 }
		 }
		 
		 
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//행크기(1<=N<=50)
			N = Integer.parseInt(st.nextToken());
			//열크기
			M = Integer.parseInt(st.nextToken());
			//배양시간 (1<=k<=300)
			K = Integer.parseInt(st.nextToken());
		
			map=new Cell[351][351];//150+50+150
			q = new LinkedList<Cell>();
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					int init = Integer.parseInt(st.nextToken());
					if(init!=0) {
						q.offer(new Cell(150+(25-N/2)+i,150+(25-M/2)+j,init,0));
						map[150+(25-N/2)+i][150+(25-M/2)+j] = new Cell(150+(25-N/2)+i,150+(25-M/2)+j,init,0);
					}
					
				}
			}
			//k시간동안 줄기세포 번식하기
			for(int i=0;i<K;i++) {
				solve(i);
			}
			resultCnt=0;
			while(!q.isEmpty()) {
				if(q.peek().time<q.poll().hp*2) {
					resultCnt++;
				}
			}

			sb.append("#" + t + " " + resultCnt + "\n");
		}
		System.out.println(sb);
	}
}
