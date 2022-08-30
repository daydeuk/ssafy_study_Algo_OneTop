import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 연결되어있는 트리들을 하나의 집합으로 묶어서, 몇개의 집합이 있는지 세는
 * 서로소 문제
 * Kruskal 알고리즘 사용 => find, Union
 * 사이클은 어떻게 해결??????? => Union false
 */
public class BJ_4803_트리_신대득 {

	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 & M == 0)
				break;
			sb.append("Case ").append(tc).append(": ");
			// make
			parents = new int[N + 1];
			for (int i = 1; i <= N; i++)
				parents[i] = i;

			// 입력
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(in.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				union(from, to);
			}

			// 유니온 파인드
			int sum=0;
			for(int i=1;i<=N;i++) {
				if(find(i)==i) {
					sum+=1;
				}
			}
			
			/*
			Set s = new HashSet<Integer>();
			for(int i=1;i<=N;i++) {
				int root = find(i);
				if(find(i)!=0)
					s.add(root);
			}
				int sum = s.size();
			*/
		
			
			if (sum > 1) {
				sb.append("A forest of ").append(sum).append(" trees.\n");
			} else if (sum == 1) {
				sb.append("There is one tree.\n");
			} else if (sum == 0) {
				sb.append("No trees.\n");
			}
			tc++;
		}
		System.out.printf(sb.toString());

	}

	public static int find(int check) {
		if (parents[check] == check)
			return check;
		return parents[check] = find(parents[check]);
	}

	public static boolean union(int from, int to) {
		int aRoot = find(from);
		int bRoot = find(to);
		if (aRoot == bRoot || aRoot==0 || bRoot==0) { // 원래 같은 부모면.. 싸이클이면?
			parents[aRoot] = 0;
			parents[bRoot] = 0;
			return false;
		}
		if(bRoot<aRoot) {
			int tmp=aRoot;
			aRoot=bRoot;
			bRoot=tmp;
		}
		parents[bRoot] = aRoot;
		return true;
	}
}
