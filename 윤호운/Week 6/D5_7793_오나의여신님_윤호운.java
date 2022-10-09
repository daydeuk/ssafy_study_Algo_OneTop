import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Node3{
	int i,j;
	public Node3(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
public class D5_7793_오나의여신님_윤호운 {
	static int n,m;
	static char[][] map;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static Queue<Node3> sq ;
	static Queue<Node3> dq ;
	static boolean[][] visited;
	static int[][] time;
	static int ans=0;
	
	public static int bfs() {
		
		while(!sq.isEmpty()) {
			//악마 이동
			int dsize = dq.size();
			for(int i=0;i<dsize;i++) {
				Node3 now = dq.poll();
				
				for(int k=0;k<4;k++) {
					int ti = now.i + di[k];
					int tj = now.j + dj[k];
					
					if(ti<0||tj<0||ti>=n||tj>=m||map[ti][tj]=='X') {
						continue;
					}
					if(map[ti][tj]=='.' || map[ti][tj]=='S') {
						map[ti][tj]='*';
						dq.offer(new Node3(ti,tj));
					}

				}
			}
			

			
			//수연이 이동
			int ssize = sq.size();
			for(int i=0;i<ssize;i++) {
				Node3 now = sq.poll();
				
				if(map[now.i][now.j]=='D')
					return time[now.i][now.j];
				
				for(int k=0;k<4;k++) {
					int ti = now.i + di[k];
					int tj = now.j + dj[k];
					
					if(ti<0||tj<0||ti>=n||tj>=m||visited[ti][tj]||map[ti][tj]=='X') {
						continue;
					}
					if(map[ti][tj]=='.'||map[ti][tj]=='D') {
						time[ti][tj]=time[now.i][now.j]+1;
						visited[ti][tj]=true;
						sq.offer(new Node3(ti,tj));
					}

				}
			}
	
		}
		
		return -1;
		
	}
	
	public static void main(String[] args) throws IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringBuilder sb = new StringBuilder();
	      int tc = Integer.parseInt(br.readLine());
	      for(int t=1;t<=tc;t++) {
	    	  int ans=0;
	    	  StringTokenizer st = new StringTokenizer(br.readLine());
		      n = Integer.parseInt(st.nextToken()); 
		      m = Integer.parseInt(st.nextToken()); 
		      
		      
		      map=new char[n][m];
		      visited=new boolean[n][m];
		      time = new int[n][m];
		      sq = new LinkedList<>();
		      dq = new LinkedList<>();
		      
		      for(int i=0;i<n;i++) {
		    	  map[i]=br.readLine().toCharArray();
		    	  for(int j=0;j<m;j++) {
		    		  if(map[i][j]=='S') {
		    			  sq.offer(new Node3(i,j));
		    			  visited[i][j]=true;
		    		  }
		    		  //악마면 다 넣기
		    		  else if(map[i][j]=='*') {
		    			  dq.offer(new Node3(i,j));
		    		  }
		    			  
		    	  }
		      }

		      
		      ans = bfs();
		      if(ans==-1)
		    	  sb.append("#"+t+" GAME OVER\n");
		      else
		    	  sb.append("#"+t+" "+ans+"\n");
			     
		    
		      

	      }

	      System.out.println(sb);
	          

	}
}
