import java.util.Scanner;
import java.util.Stack;

/**
 * BJ_2504_괄호의 값
 * 4개의 기호 '(',')','[',']'를 이용해 만들어지는 괄호열 중 올바른 괄호열은 다음과 같이 정의한다.
 * 한쌍의 괄호로만 이루어진 '()','[]'는 올바른 괄호열이다.
 * 만약 x가 올바른 괄호라면 (x), [x]도 모두 올바르다.
 * x와 y가 올바른 괄호열이라면 결합한 xy도 올바른 괄호열이다.
 * 
 * ‘()’ 인 괄호열의 값은 2이다.
	‘[]’ 인 괄호열의 값은 3이다.
	‘(X)’ 의 괄호값은 2×값(X) 으로 계산된다.
	‘[X]’ 의 괄호값은 3×값(X) 으로 계산된다.
	올바른 괄호열 X와 Y가 결합된 XY의 괄호값은 값(XY)= 값(X)+값(Y) 로 계산된다.
 * 
 * @author djunnni
 *
 */
public class Main2 {
	public static int smallBrace;
	public static int bigBrace;
	public static boolean isValid;
	public static int answer;
	public static Stack<Integer> stack;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		isValid = true;
		stack = new Stack<>();
		String inputs = sc.next();
		
		parse(inputs, 0);
		
		if(smallBrace != 0 || bigBrace != 0) {
			isValid = false;
		}
		for(int x : stack) {
			answer+= x;
		}
		System.out.println(isValid ? answer : 0);
		
	}
	public static void parse(String inputs, int position) {
		if(inputs.length() == position || !isValid) {
			return;
		}
		char c = inputs.charAt(position);
		if(c == '(') {
			smallBrace++;
			stack.add(0);
		} else if(c == '[') {
			bigBrace++;
			stack.add(1);
		} else if(c == ']') {
			if(bigBrace == 0) {
				isValid = false;
				return;
			}
			if(stack.peek() == 1) {
				stack.pop();
				stack.add(3);
			} else {
				int temp = 0;
				while(stack.peek() != 1) {
					temp += stack.pop();
					if(stack.size() == 0) {
						isValid = false;
						return;
					}
				}
				stack.pop();
				stack.add(temp * 3);
			}
			bigBrace--;
		} else if(c == ')') {
			if(smallBrace == 0) {
				isValid = false;
				return;
			}
			if(stack.peek() == 0) {
				stack.pop();
				stack.add(2);
			} else {
				int temp = 0;
				while(stack.peek() != 0) {
					temp += stack.pop();
					if(stack.size() == 0) {
						isValid = false;
						return;
					}
				}
				stack.pop();
				stack.add(temp * 2);
			}
			smallBrace--;
		}
		parse(inputs, position + 1);
	}
}
