import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_4949_균형잡힌세상_윤호운 {
	
	public static void main(String[] args) throws IOException {

			BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			String s = br.readLine();
			while(!s.equals(".")) {
				Stack<Character> stack = new Stack<>();
				String ans ="yes"; 
				
				for(int i=0;i<s.length()-1;i++) {
					char now = s.charAt(i);
					if(now=='['||now=='(') {
						stack.add(s.charAt(i)); //여는 괄호면 스택에 넣기
					}
					else if(now==')'||now==']') { //닫는 괄호 만났을떄
						if(stack.isEmpty()) { //스택이 비어있으면 실패
							ans="no";
							break;
						}
						char open = stack.pop(); //스택에서 하나 뽑아서 짝 확인
						if((now==')'&&open=='(')||(now==']'&&open=='[')) //맞으면 continue
							continue;
						else { //아니면 실패
							ans="no";
							break;
						}

					}
				}
				// 스택에 값 남아있으면 실패
				if(!stack.isEmpty())
					ans="no";
				sb.append(ans+"\n");
				s=br.readLine();
			}
			
			
		    System.out.println(sb);
		    
		}
}
