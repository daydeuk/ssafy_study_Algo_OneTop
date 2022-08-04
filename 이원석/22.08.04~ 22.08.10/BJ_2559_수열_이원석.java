import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2559
 * @author wonseok
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] dp = new int[N + 1];
        int max_val = Integer.MIN_VALUE;

        // 배열 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // N의 길이의 배열에서 K 길이 만큼의 합들을 구해주게 되면 시간복잡도 O(n^2)
        // N의 최대 길이는 10만, K는 1과 N 사이의 정수이므로 최악의 경우 10만*5만의 시간복잡도를 가지게 된다. 
        // 따라서, 동적 계획법을 사용하여, K일 간의 온도의 합을 구할 수 있다.
        // 1. 누적합을 통해 dp를 초기화 해준다.
        // 2. 모든 연속적인 K일 간의 온도의 합을 구해준다.
        // 2-1. N일을 기점으로 K일 간의 온도의 합은
        //      (N일 간의 누적합) - (N - K일 간의 누적합) 의 점화식을 가진다.
        // 3. 다만, N일을 기점으로 잡을 때, K일 보다 작을 수 없기 때문에
        //    시작 지점은 K일 부터로 한다. 그렇게 되면
        //    (K일 간의 누적합) - (K - K일 간의 누적합 -> 0)


        for (int i = 1; i < N + 1; i++) {
            dp[i] = dp[i - 1] + arr[i - 1];
        }

        for (int i = K; i < N + 1; i++) {
            max_val = Math.max(dp[i] - dp[i - K], max_val);
        }

        System.out.println(max_val);
    }
}

// 3 -2 -4 -9 0 3 7 13 8 -3
// 3  1 -3 -12 -12 -9 -2 11 19 16
// 1-2 누적합 - 1-0 누적합
// 1-3 누적합 - 1-1 누적합
// 1-4 누적합 - 1-2 누적합
////