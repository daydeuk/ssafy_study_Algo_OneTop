import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	static int find(int x) {
		if(parents[x] ==x) return x;
		return find(parents[x]);
	}
	
	static void union(int x, int y) {
		x= find(x);
		y= find(y);
		
		if(x > y) {
			parents[x] = y;
		}
		else if(x==y) { //간선인데 부모가 같다면...? => 싸이클
			parents[x] = 0;
		}
		else {
			parents[y] = x;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int idx=1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			if(n==0&&m==0) 
				break;
			
			parents = new int[n+1];
			for(int i=1; i<=n; i++) {
				parents[i] = i;
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
			}
			
			Set s = new HashSet<Integer>();
			for(int i=1;i<=n;i++) {
				int root = find(i);
				if(find(i)!=0)
					s.add(root);
			}
			
			int cnt = s.size();
			
			if(cnt==0) {
				sb.append("Case " + idx +": ").append("No trees.\n");
			}else if(cnt==1) {
				sb.append("Case " + idx +": ").append("There is one tree.\n");
			}else if(cnt>1){
				sb.append("Case " + idx +": ").append("A forest of "+cnt+" trees.\n");
			}
			idx++;
		}
		
		System.out.println(sb.toString());
	}
	
	
}
