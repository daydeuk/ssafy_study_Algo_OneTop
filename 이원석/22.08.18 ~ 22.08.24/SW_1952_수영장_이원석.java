package com.ssafy._2022_08.day_220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_1952_수영장_이원석 {
    static int[] price = new int[4];
    static int[] month = new int[12];
    static int min_val;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t < TC + 1; t++) {
            min_val = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 4; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 12; i++) {
                int get = Integer.parseInt(st.nextToken());
                month[i] = get;
            }

            dfs(0, 0);
            System.out.printf("#%d %d\n", t, min_val);
        }
    }

    public static void dfs(int m, int now_price) {
        if (m >= 12) {
            min_val = Math.min(min_val, now_price);
            return;
        }

        if (month[m] == 0) {
            dfs(m + 1, now_price);
        } else {
            // 1일
            dfs(m + 1, now_price + month[m] * price[0]);

            // 1달
            dfs(m + 1, now_price + price[1]);

            // 3달
            dfs(m + 3, now_price + price[2]);

            // 1년
            if (m == 0) {
                dfs(m + 12, now_price + price[3]);
            }
        }


    }
}

//10
//10 40 100 300
//0 0 2 9 1 5 0 0 0 0 0 0