package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_4949_균형잡힌세상_임지원 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		
		while(true) {
			s = br.readLine();
			
			// 입력의 종료조건
			if(s.equals(".")) break;
			
			sb.append(solve(s)).append("\n");
		}
		System.out.println(sb);

	}
	
	public static String solve(String s) {
		Stack<Character> st = new Stack<>();
		
		for(int i =0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if(c== '(' || c=='[') {
				st.push(c);
			}
			else if(c ==')') {
				// 스택이 비어있거나, pop헐 원소가 소괄호랑 매칭안됨
				if(st.empty() || st.peek() != '(') {
					return "no";
				}
				else {
					st.pop();
				}
			}
			else if(c ==']') {
				// 스택이 비어있거나, pop헐 원소가 소괄호랑 매칭안됨
				if(st.empty() || st.peek() != '[') {
					return "no";
				}
				else {
					st.pop();
				}
			}
		}
		
		if(st.isEmpty())    return "yes";
		else return "no";
	}
}
