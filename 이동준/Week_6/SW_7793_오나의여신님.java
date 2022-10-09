import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * swexpert.SW_7793_오! 나의 여신님
 * author djunnni
 * 소요시간: 50min
 */
public class Solution {
    static class Spot {
        int r, c;

        Spot() {
        }

        Spot(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Spot{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int N, M, answer;
    static char[][] map;
    static int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        // 테스트 케이스 수
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");

            // 열 n (50 이하)
            N = Integer.parseInt(st.nextToken());
            // 행 m (50 이하)
            M = Integer.parseInt(st.nextToken());

            // 지도 초기화
            map = new char[N][M];

            Spot suyeon = new Spot();
            Queue<Spot> devils = new LinkedList<>();

            /*
             * . : 평범지역
             * S : 수연
             * D : 여신
             * X : 돌
             * : 악마
             */
            for (int i = 0; i < N; i++) {
                String data = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = data.charAt(j);
                    if (map[i][j] == 'S') {
                        suyeon.r = i;
                        suyeon.c = j;
                        map[i][j] = '.';
                    } else if (map[i][j] == '*') {
                        devils.offer(new Spot(i, j));
                    }
                }
            }

            // map 출력
            // for(char[] row : map) {
            // System.out.println(Arrays.toString(row));
            // }

            answer = -1;
            BFS(suyeon, devils);

            sb.append("#").append(tc).append(" ").append((answer == -1 ? "GAME OVER" : answer)).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void BFS(Spot suyeon, Queue<Spot> devils) {
        boolean visited[][] = new boolean[N][M];
        Queue<Spot> queue = new ArrayDeque<>();
        queue.offer(suyeon);
        visited[suyeon.r][suyeon.c] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            while (size-- > 0) {
                Spot su = queue.poll();

                // 악마의 손아귀가 먼저 퍼졌을 수 있어서
                if (map[su.r][su.c] == '*') {
                    continue;
                }
                for (int i = 0; i < dr.length; i++) {
                    int nr = su.r + dr[i];
                    int nc = su.c + dc[i];

                    // 지도에서 나갈 경우, 방문했던 경우
                    if (isOver(nr, nc) || visited[nr][nc])
                        continue;
                    // 돌이거나 악마의 손아귀일 경우,
                    if (map[nr][nc] == 'X' || map[nr][nc] == '*')
                        continue;

                    if (map[nr][nc] == 'D') {
                        answer = count;
                        return;
                    }
                    if (map[nr][nc] == '.') {
                        visited[nr][nc] = true;
                        queue.offer(new Spot(nr, nc));
                    }
                }
            }

            int devilSize = devils.size();
            while (devilSize-- > 0) {
                Spot devil = devils.poll();
                // System.out.println("devil: " + devil);

                for (int i = 0; i < dr.length; i++) {
                    int nr = devil.r + dr[i];
                    int nc = devil.c + dc[i];

                    if (isOver(nr, nc) || map[nr][nc] == '*' || map[nr][nc] == 'X' || map[nr][nc] == 'D')
                        continue;

                    map[nr][nc] = '*';
                    devils.offer(new Spot(nr, nc));
                }
            }
        }
    }

    static boolean isOver(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= M) {
            return true;
        }
        return false;
    }
}
