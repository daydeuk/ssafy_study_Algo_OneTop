package com.ssafy._2022_08.day_220820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468_안전영역_이원석 {
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int N, max_h = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int get, cnt;
        int max_cnt = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                get = Integer.parseInt(st.nextToken());
                // 최대높이
                max_h = Math.max(max_h, get);
                arr[i][j] = get;
            }
        }



        for (int i = 0; i < max_h; i++) {
            cnt = 0;
            visited = new boolean[N][N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    // 강수량에 잠기지 않으며 방문하지 않은 영역
                    if (arr[j][k] > i && !visited[j][k]) {
                        bfs(i, j, k);
                        cnt++;
                    }
                }
            }
            max_cnt = Math.max(max_cnt, cnt);
        }

        System.out.println(max_cnt);
    }

    public static void bfs(int h, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            x = poll[0];
            y = poll[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 배열 내부에서
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    // 강에 잠기지 않는 곳으로 가자
                    if (arr[nx][ny] > h && !visited[nx][ny]) {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }

        }

    }
}
