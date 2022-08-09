package com.ssafy.day_220809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BJ_4949_균형잡힌세상_이원석 {
    static Stack<Character> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            stack = new Stack<>();
            char[] chars = br.readLine().toCharArray();
            if ((chars[0] == '.') && (chars.length == 1)) {
                break;
            }
            check(chars);
        }

    }

    public static void check(char[] chars){
        for (char c : chars) {
            // 괄호일 경우에만
            if ((c == '(') || (c == '[') || (c == ']') || (c == ')')) {
                if (c == '(' || c == '[') { // 여는 괄호면 그냥 추가
                    stack.add(c);
                } else if (c == ']') { // 닫는 괄호일 때
                    if (stack.isEmpty()) {
                        stack.add(c);
                        break;
                    }

                    if (stack.peek() == '[') { // 짝이라면
                        stack.pop();
                        continue;
                    } else if (stack.peek() == '(') { // 불균형을 이룬다면
                        break;
                    } else if (stack.peek() == ']' || stack.peek() == ')') {
                        stack.add(c);
                    }
                } else if (c == ')') {
                    if (stack.isEmpty()) {
                        stack.add(c);
                        break;
                    }

                    if (stack.peek() == '(') { // 짝이라면
                        stack.pop();
                        continue;
                    } else if (stack.peek() == '[') { // 불균형을 이룬다면
                        break;
                    } else if (stack.peek() == ']' || stack.peek() == ')') {
                        stack.add(c);
                    }
                }
            }
        }
        if (stack.size() == 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }
}
