package com.ssafy.day_220812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_14889_스타트와링크_이원석 {
    static int N = 0;
    static int[] visited;
    static int[][] arr;
    static int min_val = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        visited = new int[N];
        arr = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);

        System.out.println(min_val);
    }

    public static void dfs(int r, int start) {
        if (r == N / 2) {
            System.out.println(Arrays.toString(visited));
            form(visited);
            return;
        }

        for (int i = start; i < N; i++) {
            if (visited[i] == 1) {
                continue;
            }
            visited[i] = 1;
            dfs(r + 1, i + 1);
            visited[i] = 0;
        }
    }

    public static void form(int[] visited) {
        List<Integer> team_a = new ArrayList<>();
        List<Integer> team_b = new ArrayList<>();
        int abil_a = 0;
        int abil_b = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 1) { // 팀A
                team_a.add(i);
            } else { // 팀B
                team_b.add(i);
            }
        }
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                if (i == j) {
                    continue;
                }
                int ax = team_a.get(i);
                int ay = team_a.get(j);
                int bx = team_b.get(i);
                int by = team_b.get(j);
                abil_a += arr[ax][ay];
                abil_b += arr[bx][by];
            }
        }

        min_val = Math.min(Math.abs(abil_a - abil_b), min_val);
    }
}

// 1 2 3
// S12 + S21, S13 + S31, S23 + S32
//
//[1, 1, 0, 0]
//[1, 0, 1, 0]
//[1, 0, 0, 1]
//[0, 1, 1, 0]
//[0, 1, 0, 1]
//[0, 0, 1, 1]