package ps.data.structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// ...킹받는다
public class BJ_2504_괄호의값_윤호운 {
	
	static char[] arr;
	static Stack<Character> open_s = new Stack<>();
	static boolean[] visited;
	
	public static int solve(int i) {
		int sum=0;
		open_s.add(arr[i]); //여는 괄호 스택에 추가
		char close; //여는괄호에 맞는 닫는괄호 close 변수로 지정
		if(arr[i]=='[')
			close=']';
		else
			close=')';
		
		//여는 괄호 내부부터 탐색 
		for(int k=i+1;k<arr.length;k++) {
			if(!visited[k]) { //만약 방문 안했으면 검사
				visited[k]=true;
				if (arr[k]==close) { //여는괄호한테 맞는 닫는 괄호면
					open_s.pop(); //일단 여는괄호 스택에서 빼
					if(sum==0) { // sum 이 없다는 것은 [] 또는 () 임 => 2또는 3 반환
						if(close==']')
							return 3;
						else 
							return 2;
					}
					else{ // sum이 0이 아니면 => 2또는 3을 sum에 곱해서 반환
						if(close==']')
							return 3*sum;
						else 
							return 2*sum;
					}
	
				}
				else if(arr[k]=='['||arr[k]=='(') { //여는괄호면 solve호출 -> 여는 괄호의 내부 탐색
					sum+=solve(k);
				}
				else { //여는괄호도아니고 나한테 맞는 닫는괄호도 아니면 걍 실패임
					System.out.println(0);
					System.exit(0);
				}
		}
		}

		//여기까지 오면 실패임 => 걍 아무거나 리턴 , 어차피 메인에서 처리함
		return -1;
	}

	public static void main(String[] args) throws IOException {
		// () ->2 / []->3
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    arr = br.readLine().toCharArray();
	    
	    int ans=0;
	    visited=new boolean[arr.length];
	    for(int i=0;i<arr.length;i++) {
	    	if(!visited[i]) {
		    	if(arr[i]=='('||arr[i]=='[') //현재인덱스가 여는 괄호면 solve(현재인덱스) 호출
		    		ans+=solve(i);
		    	else { //만약 visited 안한 닫는 괄호면 실패 => 닫는 괄호는 solve에서 다 처리해야댐
		    		ans=0;
		    		break;
		    	}
	    	}
	    }
	    if(!open_s.isEmpty())
	    	ans=0;
	    System.out.println(ans);
	    
	}
}
