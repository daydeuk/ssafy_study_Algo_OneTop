package com.ssafy._2022_09.day_220920;

import java.util.HashMap;

public class PG_완주하지못한선수_이원석 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));
    }

    public static String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        String answer = "";

        for (String p : participant) {
            // 동명이인이 없는 경우 default_value 0 + 1 을 value 값으로 둔다.
            // 동명이인이 있는 경우 map.key(p) + 1 을 value 값으로 둔다.
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        for (String c : completion) {
            // 완주한 player 의 value 값을 1 감소시킨다.
            map.put(c, map.get(c) - 1);
        }

//        for (String s : map.keySet()) {
//            System.out.println(s + ", " + map.get(s));
//        }

        for (String s : map.keySet()) {
            if (map.get(s) == 1) {
                answer += s;
            }
        }

        return answer;
    }
}
