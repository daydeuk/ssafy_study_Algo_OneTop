import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1068_트리_신대득 {

	static int N;
	static int leafCount;

	public static class Node {
		int number;

		Node left;
		Node right;

		int parentNumber;
		Node parent;

		public Node(int parentNumber) {
			this.parentNumber = parentNumber;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");	
		// root 노드
		Node root = new Node(Integer.parseInt(st.nextToken()));
		root.number = 0;
		root.parent = null;
		// root 노드 제외 n-1개 노드찾기
		for (int i = 1; i < N; i++) {
			Node curNode = root;
			int parentNumber = Integer.parseInt(st.nextToken());
			Node node = new Node(parentNumber);
			node.number = i;
			findNode(node, curNode);
		}
		int deleteNumber = Integer.parseInt(in.readLine());

		if (deleteNumber == 0) {
			System.out.printf("%d", leafCount);
			return;
		}
		deleteNode(root, deleteNumber);
		if (root != null)
			findleaf(root);
		System.out.printf("%d", leafCount);
	}

	public static void findleaf(Node curNode) {
		if (curNode.left == null && curNode.right == null) {
			leafCount++;
			return;
		}
		if (curNode.left != null)
			findleaf(curNode.left);
		if (curNode.right != null)
			findleaf(curNode.right);
	}

	public static void deleteNode(Node curNode, int deleteNumber) {
		if (curNode.left != null) {
			if (curNode.left.number == deleteNumber) {
				curNode.left = null;
				return;
			}
		}
		if (curNode.right != null) {
			if (curNode.right.number == deleteNumber) {
				curNode.right = null;
				return;
			}
		}
		if (deleteNumber < curNode.number) {
			return;
		}
		if (curNode.left != null)
			deleteNode(curNode.left, deleteNumber);
		if (curNode.right != null)
			deleteNode(curNode.right, deleteNumber);
	}

	public static void findNode(Node node, Node curNode) {
		if (curNode.number == node.parentNumber) {
			if (curNode.left == null) {
				curNode.left = node;
				return;
			} else if (curNode.right == null) {
				curNode.right = node;
				return;
			}
		}
		if (curNode.left != null)
			findNode(node, curNode.left);
		if (curNode.right != null)
			findNode(node, curNode.right);
	}

}
