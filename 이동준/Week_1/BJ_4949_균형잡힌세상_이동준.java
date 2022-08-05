package com.ssafy.lab.BJ_4949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * BJ_4949_균형잡힌세상
 * 시간제한 : 1초, 메모리 : 128MB
 * 
 * 어떤 문자열이 주어졌을 때, 괄호들의 균형이 잘 맞춰져 있는지 판단하는 프로그램
 * 문자열에 포함된 괄호는 '()', '[]' 2종류이며 문자열이 균형을 이루는 조건은 다음과 같다.
 * 
 * 모든 왼쪽 소괄호는 오른쪽 소괄호와 짝을 이루어야 한다.
 * 모든 왼쪽 대괄호는 오른쪽 대괄호와 짝을 이루어야 한다.
 * 모든 오른쪽 괄호들은 자신과 짝을 이룰 수 있는 왼쪽 괄호가 존재한다.
 * 모든 괄호들의 짝은 1:1 매칭만 가능하며, 괄호 하나가 둘 이상의 괄호와 짝지어지지 X
 * 짝을 이루는 괄호가 2개 있을 때, 그 사이에 문자열도 균형으로 구현되어야 함.
 * 
 * @author djunnni
 *
 */
public class Main2 {
	public static void main(String[] args) throws IOException {
		// 여러줄의 문장이 나와 BufferedReader를 사용함.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			// 한줄을 읽되, 만약 "."이면 종료를 한다.
			String input = br.readLine();
			if(input.equals(".")) {
				break;
			}
			// 대괄호와 소괄호의 시작 부분의 Count를 센다.
			Stack<Character> stack = new Stack<>();
			boolean isValid = true;
			// 괄호가 교차했을 때, 어떻게 알 수 있니?
			
			for(int i = 0; i < input.length() && isValid; i++) {
				char c = input.charAt(i);
				switch(c) {
					case '(':
						stack.add('(');
						break;
					case ')':
						if(stack.isEmpty()) {
							isValid = false;
							break;
						}
						if(stack.peek() == '(') {
							stack.pop();
						} else {
                            isValid = false;
                        }
						break;
					case '[':
						stack.add('[');
						break;
					case ']':
						if(stack.isEmpty()) {
							isValid = false;
							break;
						}
						if(stack.peek() == '[') {
							stack.pop();
						} else {
                            isValid = false;
                        }
						break;
				}
			}
			if(stack.isEmpty() && isValid) {
				System.out.println("yes");
				continue;
			}
			System.out.println("no");	
		}
	}
}
