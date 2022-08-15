package com.ssafy.day_220815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int root;
    Node left;
    Node right;

    public Node(int root) {
        this.root = root;
    }

    public void insert(int root) {
        if (this.root > root) { // 루트 노드의 왼쪽 자식일 경우,
            if (left == null) {
                // 아직 자식설정을 안했다면 추가
                left = new Node(root);
            } else {
                // 자식이 있다면 자식의 자식으로 보내버려
                left.insert(root);
            }
        } else {
            if (right == null) {
                // 아직 자식설정을 안했다면 추가
                right = new Node(root);
            } else {
                // 자식이 있다면 자식의 자식으로 보내버려
                right.insert(root);
            }
        }
    }
}

public class BJ_5639_이진검색트리_이원석 {
    static StringBuilder sb = new StringBuilder("");
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String get = br.readLine();
            if (get == null || get.equals("")) {
                break;
            }
            root.insert(Integer.parseInt(get));
        }

        post_order(root);
        System.out.println(sb);
    }

    public static void post_order(Node node) { // 후위 순회
        if (node == null) {
            return;
        }

        post_order(node.left); // 왼쪽
        post_order(node.right); // 오른쪽
        sb.append(node.root + "\n");
    }
}
