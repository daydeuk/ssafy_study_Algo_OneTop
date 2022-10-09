import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * swexpert.SW_4008_숫자 만들기
 * 256MB, 0.1sec
 * author djunnni
 * 소요시간: 32min
 */
public class Solution {
    static int N, max, min, data[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        // 테스트케이스 개수
        int T = Integer.parseInt(br.readLine());

        // 반복 TC
        for (int tc = 1; tc <= T; tc++) {
            // 게임판에 적힌 숫자의 개수 (12이하)
            N = Integer.parseInt(br.readLine());

            // 연산자 수 저장
            // 0 : + , 1 : - , 2 : * , 3 : /
            int[] operators = new int[4];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 4; i++) {
                operators[i] = Integer.parseInt(st.nextToken());
            }
            // 수식에 사용될 숫자
            data = new int[N];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                data[i] = Integer.parseInt(st.nextToken());
            }

            // max, min 초기화
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            // 순열 돌리기
            int indexes[] = new int[N - 1];
            permutation(indexes, operators, 0);

            System.out.println("#" + tc + " " + Math.abs(max - min));
        }
    }

    static void permutation(int[] indexes, int[] operators, int cnt) {
        // 순열 돌기 끝!
        if (cnt == N - 1) {
            getResult(indexes);
            // System.out.println(Arrays.toString(indexes));
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operators[i] == 0)
                continue;
            operators[i]--;
            indexes[cnt] = i;
            permutation(indexes, operators, cnt + 1);
            operators[i]++;
        }
    }

    static void getResult(int[] indexes) {
        int sum = data[0];

        for (int i = 0; i < N - 1; i++) {
            sum = calculate(sum, data[i + 1], indexes[i]);
        }

        max = Math.max(max, sum);
        min = Math.min(min, sum);
    }

    static int calculate(int left, int right, int operator) {
        switch (operator) {
            case 0:
                return left + right;
            case 1:
                return left - right;
            case 2:
                return left * right;
            case 3:
                return (int) Math.floor(left / right);
        }
        return 0;
    }
}
