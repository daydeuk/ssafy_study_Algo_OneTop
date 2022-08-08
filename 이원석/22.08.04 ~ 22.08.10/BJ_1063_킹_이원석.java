package com.ssafy.day_220808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class King {
    int x;
    int y;

    public King(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Stone {
    int x;
    int y;

    public Stone (int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// 1-7,
// 2-6,
// 3-5,
// 4-4,
// 5-3,
// 6-2,
// 7-1,
// 8-0

public class BJ_1063_킹_이원석 {
    static King k_target;
    static Stone s_target;
    static char[] validate = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        char[] king = st.nextToken().toCharArray();
        char[] stone = st.nextToken().toCharArray();
        k_target = new King(8 - Integer.parseInt(String.valueOf(king[1])), king[0] - 65);
        s_target = new Stone(8 - Integer.parseInt(String.valueOf(stone[1])), stone[0] - 65);

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            move(br.readLine());

        }
        convert();

    }

    public static void move(String m) {
        int origin_x = k_target.x;
        int origin_y = k_target.y;

        switch (m) {
            case "R":
                if ((k_target.y + 1 < 8) && ((k_target.x >= 0) && (k_target.x < 8))) {
                    k_target.y += 1;
                }
                break;
            case "L":
                if ((k_target.y - 1 >= 0) && ((k_target.x >= 0) && (k_target.x < 8))) {
                    k_target.y -= 1;
                }
                break;
            case "B":
                if ((k_target.x + 1 < 8) && ((k_target.y >= 0) && (k_target.y < 8))) {
                    k_target.x += 1;
                }
                break;
            case "T":
                if ((k_target.x - 1 >= 0) && ((k_target.y >= 0) && (k_target.y < 8))) {
                    k_target.x -= 1;
                }
                break;
            case "RT":
                if ((k_target.x - 1 >= 0) && (k_target.y + 1 < 8)) {
                    k_target.x -= 1;
                    k_target.y += 1;
                }
                break;
            case "LT":
                if ((k_target.x - 1 >= 0) && (k_target.y - 1 >= 0)) {
                    k_target.x -= 1;
                    k_target.y -= 1;
                }
                break;
            case "RB":
                if ((k_target.x + 1 < 8) && (k_target.y + 1 < 8)) {
                    k_target.x += 1;
                    k_target.y += 1;
                }
                break;
            case "LB":
                if ((k_target.x + 1 < 8) && (k_target.y - 1 >= 0)) {
                    k_target.x += 1;
                    k_target.y -= 1;
                }
                break;
        }
        // 8,2 -> 7,1

        // 돌이 겹치는 경우
        if ((k_target.x == s_target.x) && (k_target.y == s_target.y)) {
//            System.out.println("!!");
//            System.out.println(s_target.x + (k_target.x - origin_x) + ", " + (s_target.y + (k_target.y - origin_y)));
            int get_x = s_target.x + (k_target.x - origin_x);
            int get_y = s_target.y + (k_target.y - origin_y);
//            System.out.println(get_x + ", " + get_y);

            if (((get_x >= 0) && (get_x < 8)) && ((get_y >= 0) && (get_y < 8))) {
                s_target.x = get_x;
                s_target.y = get_y;
            } else {
                k_target.x -= (k_target.x - origin_x);
                k_target.y -= (k_target.y - origin_y);
            }
        }
    }

    public static void convert() {
        String king_ans = validate[k_target.y] + Integer.toString(8 - k_target.x);
        String stone_ans = validate[s_target.y] + Integer.toString(8 - s_target.x);
        System.out.println(king_ans);
        System.out.println(stone_ans);
    }
}
