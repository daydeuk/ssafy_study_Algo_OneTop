import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BJ_5639_이진검색트리
 * 
 * @author djunnni
 *
 */
public class Main {
	static class Node {
		int data;
		Node left;
		Node right;
		
		public Node(int data) {
			this.data = data;
		}
		
		static public void insert(Node node, int data) {
			boolean isBig = node.data < data;
			if(isBig) {
				// 오른쪽
				if(node.right == null) {
					node.right = new Node(data);
				} else {
					insert(node.right, data);
				}
			} else {
				// 왼쪽
				if(node.left == null) {
					node.left = new Node(data);
				} else {
					insert(node.left, data);
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Node tree = null;
		String data;
		while((data = br.readLine()) != null) {
			if(data.equals("")) {
				break;
			}
			if(tree == null) {
				tree = new Node(parseInt(data));
			} else {
				Node.insert(tree, parseInt(data));
			}
		}
		
		postTrace(tree);
		
	}
	public static void postTrace(Node tree) {
		if(tree.left != null) {
			postTrace(tree.left);
		}
		if(tree.right != null) {
			postTrace(tree.right);
		}
		System.out.println(tree.data);
	}
	public static int parseInt(String v) {
		return Integer.parseInt(v);
	}
}
