package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 후위순회한 결과 출력
 * 
 * 1. Node 클래스 선언
 * 2. 입력받기
 * 3. 이진트리 생성
 * 4. 후위순회 결과 출력
 * 
 * EOF : 입력에서 더이상의 읽을 수 있는 데이터가 존재하지 않을 때
 * 참고
 * https://st-lab.tistory.com/40
 */
public class BJ_5639_이진검색트리_임지원 {

	static StringBuilder sb = new StringBuilder();
	
	static class Node{
		int data;
		Node left;
		Node right;
		
		public Node(int data) {
			this.data = data;
		}
		
//		@Override
//		public String toString() {
//			return "data : " + data +
//	                " left :" + left +
//	                " right : " + right;
//		}

	}
	
	// 후위순회 (왼자식 - 오른자식 - 루트)
	private static void postOrder(Node root) {
		
		if(root.left != null) {
			postOrder(root.left);
		}
		if(root.right != null) {
			postOrder(root.right);
		}
		
		sb.append(root.data+"\n");
		
	}

	private static void insert(Node root, Node next) {
		
		if(next.data < root.data) {
			if(root.left !=null) {
				insert(root.left, next);
			}else {
				root.left = next;
			}
		}else {
			if(root.right != null) {
				insert(root.right, next);
			}else {
				root.right = next;
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 루트
		Node root = new Node(Integer.parseInt(br.readLine()));
		
		String input;
		
		// 트리를 전위 순회(루트 - 왼자식 - 오른자식)한 결과 입력됨
		// EOF는 데이터가 없는 상태
		// 엔터, 스페이스 같은 모든 키보드 입력은 데이터가 존재
		// BufferedReader 의 경우 null 을 반환
		// BufferedReader로 null을 반환하기 위해서는 역시 EOF를 던져주어야 하기 때문에 
		// IDE에서도 '데이터의 마지막 값을 다 읽었다'는 'EOF' 상태로 만들기 위해서
		// ctrl + Z (윈도우)혹은 ctrl + D(리눅스)를 입력해야함 
		while((input = br.readLine()) != null) {
			Node next = new Node(Integer.parseInt(input));
			insert(root,next);
		}
		
		postOrder(root);
		
		System.out.println(sb);
	}


}
