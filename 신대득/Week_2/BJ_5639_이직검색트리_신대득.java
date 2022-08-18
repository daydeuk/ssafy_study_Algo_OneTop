import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_5639_이직검색트리_신대득 {
	public static class Node {
		int data;
		Node leftChild;
		Node RightChild;

		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 가장 처음 입력은 Root
		int data = Integer.parseInt(in.readLine());
		Node Root = new Node(data);
		Node head = Root;
		String str;
		
		// 이제 자식을 추가해 가는데 Root부터 시작해서 추가할 위치를 찾아낸다.
		while (true) {
			str = in.readLine();
			if(str == null || str == "") {
				break;
			}
			Node curNode = Root;
			data = Integer.parseInt(str);
			Node N = new Node(data);
			while (true) {
				if (N.data > curNode.data) {
					if (curNode.RightChild == null) {
						curNode.RightChild = N;
						break;
					}
					curNode = curNode.RightChild;
				} else if (N.data < curNode.data) {
					if (curNode.leftChild == null) {
						curNode.leftChild = N;
						break;
					}
					curNode = curNode.leftChild;
				}
			}
		}
		preOrder(head);
	}

	static void preOrder(Node node) {
		if(node==null)
			return;
		preOrder(node.leftChild);
		preOrder(node.RightChild);
		System.out.println(node.data);
	}
}
