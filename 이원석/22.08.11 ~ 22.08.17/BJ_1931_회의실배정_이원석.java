package com.ssafy.day_220817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int start;
    int end;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Node o) {
        return this.end != o.end ? this.end - o.end : this.start - o.start;
    }
}

public class BJ_1931_회의실배정_이원석 {
    static int N;
    static int max_len = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<Node> list = new ArrayList<>();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Node(start, end));
        }

        Collections.sort(list);
        Assignment(list, 0);

        System.out.println(max_len);
    }

    public static void Assignment(List<Node> list, int index) {
        List<Node> result = new ArrayList<>();
        result.add(list.get(index));

        for (int i = index + 1; i < N; i++) {
            if (list.get(i).start >= result.get(result.size() - 1).end) {
                result.add(list.get(i));
            }
        }

//        for (Node node : result) {
//            System.out.println(node.start + ", " + node.end);
//        }

        max_len = Math.max(max_len, result.size());
    }
}

// 0, 6
// 1, 4
// 2, 13
// 3, 5
// 3, 8
// 5, 7
// 5, 9
// 6, 10
// 8, 11
// 8, 12
// 12, 14