import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_1949_등산로조성_윤호운 {
	static int[] di= {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int len;
	static int ans;
	
	public static void dfs(int[][] map,int i, int j,boolean[][] visited,int n,int sum) {
    //방문했으면 리턴
		if(visited[i][j]) 
			return;
    //방문표시
		visited[i][j]=true;
    //상하좌우 탐색
		for(int k=0;k<4;k++) {
			int t_i = i+di[k];
			int t_j = j+dj[k];
			if(t_i<0||t_j<0||t_i>=n||t_j>=n)
				continue;
      //길이가 현재보다 낮으면 sum+1해서 dfs호출
			if(map[t_i][t_j]<map[i][j])
				dfs(map,t_i,t_j,visited,n,sum+1);
		}
    //방문취소 => 모든 경우 다해야바야대니까 취소처리~!
		visited[i][j]=false;
		
    // sum이 len보다 길면 
		if(sum>len)
			len=sum;
	}
	public static void solve(int[][] map,int max,int n) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
        //최대 높이에서 
				if(map[i][j]==max) {
          //dfs 호출
					dfs(map,i,j,new boolean[n][n],n,1);
          //len이 ans보다 길면 정답 업데이트
					if(len>ans)
						ans=len;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    int tc=Integer.parseInt(br.readLine());
	    StringBuilder sb=new StringBuilder();
	    for(int t=1;t<=tc;t++){
	    	StringTokenizer st=new StringTokenizer(br.readLine());
	    	int n=Integer.parseInt(st.nextToken());
	    	int k = Integer.parseInt(st.nextToken());
	    	int[][] map = new int[n][n];
	    	ans=0;
	    	len=0;
	    	
	    	int max=0;
	    	for(int i=0;i<n;i++) {
	    		st=new StringTokenizer( br.readLine());
	    		for(int j=0;j<n;j++) {
	    			map[i][j]=Integer.parseInt(st.nextToken());
	    			if(map[i][j]>max)
	    				max = map[i][j]; //최대 높이 구하기
	    		}
	    	}
        
	    	//모든 위치 
	    	for(int i=0;i<n;i++) {
	    		for(int j=0;j<n;j++) {
	    			int temp = map[i][j];
	    			int cut=0;
            //해당 위치에서 0~K 깊이까지 하나씩 깎아보기
	    			while(map[i][j]>=0) {
	    				if(cut>k)
	    					break;
	    				map[i][j]=temp-cut++;
              //깎 후 solve 호출
	    				solve(map, max,n);
	    				
	    			}
            //깎은거 되돌려주기
	    			map[i][j]=temp;
	    		}
	    	}
	   
	    	
	    	sb.append("#"+t+" "+ans+"\n");
	    	
	    }
	    
	    System.out.println(sb);
	   
	    

	}
}
