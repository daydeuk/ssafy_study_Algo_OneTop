import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_14888_연산자끼워넣기
 *
 * 소요시간 21분 35초
 * 
 * author djunnni
 */
public class Main {
    public static int N, MAX, MIN;
    public static int[] operators, arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        // 0 -> 더하기, 1 -> 빼기, 2 -> 곱하기, 3 -> 나누기
        operators = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < operators.length; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        MAX = Integer.MIN_VALUE;
        MIN = Integer.MAX_VALUE;

        selectOperator(0, 0, 0, 0, 0, arr[0]);

        System.out.printf("%d\n%d", MAX, MIN);
    }

    /**
     * cnt는 cnt와 cnt+1 사이의 연산자가 들어가 값을 구해준다.
     */
    public static void selectOperator(int cnt, int plus, int minus, int multi, int divide, int sum) {
        if(cnt == N - 1) {
            MAX = Math.max(MAX, sum);
            MIN = Math.min(MIN, sum);
            return;
        }

        for(int operator = 0; operator < operators.length; operator++) {
            if(operators[operator] == 0) {
                continue;
            }
            operators[operator]--;
            switch(operator) {
                case 0:
                    selectOperator(cnt + 1, plus + 1, minus, multi, divide, sum + arr[cnt + 1]);
                    break;
                case 1:
                    selectOperator(cnt + 1, plus, minus + 1, multi, divide, sum - arr[cnt + 1]);
                    break;
                case 2:
                    selectOperator(cnt + 1, plus, minus, multi + 1, divide, sum * arr[cnt + 1]);
                    break;
                case 3:
                    selectOperator(cnt + 1, plus, minus, multi, divide + 1, sum / arr[cnt + 1]);
                    break;
            }
            operators[operator]++;
        }
    }
}
