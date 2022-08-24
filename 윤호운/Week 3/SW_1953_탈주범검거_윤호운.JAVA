import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
/**
탈주범 검거
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq

경과된 시간이 주어질떄 탈주범이 갈수 있는 공간 수 구하기=> BFS사용해서 해당된시간이되면 VISTED 한 영역 수를 구한다

**/

//경과된 시간,현재위치 저장
class Pos{
	int i,j,time;

	public Pos(int i, int j, int time) {
		super();
		this.i = i;
		this.j = j;
		this.time = time;
	}
	
}
public class Solution {
	static int n,m,r,c,l,ans;
	static int[][] map;
	static int[] di = {-1,1,0,0}; //상하좌우
	static int[] dj = {0,0,-1,1};
	static boolean[][] visited;

  	//현재위치에서 갈수 있는 경우를 큐에삽입한 후 큐리턴
  	//주의: 현재상태뿐만아니라 다음상태도 살펴봐야댐 (EX: 현재가 상이면 다음은 하)
	public static Queue<Pos> check(Queue<Pos> q, int num, Pos now) {
		int ni,nj;
		//상
		if(num==1 || num==2 || num==4 || num== 7) {
			ni = now.i+di[0];
			nj = now.j+dj[0];
			if(ni>=0&&nj>=0&&ni<n&&nj<m&&!visited[ni][nj]) {
				if(map[ni][nj]==1||map[ni][nj]==2||map[ni][nj]==5||map[ni][nj]==6) //다음은 
					q.offer(new Pos(ni,nj,now.time+1));
			}
		}
		//하
		if(num==1 || num==2 || num==5 ||num==6) {
			ni = now.i+di[1];
			nj = now.j+dj[1];
			if(ni>=0&&nj>=0&&ni<n&&nj<m&&!visited[ni][nj]) {
				if(map[ni][nj]==1||map[ni][nj]==2||map[ni][nj]==4||map[ni][nj]==7)
					q.offer(new Pos(ni,nj,now.time+1));
			}
		}
		//좌
		if(num==1 || num==3 || num==6 ||num==7) {
			ni = now.i+di[2];
			nj = now.j+dj[2];
			if(ni>=0&&nj>=0&&ni<n&&nj<m&&!visited[ni][nj]) {
				if(map[ni][nj]==1||map[ni][nj]==3||map[ni][nj]==5||map[ni][nj]==4)
					q.offer(new Pos(ni,nj,now.time+1));
			}
		}
		//우
		if(num==1 || num==3 || num==4 ||num==5) {
			ni = now.i+di[3];
			nj = now.j+dj[3];
			if(ni>=0&&nj>=0&&ni<n&&nj<m&&!visited[ni][nj]) {
				if(map[ni][nj]==1||map[ni][nj]==3||map[ni][nj]==7||map[ni][nj]==6)
					q.offer(new Pos(ni,nj,now.time+1));
			}
		}
		
		return q;
			
	}
	public static void solve() {

		boolean[][] visited = new boolean[n][m];
		Queue<Pos> q = new LinkedList<Pos>();
    		//큐에 초기 위치 삽입.
		q.offer(new Pos(r,c,0));
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
      			//방문한거면 다음 꺼로
			if(visited[now.i][now.j])
				continue;
      			//현재시간이 주어진시간이면 탈출~!
			if(now.time==l)
				break;
      
			//visited 처리를 방문하고나서 해줘야 답구할때 씽크가 맞음
			visited[now.i][now.j]=true;
      			//큐에 넣을거 고르기~ check 호출
			q = check(q,map[now.i][now.j],now);
		}
		
    		//visited된거 개수가 답이다!
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(visited[i][j])
					ans++;
			}
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    int tc=Integer.parseInt(br.readLine());
	    StringBuilder sb=new StringBuilder();
	    for(int t=1;t<=tc;t++){
	    	StringTokenizer st=new StringTokenizer(br.readLine());
	    	ans=0;
	    	n = Integer.parseInt(st.nextToken());
	    	m = Integer.parseInt(st.nextToken());
	    	r = Integer.parseInt(st.nextToken());
	    	c = Integer.parseInt(st.nextToken());
	    	l = Integer.parseInt(st.nextToken());
	    	
	    	map =new int[n][m];
	    	visited = new boolean[n][m];

	    	for(int i=0;i<n;i++) {
	    		st=new StringTokenizer(br.readLine());
	    		for(int j=0;j<m;j++) {
	    			map[i][j] = Integer.parseInt(st.nextToken());
	    		}
	    	}
	    	
	    	solve();
        
	    	sb.append("#"+t+" "+ans+"\n"); 
	    	
	    	
	    }
	    
	    System.out.println(sb);
	   
	    

	}
}
