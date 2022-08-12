

import java.lang.*;
import java.io.*;
import java.util.*;

public class BJ_11725_트리의부모찾기_윤호운 {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n=Integer.parseInt(br.readLine());
        
        //n+1길이의 ArrayList 형 배열 arr 선언 
        // 예) arr[1] = {4,6} 1번노드에 연결된 노드들을 어레이리스트에 담는다
        ArrayList<Integer>[] arr= new ArrayList[n+1];
        
        for(int i=1;i<=n;i++){
            arr[i]=new ArrayList<Integer>();
        }



        //노드번호별로 연결된 노드들을 노드번호별 어레이리스트에 담는다
        for(int i=0;i<n-1;i++){
               StringTokenizer st=new StringTokenizer(br.readLine());
               int a= Integer.parseInt(st.nextToken());
               int b = Integer.parseInt(st.nextToken());

               arr[a].add(b);
               arr[b].add(a);

        }

        int[] ans= new int[n+1];

        Queue<Integer> q = new LinkedList<Integer>();
        // 루트번호 1을 큐에 담음
        q.offer(1);
        boolean[] visited=new boolean[n+1];
        visited[1]=true;
        
        //부모노드 출력 => 너비우선탐색 => 큐!!!!!!!!!!!!!!!!!
        // bfs 너낌
        while(!q.isEmpty()){
            int temp = q.poll();
            for(int i=0;i<arr[temp].size();i++){
                int t= arr[temp].get(i);
                if(!visited[t]){
                    visited[t]=true;
                    q.offer(t);
                    ans[t]=temp; //방문할때 노드번호 기록
                }
            }

        }

        StringBuilder sb= new StringBuilder();
        //출력~
        for(int i=2;i<=n;i++){
            sb.append(ans[i]+"\n");
        }
        System.out.println(sb);

     


    }   

}

