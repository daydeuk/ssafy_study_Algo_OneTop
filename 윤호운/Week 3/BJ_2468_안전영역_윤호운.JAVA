import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map ;
	static int n;
	static int ans;
	static int[] di= {-1,1,0,0,};
	static int[] dj = {0,0,-1,1};
	static boolean[][] visited;
	
	public static void dfs(int now_i,int now_j,int h) {
    //방문한 곳이라면 리턴
		if(visited[now_i][now_j]) {
			return;
		}
    //방문표시
		visited[now_i][now_j]=true;
    //상하좌우 살피기
		for(int k=0;k<4;k++) {
			int t_i = now_i+di[k];
			int t_j = now_j + dj[k];
			if(t_i<0||t_j<0||t_i>=n||t_j>=n) {
				continue;
			}
      //해당 위치의 높이가 물의 높이보다 크면 dfs
			if(map[t_i][t_j]>h)
				dfs(t_i,t_j,h);
			
		}
	}
	public static void solve(int h) {
		int cnt=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
        //맵의 각 위치마다 방문하지않은 곳에서 dfs를 돌리기 => 안전지역 방문표시 해주기 위함 
				if(map[i][j]>h&&!visited[i][j]) {
					dfs(i,j,h);
          //dfs 갓다오면 cnt+1
					cnt++;
				}
			}
		}
		
		if(cnt>ans)
			ans=cnt;
		
	}
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());  
       
        map = new int[n][n];
        int max = Integer.MIN_VALUE;
      
        for(int i=0;i<n;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j=0;j<n;j++) {
        		map[i][j]=Integer.parseInt(st.nextToken());
            //최대 높이 찾기
        		if(map[i][j]>max)
        			max = map[i][j];
        	}
        }
        //비의 양마다 DFS 돌려서 안전영역 찾아보기 =>  0부터~max
        for(int i=0;i<=max;i++) {
          //새로운 비의 양마다 새로운 visited
        	visited=new boolean[n][n];
        	solve(i); 
        }
        System.out.println(ans);

    }  
}
