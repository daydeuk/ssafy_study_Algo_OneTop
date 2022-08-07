package com.ssafy.day_220805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class BJ_2504_괄호의값_이원석 {
    static char[] chars;
    static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // (()[[]])([])
        chars = br.readLine().toCharArray();
        stack = new Stack<>();
        int get = 1;
        int answer = 0;
        int flag = 0;

        for (int i = 0; i < chars.length; i++) {
            if (flag == 1) {
                break;
            }
            switch (chars[i]) {

                // 여는 괄호일 때는 무조건 stack에 쌓는다.
                case '(':
                    stack.push('(');
                    get *= 2;
                    break;
                case '[':
                    stack.push('[');
                    get *= 3;
                    break;

                // 닫는 괄호일 때
                case ')':
                    // 만약 스택이 비어있거나, push 할 괄호가 스택에서 짝이 이루어지지 않을경우
                    if ((stack.isEmpty()) || (stack.get(stack.size() - 1) == '[')) {
                        // 정답을 0으로 초기화하고 flag 를 기록한다.
                        answer = 0;
                        flag = 1;
                        break;
                    }
                    // 만약 이전괄호와 짝이 이루어진다면 더해준다.
                    // 스택에서 마지막 괄호와 짝은 이루어지고, 이전 괄호와도 짝이 이루어진다면
                    // 내부 괄호이기 때문에 answer 에 값을 더해준다.
                    else if (chars[i - 1] == '(') {
                        answer += get;
                    }
                    stack.pop();

                    // 위에서 answer 에 값을 추가해줬기 때문에 다음 연산을 위해 괄호값을 나누어준다.
                    get /= 2;
                    break;

                case ']':
                    if ((stack.isEmpty()) || (stack.get(stack.size() - 1) == '(')) {
                        answer = 0;
                        flag = 1;
                        break;
                    } else if (chars[i - 1] == '[') {
                        answer += get;
                    }

                    stack.pop();
                    get /= 3;
                    break;
            }
        }
        if (!stack.isEmpty()) { // 스택에 남은게 있다면 error
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}

// 닫는 괄호
// 이전에 들어온 괄호의 방향과 짝일 경우 (* N)
// 이전에 들어온 괄호와 짝이 아닐경우 (0)
// ( + )
// ( + ]

// 여는 괄호
// 그냥 스택과 배열에 추가한다.
// + [
// + (
// + [
