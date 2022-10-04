import java.util.*;

/**
 * 양궁대회
 * 2022 KAKAO BLIND RECRUITMENT
 * 
 * @author djunnni
 */
class Solution {
    class GameResult {
        boolean win;
        int scoreDiff;
        int[] arr;

        GameResult(boolean win, int diff) {
            this.win = win;
            scoreDiff = diff;
        }

        GameResult(boolean win, int diff, int[] arr) {
            this(win, diff);
            this.arr = arr;
        }

        @Override
        public String toString() {
            return "[win :" + win + ",scoreDiff :" + scoreDiff + ", arr :" + Arrays.toString(arr).replaceAll(" ", "")
                    + " +]";
        }
    }

    static GameResult res;
    static int n, info[];

    // 목표: 라이언이 어피치를 가장 큰 점수 차이로 이기기위해 어디로 n발을 쏠지 구합니다.
    public int[] solution(int n, int[] info) {
        // n의 범위 (1 <= n <= 10)
        this.info = info;
        this.n = n;

        res = new GameResult(false, 0, new int[info.length]);

        int temp[] = new int[info.length];
        shoot(temp, 10, 0);

        return res.win ? res.arr : new int[] { -1 };
    }

    public void shoot(int[] temp, int cnt, int shootCount) {
        if (cnt < 0) {
            GameResult result = isWin(temp);
            if (result.win && res.scoreDiff <= result.scoreDiff) {
                res.win = result.win;
                res.scoreDiff = result.scoreDiff;
                res.arr = result.arr;
            }
            return;
        }

       for(int i = 0; i < info.length; i++) {
            if(shootCount + i > n) continue;
            temp[cnt] += i;
            shoot(temp, cnt - 1, shootCount + i);
            temp[cnt] -= i;
       }
    }

    public GameResult isWin(int[] answer) {
        int apachScore = 0;
        int ryanScore = 0;

        for (int i = 0; i < info.length; i++) {
            if (info[i] == 0 && answer[i] == 0) {

            } else if (info[i] >= answer[i]) {
                apachScore += (10 - i);
            } else if (info[i] < answer[i]) {
                ryanScore += (10 - i);
            }
        }
        return new GameResult(ryanScore > apachScore, ryanScore - apachScore, answer.clone());
    }
}
