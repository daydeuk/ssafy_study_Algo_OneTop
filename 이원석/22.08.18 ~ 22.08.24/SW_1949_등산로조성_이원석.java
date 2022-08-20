package com.ssafy._2022_08.day_220820;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


//① 등산로는 가장 높은 봉우리에서 시작해야 한다.
//
//② 등산로는 산으로 올라갈 수 있도록 반드시 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 한다.
//즉, 높이가 같은 곳 혹은 낮은 지형이나, 대각선 방향의 연결은 불가능하다.
//
//③ 긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있다.

public class SW_1949_등산로조성_이원석 {
    static int[][] arr, visited;
    static int N, K, max_h, cnt, flag = 0, max_cnt;

    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t < TC + 1; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            max_cnt = Integer.MIN_VALUE;
            max_h = Integer.MIN_VALUE;

            arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max_h = Math.max(arr[i][j], max_h);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == max_h) {
                        cnt = 1;
                        visited = new int[N][N];
                        visited[i][j] = 1;
                        dfs(i, j, arr[i][j]);
                    }
                }
            }

            System.out.printf("#%d %d\n", t, max_cnt);
        }
    }

    public static void dfs(int x, int y, int now) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 배열 내부에서
            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                // 다음 등산로의 높이는 지금보다 낮아야한다.
                if (arr[nx][ny] < now) {
                    // 방문하지 않은곳만
                    if (visited[nx][ny] == 0) {
                        visited[nx][ny] = 1;
                        cnt++;
                        dfs(nx, ny, arr[nx][ny]);
                        visited[nx][ny] = 0;
                        cnt--;
                    }

                } else if (K >= 0 && flag == 0) { // 그렇지 않다면, 필살기가 남았다면
                    // 방문하지 않은 곳이면
                    if (visited[nx][ny] == 0) {
                        // 필살기를 초과해서 사용하면 안된다.
                        if ((K - (arr[nx][ny] - now + 1)) < 0) {
                            continue;
                        }

                        // 필살기가 먹혔다면,
                        visited[nx][ny] = 1;
                        flag = 1;
                        cnt++;
                        K -= arr[nx][ny] - now + 1;
                        // 낮아진 땅의 높이를 기준으로 순회하기 때문에
                        // 지금 내 높이의 정보를 넘겨야한다.
                        dfs(nx, ny, now - 1);
                        visited[nx][ny] = 0;
                        flag = 0;
                        cnt--;
                        K += arr[nx][ny] - now + 1;
                    }
                }

            }
        }

        // 아무곳도 가지 못하는 경우
        max_cnt = Math.max(max_cnt, cnt);
    }
}

//10
//5 1
//9 3 2 3 2
//6 3 1 7 5
//3 4 8 9 9
//2 3 7 7 7
//7 6 5 5 8

//4 4
//8 3 9 5
//4 6 8 5
//8 1 5 1
//4 9 5 5