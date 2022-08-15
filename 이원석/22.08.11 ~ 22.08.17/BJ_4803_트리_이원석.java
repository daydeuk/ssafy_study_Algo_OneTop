package com.ssafy.day_220815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_4803_트리_이원석 {
    static List<Integer>[] graph;
    static int[] visited;
    static int flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = 0;

        while (true) {
            int cnt = 0;
            T++; // TC

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            visited = new int[n + 1];
            graph = new ArrayList[n + 1];

            for (int i = 1; i < n + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x].add(y);
                graph[y].add(x);
            }


            for (int i = 1; i < n + 1; i++) {
                if (visited[i] == 0) { // 방문하지 않을 노드일 경우만 탐색
//                    System.out.println("==== Start!! ====");
                    flag = 0;
                    dfs(i, 0);
                    if (flag == 0) { // 사이클이 아닐 경우,
                        cnt += 1;
                    }
                }
            }

            if (cnt > 1) {
                System.out.println("Case " + T + ": A forest of " + cnt + " trees.");
            } else if (cnt == 1) {
                System.out.println("Case " + T + ": There is one tree.");
            } else {
                System.out.println("Case " + T + ": No trees.");
            }
        }
    }

    public static void dfs(int node, int before) {
        if (graph[node] == null) { // 더 이상 진행할 노드가 없다면 return
            return;
        }
        if (visited[node] == 1) { // 이미 방문한 노드일 경우 (되돌아 가는 경우)
            return;
        }

        visited[node] = 1; // 방문처리

        for (int n : graph[node]) {
            if ((visited[n] == 1) && (n != before)) { // 되돌아 가는 상황이 아닐경우,
                flag = 1;
                continue;
            }
            dfs(n, node);
        }
    }
}
