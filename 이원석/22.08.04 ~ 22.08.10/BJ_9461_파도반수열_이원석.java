package com.ssafy.day_220808;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class BJ_9461_파도반수열_이원석 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();
            long[] dp = new long[101];

            // 9 - 5 -> 10
            // 8 - 4 -> 9
            // 7 - 3 -> 8

            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;
            dp[5] = 2;

            for (int i = 6; i < N + 1; i++) {
                dp[i] = dp[i - 1] + dp[i - 5];
            }
            System.out.println(Arrays.toString(dp));
            System.out.println(dp[N]);
        }
    }
}

