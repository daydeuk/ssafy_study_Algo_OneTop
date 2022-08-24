
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW_7733_치즈도둑_윤호운 {
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
	
	//visited 배열 클론해서 dfs 구하기 : 이때 바로 클론하면안댐.. 행마다 클론해주기
	public static void solve() {
		int len=0;
		checked = new boolean[n][n];
		
		for(int i=0;i<n;i++) {
			checked[i]=visited[i].clone();
		}
		
		//만약 방문하지 않았다면 dfs 돌려서 덩어리들 방문표시
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!checked[i][j]) {
					dfs(i,j);
					//개수 +1
					len++;
				}
	
			}
		}
		//최대개수 업데이트
		if(len>ans)
			ans=len;

		
	}
	public static void main(String[] args) throws IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringBuilder sb = new StringBuilder();
	      int tc = Integer.parseInt(br.readLine());
	      for(int t=1;t<=tc;t++) {
	    	  
		      n = Integer.parseInt(br.readLine()); 
		      map = new int[n][n];
		      visited = new boolean[n][n];
		      int min=Integer.MAX_VALUE;
		      
		      for(int i=0;i<n;i++) {
		    	  StringTokenizer st= new StringTokenizer(br.readLine());
		    	  for(int j=0;j<n;j++) {
		    		  map[i][j]= Integer.parseInt(st.nextToken());
		    	  }
		      }
		      
		      //day 1부터 시작
		      for(int day=1;day<101;day++) {
		    	  for(int i=0;i<n;i++) {
		    		  for(int j=0;j<n;j++) {
		    			  // 치즈양과 날짜가 같으면 visited 배열 true로 
		    			  if(map[i][j]==day) {
		    				  visited[i][j] = true;
		    			  }
		    		  }
		    	  }
		    	  //solve() 호출
		    	  solve();
	
		      }

		      sb.append("#"+t+" "+ans+"\n");
		      //초기 치즈양이 1이므로 1로 초기화
		      ans=1;
	      }

	      System.out.println(sb);
	          

	}
}
