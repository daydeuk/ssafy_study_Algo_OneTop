package ps.graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node1{
	int left=-1;
	int right=-1;

}
public class BJ_5639_이진검색트리_윤호운 {
	
	public static Node1[] arr =new Node1[1000000];
	public static int root;
	public static StringBuilder sb = new StringBuilder();
	public static void makeTree(int now,int parent) {
		if(now<parent) { //현재노드가 부모보다 작음
			if(arr[parent].left==-1) //왼쪽자식없으면 내가 왼쪽자식이다
				arr[parent].left = now;
			else //왼쪽 자식 있으면 왼쪽으로 내려가-> 왼쪽자식이 내 부모인지확인^^
				makeTree(now,arr[parent].left);
			
		}
		else { //현재노드가 부모보다 크면
			if(arr[parent].right==-1) //근데 오른쪽자식없으면 내가 오른쪽자식이다~
				arr[parent].right = now;
			else //오른쪽 자식있으면 오른쪽으로 내려가 -> 오른쪽 자식이 내 부모인지 확인^^
				makeTree(now,arr[parent].right);
		}
	}
	//포스트오더 출력 
	public static void postOrder(int now) {
		if(now==-1)
			return;
		postOrder(arr[now].left);
		postOrder(arr[now].right);
		sb.append(now+"\n");
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //최고부모인 루트를 기준으로 보기!    
        root = Integer.parseInt(br.readLine());
        for(int i=0;i<1000000;i++) {
        	arr[i]=new Node1();
        }
        
        while(true) {
        	String str = br.readLine();
        	if(str.equals("")||str==null)
        		break;
        	int now = Integer.parseInt(str);
        	makeTree(now,root);
        }
        postOrder(root);
        System.out.println(sb);
    }   
}
