package com.ssafy._2022_09.day_220920;

import java.util.Arrays;

public class PG_전화번호목록_이원석 {
    public static void main(String[] args) {
        solution(new String[]{"119", "97674223", "1195524421"});
    }

    public static boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                answer = false;
                return answer;
            }
        }

        return answer;
    }
}
