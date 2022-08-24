package ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW_7733_Ä¡ÁîµµµÏ_À±È£¿î {
	static int n;
	static int[][] map;
	static int ans=1;
	static boolean[][] visited;
	static boolean[][] checked;
	static int[] di= {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	public static void dfs(int i,int j) {
		
		if(checked[i][j])
			return;
		checked[i][j]=true;
		
		for(int k=0;k<4;k++) {
			int ni = i+di[k];
			int nj = j+dj[k];
			if(ni<0||nj<0||ni>=n||nj>=n||checked[ni][nj])
				continue;
			dfs(ni,nj);
		}
	}
	public static void solve() {
		int len=0;
		checked = new boolean[n][n];
		for(int i=0;i<n;i++) {
			checked[i]=visited[i].clone();
		}
		
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!checked[i][j]) {
					dfs(i,j);
					len++;
				}
	
			}
		}
		if(len>ans)
			ans=len;

		
	}
	public static void main(String[] args) throws IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringBuilder sb = new StringBuilder();
	      int tc = Integer.parseInt(br.readLine());
	      for(int t=1;t<=tc;t++) {
	    	  
		      n = Integer.parseInt(br.readLine()); //¸Ê ±æÀÌ
		      map = new int[n][n];
		      visited = new boolean[n][n];
		      int min=Integer.MAX_VALUE;
		      
		      for(int i=0;i<n;i++) {
		    	  StringTokenizer st= new StringTokenizer(br.readLine());
		    	  for(int j=0;j<n;j++) {
		    		  map[i][j]= Integer.parseInt(st.nextToken());
		    	  }
		      }
		      
		      for(int day=1;day<101;day++) {
		    	  for(int i=0;i<n;i++) {
		    		  for(int j=0;j<n;j++) {
		    			  if(map[i][j]==day) {
		    				  visited[i][j] = true;
		    			  }
		    		  }
		    	  }
		    	  solve();
	
		      }

		      sb.append("#"+t+" "+ans+"\n");
		      ans=1;
	      }

	      System.out.println(sb);
	          

	}
}
