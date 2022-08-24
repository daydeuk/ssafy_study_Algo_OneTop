import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * author djunnni
 */
public class Solution {
    static char[][] matrix;
    static boolean[][] visited;
    static int L,C,R,M,N, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder out = new StringBuilder();

        // 테스트 개수
        int T = Integer.parseInt(in.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            out.append("#").append(test_case).append(" ");

            StringTokenizer tokens = new StringTokenizer(in.readLine(), " ");

            // 지하터널의 세로 크기
             N = Integer.parseInt(tokens.nextToken());
            // 지하터널의 가로 크기
             M = Integer.parseInt(tokens.nextToken());
            // 맨홀 뚜껑 위치 세로
             R = Integer.parseInt(tokens.nextToken());
            // 맨홀 뚜껑 위치 가로
             C = Integer.parseInt(tokens.nextToken());
            // 탈출 후 소요된 시간 L
             L = Integer.parseInt(tokens.nextToken());

            // 공간 정보 배열
            matrix = new char[N][M];

            for(int i = 0; i < N; i++) {
                String input = in.readLine();
                for(int j = 0, index = 0; j < M; j++, index += 2) {
                    matrix[i][j] = input.charAt(index);
                }
            }

//            // 공간 정보 출력하기
//            for(char [] row : matrix) {
//                System.out.println(Arrays.toString(row));
//            }
//            System.out.println("========================");

            visited = new boolean[N][M];

            BFS(R, C);

            out.append(answer).append("\n");
        }
        System.out.println(out);
    }
    public static boolean isOver(int r, int c) {
        if(r >= N || r < 0 || c < 0 || c >= M) {
            return true;
        }
        return false;
    }
    public static void BFS(int row, int column) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {row ,column, 1});

        int cnt = 0;

        while(!queue.isEmpty()) {
            int[] spot = queue.poll();
            int r = spot[0];
            int c = spot[1];
            int time = spot[2];
            // 방문 이력이 있다면 0 리턴
            if(visited[r][c] || matrix[r][c] == '0' || time > L) {
                continue;
            }
            visited[r][c] = true;
            cnt++;
            //시간이 L과 같다면 1 리턴

            switch(matrix[r][c]) {
                case '1':
                    if(!isOver(r - 1, c) && !visited[r-1][c] && "1256".contains(matrix[r - 1][c] + "")) queue.add(new int[] {r-1, c,time + 1 }); // 상
                    if(!isOver(r + 1, c) && !visited[r+1][c] && "1247".contains(matrix[r + 1][c] + "")) queue.add(new int[] {r+1, c,time + 1}); // 하
                    if(!isOver(r, c + 1) && !visited[r][c+1] && "1367".contains(matrix[r][c + 1] + "")) queue.add(new int[] {r, c + 1,time + 1}); // 우
                    if(!isOver(r, c - 1) && !visited[r][c-1] && "1345".contains(matrix[r][c - 1] + "")) queue.add(new int[] {r, c -1,time + 1}); // 좌
                    break;
                case '2':
                    if(!isOver(r - 1, c) && !visited[r-1][c] && "1256".contains(matrix[r - 1][c] + "")) queue.add(new int[] {r-1, c,time + 1}); // 상
                    if(!isOver(r + 1, c) && !visited[r+1][c] && "1247".contains(matrix[r + 1][c] + "")) queue.add(new int[] {r+1, c,time + 1}); // 하
                    break;
                case '3':
                    if(!isOver(r, c + 1) && !visited[r][c+1] && "1367".contains(matrix[r][c + 1] + "")) queue.add(new int[] {r, c + 1,time + 1}); // 우
                    if(!isOver(r, c - 1) && !visited[r][c-1] && "1345".contains(matrix[r][c - 1] + "")) queue.add(new int[] {r, c -1,time + 1}); // 좌
                    break;
                case '4':
                    if(!isOver(r - 1, c) && !visited[r-1][c] && "1256".contains(matrix[r - 1][c] + "")) queue.add(new int[] {r-1, c,time + 1}); // 상
                    if(!isOver(r, c + 1) && !visited[r][c+1] && "1367".contains(matrix[r][c + 1] + "")) queue.add(new int[] {r, c + 1,time + 1}); // 우
                    break;
                case '5':
                    if(!isOver(r, c + 1) && !visited[r][c+1] && "1367".contains(matrix[r][c + 1] + "")) queue.add(new int[] {r, c + 1,time + 1}); // 우
                    if(!isOver(r + 1, c) && !visited[r+1][c] && "1247".contains(matrix[r + 1][c] + "")) queue.add(new int[] {r+1, c,time + 1}); // 하
                    break;
                case '6':
                    if(!isOver(r + 1, c) && !visited[r+1][c] && "1247".contains(matrix[r + 1][c] + "")) queue.add(new int[] {r+1, c,time + 1}); // 하
                    if(!isOver(r, c - 1) && !visited[r][c-1] && "1345".contains(matrix[r][c - 1] + "")) queue.add(new int[] {r, c -1,time + 1}); // 좌
                    break;
                case '7':
                    if(!isOver(r - 1, c) && !visited[r-1][c] && "1256".contains(matrix[r - 1][c] + "")) queue.add(new int[] {r-1, c,time + 1}); // 상
                    if(!isOver(r, c - 1) && !visited[r][c-1] && "1345".contains(matrix[r][c - 1] + "")) queue.add(new int[] {r, c -1,time + 1}); // 좌
                    break;
            }
        }
        answer = cnt;
    }
}
