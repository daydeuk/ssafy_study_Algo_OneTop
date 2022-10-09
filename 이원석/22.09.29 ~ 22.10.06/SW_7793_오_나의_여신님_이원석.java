package com.ssafy._2022_10.day_221009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int x;
    int y;
    int kind; // 0: devil, 1: su_yun

    public Point(int x, int y, int kind) {
        this.x = x;
        this.y = y;
        this.kind = kind;
    }
}

// 오나의여신님
// 1. 그래프 탐색문제
// 2. 악마의 손아귀가 수연이 보다 먼저 확장된다.
// 3. 수연이는 악마의 손아귀가 확장된 뒤에 이동한다.
// 4. 따라서 수연이가 다음으로 이동하려는 영역의 가중치가 악마의 손아귀 가중치보다 같거나 낮아야한다.


public class SW_7793_오_나의_여신님_이원석 {
    static int N, M, visited[][][],
    dx[] = {-1, 1, 0, 0,},
    dy[] = {0, 0, -1, 1};

    static char arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t < TC + 1; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new char[N][M];
            visited = new int[N][M][2];

            int sx = 0, sy = 0; // 시작점
            int ex = 0, ey = 0; // 도착점
            for (int i = 0; i < N; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    arr[i][j] = chars[j];

                    // 시작점 저장.
                    if (chars[j] == 'D') { // 목적지 저장.
                        ex = i;
                        ey = j;
                    }
                }
            }

            // 악마의 손아귀
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == '*') {
                        bfs(i, j, 0);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 'S') {
                        bfs(i, j, 1);
                    }
                }
            }

            int result = visited[ex][ey][1];

            if (result == 0) {
                System.out.printf("#%d GAME OVER\n", t);
            } else {
                System.out.printf("#%d %d\n", t, result - 1);
            }
        }
    }

    public static void bfs(int r, int c, int kind) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c, kind});

        visited[r][c][kind] = 1;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int k = poll[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 악마의 손아귀
                if (k == 0) {
                    // 범위 내에서
                    if (isIn(nx, ny)) {
                        // 다음 경로가 평지이거나 수진이의 위치인 경우에는..
                        if (arr[nx][ny] == '.' || arr[nx][ny] == 'S') {
                            // 방문하지 않았다면!?
                            if (visited[nx][ny][0] == 0) {
                                visited[nx][ny][0] = visited[x][y][0] + 1;
                                q.add(new int[]{nx, ny, kind});
                            }
                        }
                    }
                } else { // 수진
                    if (isIn(nx, ny)){
                        if (arr[nx][ny] == '.' || arr[nx][ny] == 'D') {
                            if (visited[nx][ny][1] == 0) {
                                visited[nx][ny][1] = visited[x][y][1] + 1;

                                // 아직 악마의 손아귀가 퍼지지 않았거나, 악마의 손아귀보다 빠르게 도착했다면?
                                if (visited[nx][ny][1] < visited[nx][ny][0] || visited[nx][ny][0] == 0) {
                                    q.add(new int[]{nx, ny, kind});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isIn(int nx, int ny) {
        if (0 <= nx && nx < N && 0 <= ny && ny < M) {
            return true;
        }
        return false;
    }
}

//1
//5 6
//.**..S
//....X.
//....X.
//....X.
//D.....
