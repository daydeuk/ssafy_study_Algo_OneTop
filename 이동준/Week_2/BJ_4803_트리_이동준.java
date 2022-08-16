import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * BJ_4803_트리
 *
 * author djunnni
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 1;

        String input = null;
        while((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input, " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) {
                break;
            }
            // arr은 부모의 루트노드를 의미한다.
            int arr[] = new int[N + 1];
            // 자기자신이 부모로 초기화한다.
            for(int i = 1; i <= N; i++) {
                arr[i] = i;
            }
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                union(arr, from, to);
            }

            HashSet<Integer> set = new HashSet<>();
            for(int i = 1; i <= N; i++) {
                int r = find(arr, i);
                if(r > 0) {
                    set.add(r);
                }
            }

            int answer = set.size();
            sb.append("Case ").append(tc).append(": ");
            if(answer > 1) {
                sb.append("A forest of ").append(answer).append(" trees.\n");
            } else if(answer == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("No trees.\n");
            }
            tc++;
        }
        System.out.println(sb);
    }
    public static int find(int[] arr, int n1) {
        if(arr[n1] == n1) {
            return n1;
        }
        return find(arr, arr[n1]);
    }
    // 부모는 작은 숫자를 바라보도록 변경한다.
    public static void union(int[] arr, int n1, int n2) {
        int p1 = find(arr ,n1);
        int p2 = find(arr ,n2);

        if(p1 > p2) {
          int temp = p1;
          p1 = p2;
          p2 = temp;
        }

        // 두개가 같은 부모면 사이클
        if(p1 == p2) {
            arr[p1] = 0;
        } else {
            arr[p2] = p1;
        }
    }
}
/**
 * 6 3
 * 1 2
 * 2 3
 * 3 4
 * 6 5
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 * 5 6
 * 6 6
 * 1 2
 * 2 3
 * 1 3
 * 4 5
 * 5 6
 * 6 4
 * 0 0
 * =>
 * Case 1: A forest of 3 trees.
 * Case 2: There is one tree.
 * Case 3: No trees.
 */