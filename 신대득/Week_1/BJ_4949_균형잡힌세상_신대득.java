import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_4949_균형잡힌세상_신대득 {
	static Stack<Character> stack;

	public static boolean check(char c) {
		switch (c) {
		case '[':
			stack.push(c);
			break;
		case '(':
			stack.push(c);
			break;
		case ')':
			if (!stack.isEmpty()) {
				if (stack.peek() == '(') {
					stack.pop();
					break;
				}
			}
			return false;
		case ']':
			if (!stack.isEmpty()) {
				if (stack.peek() == '[') {
					stack.pop();
					break;
				}
			}
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String s = in.readLine();
			stack = new Stack<>();
			boolean result = true;
			if (s.charAt(0) == '.')
				break;
			for (int i = 0; i < s.length() && result; i++) {
				char c = s.charAt(i);
				if (check(c) == false) {
					result = false;
					break;
				}
			}
			String answer = "no";
			if (result&&stack.isEmpty())
				answer = "yes";
			sb.append(answer).append("\n");
		}
		System.out.printf(sb.toString());
	}

}
