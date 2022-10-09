package com.ssafy._2022_10.day_221009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 숫자만들기
// 1. 주어진 기호들의 개수에 맞게 기호들을 모두 뽑는다!
// 1-1. 기호가 여러개이며, 뽑은 기호는 -1! 가지치기 조건 설정.
// 1-2. 뽑는순서에 결과가 달라지기 때문에, 순서가 상관있으며 중복을 허용한다! (중복순열)
// 1-3. 주어진 기호의 개수만큼 모두 사용했다면 중복선택에서 제외한다. (가지치기)
// 1-4. (숫자의 수 - 1) 만큼 기호를 저장할 배열에 기호들 (0,1,2,3) 을 저장한다.
// 2. 계산 로직을 통해서 계산하고 최대, 최소값을 구한다.

public class SW_4008_숫자만들기_이원석 {
    static int
            cnt_sings[], // [0: +], [1: -], [2: x], [3: /],
            nums[], N, visited[], res[],
    min_total, max_total
    ;

    static ArrayList<Character> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t < TC + 1; t++) {
            // 초기화
            list = new ArrayList<>();
            min_total = Integer.MAX_VALUE;
            max_total = Integer.MIN_VALUE;


            // 숫자의 개수 N
            N = Integer.parseInt(br.readLine());
            visited = new int[N - 1];
            res = new int[N - 1];


            // 연산자
            cnt_sings = new int[4];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                cnt_sings[i] = Integer.parseInt(st.nextToken());
            }

//            get_sign();


            // 숫자
            nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            perm(0);

            System.out.printf("#%d %d\n", t, max_total - min_total);
        }
    }

    public static void perm(int cnt) {
        // 기저조건
        if (cnt == N - 1) {
//            System.out.println(Arrays.toString(res));
            calc(res);
            return;
        }

        // [0: +], [1: -], [2: x], [3: /],
        for (int i = 0; i < 4; i++) {
//            System.out.println("====" + Arrays.toString(cnt_sings) + "====");
            // 남은 기호의 개수가 0개보다 큰 경우에만
            if (cnt_sings[i] > 0) {
                res[cnt] = i; // 사용할 기호들 기록
                cnt_sings[i] -= 1; // 해당기호 사용
                perm(cnt + 1);
                cnt_sings[i] += 1; // 백트래킹
            }
        }
    }

    public static void calc(int[] res) {
        // 계산을 위해 첫 번째 숫자는 기본적으로 할당받는다.
        int total = nums[0];

        // 숫자의 개수만큼 loop
        for (int i = 0; i < res.length; i++) {
            switch (res[i]) {
                case 0:
//                    System.out.println(total + "+" + nums[i]);
                    total += nums[i + 1];
                    break;
                case 1:
//                    System.out.println(total + "-" + nums[i]);
                    total -= nums[i + 1];
                    break;
                case 2:
//                    System.out.println(total + "*" + nums[i]);
                    total *= nums[i + 1];
                    break;
                case 3:
//                    System.out.println(total + "/" + nums[i]);
                    total /= nums[i + 1];
                    break;
            }
        }

        min_total = Math.min(min_total, total);
        max_total = Math.max(max_total, total);
    }

//    public static void get_sign() {
//        // 기호의 개수만큼
//        for (int i = 0; i < 4; i++) {
//            // 해당 기호의 수 만큼
//            for (int j = 0; j < cnt_sings[i]; j++) {
//                switch (i) {
//                    case 0: // +
//                        list.add('+');
//                        break;
//                    case 1: // -
//                        list.add('-');
//                        break;
//                    case 2: // x
//                        list.add('x');
//                        break;
//                    case 3: // /
//                        list.add('/');
//                        break;
//                }
//            }
//        }
//    }
}


//1
//5
//2 1 0 1
//3 5 3 7 9
// 4, 10

//1
//6
//4 1 0 0
//1 2 3 4 5 6

//1
//5
//1 1 1 1
//9 9 9 9 9