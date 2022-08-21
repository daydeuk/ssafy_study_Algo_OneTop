package com.ssafy._2022_08.day_220821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW_1953_탈주범검거_이원석 {
    static int N, M, R, C, L, cnt;
    static int[][] arr, visited;
    static Queue<int[]> queue;
    static List<int[]> list;
    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0 ,0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t < TC + 1; t++) {
            cnt = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            arr = new int[N][M];
            visited = new int[N][M];
            list = new ArrayList<>();
            list_make();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();

            System.out.printf("#%d %d\n", t, cnt);
        }
    }

    public static void list_make() {
        list.add(new int[]{0, 1, 2, 3});
        list.add(new int[]{0, 1});
        list.add(new int[]{2, 3});
        list.add(new int[]{0, 3});
        list.add(new int[]{1, 3});
        list.add(new int[]{1, 2});
        list.add(new int[]{0, 2});
    }

    public static void bfs() {
        queue = new LinkedList<>();
        queue.add(new int[]{R, C});
        visited[R][C] = 1;

        while (!queue.isEmpty()) {
            cnt++;
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            switch (arr[x][y]) {
                // 상하좌우
                case 1:
                    search(x, y, list.get(0));
                    break;
                // 상하
                case 2:
                    search(x, y, list.get(1));
                    break;
                // 좌우
                case 3:
                    search(x, y, list.get(2));
                    break;
                // 상우
                case 4:
                    search(x, y, list.get(3));
                    break;
                // 하우
                case 5:
                    search(x, y, list.get(4));
                    break;
                // 하좌
                case 6:
                    search(x, y, list.get(5));
                    break;
                // 상좌
                case 7:
                    search(x, y, list.get(6));
                    break;
            }
        }
    }
    // 다음 파이프와 지금 파이프가 맞는지 보려면,
    // 다음 파이프의 좌표에서 지금 파이프의 좌표를 뺀다.
    // x = -1(위에서 밑으로), 1(밑에서 위로)
    // y = -1(우에서 좌로), 1(좌에서 우로)
    public static void search(int x, int y, int[] way) {
        for (int i = 0; i < way.length; i++) {
            int nx = x + dx[way[i]];
            int ny = y + dy[way[i]];
            // 범위 내부
            if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                if (arr[nx][ny] != 0) {
                    int wx = x - nx;
                    int wy = y - ny;

                    if (!check(wx, wy, arr[nx][ny])) {
                        continue;
                    }

                    if (visited[x][y] >= L) { // 제한시간을 초과하는 경우
                        continue;
                    }

                    // 한번도 방문하지 않은 경로일 때,
                    if (visited[nx][ny] == 0) {

                        visited[nx][ny] = visited[x][y] + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // 지금의 파이프가 다음 파이프와 연결되어 있는지 확인
    public static boolean check(int wx, int wy, int i) {
        int[] ways = list.get(i - 1);

        for (int way : ways) {
            if (wx == dx[way] && wy == dy[way]) {
                return true;
            }
        }
        return false;
    }
//        System.out.println(nx + ", " + ny);
//        System.out.println(Arrays.toString(way));
//        System.out.println(Arrays.toString(list.get(arr[nx][ny] - 1)));

}

// 10 10 4 3 9
// 0 0 0 0 0 0 0 0 0 0
// 0 0 0 7 5 0 5 0 0 0
// 0 0 3 2 2 6 0 0 0 0
// 0 4 7 2 2 2 7 0 0 4
// 0 3 0 1 1 2 2 0 0 5
// 0 5 6 1 1 1 1 6 2 5
// 7 4 1 2 0 0 4 6 0 0
// 5 3 1 7 0 2 2 6 5 7
// 7 3 2 1 1 7 1 0 2 7
// 3 4 0 0 4 0 5 1 0 1