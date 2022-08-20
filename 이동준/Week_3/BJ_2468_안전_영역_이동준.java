import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ_2468_안전영역
 * 1sec, 128mb
 * author djunnni
 */
public class BJ_2468_안전_영역_이동준 {
    public static int matrix[][], answer;
    public static int[][] dim = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 지역의 행과 열의 개수 N
        int N = Integer.parseInt(br.readLine());

        // 지도를 N^2로 초기화
        matrix = new int[N][N];
        // 최대 높이를 미리 찾아놓는다.
        int max = 0;
        // 지도에 높이들을 넣습니다.
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(max < matrix[i][j]) {
                    max = matrix[i][j];
                }
            }
        }

//        배열을 출력합니다.
//        for(int[] row : matrix) {
//            System.out.println(Arrays.toString(row));
//        }

        // 최대값을 구하기 위해 최소로 변경해둠.
        answer = Integer.MIN_VALUE;

        /**
         * 문제 접근 방법
         * 인접행렬로 침수지역을 체크하고 BFS로 돌면서 살아있는 노드들을 묶는다.
         */
        for(int level = 0; level <= max; level++) {
            // 침수지역 인접행렬 만들기
            boolean isFlood[][] = new boolean[N][N];
            boolean isVisited[][] = new boolean[N][N];
            int count = 0;
            // 침수지역 체크
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(matrix[i][j] <= level) isFlood[i][j] = true;
                }
            }


            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!isFlood[i][j] && !isVisited[i][j]) {
                        bfs(isFlood, isVisited, j, i);
                        count++;
                    }
                }
            }
            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }
    public static void bfs(boolean[][] isFlood, boolean[][] isVisited, int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY});
        while(!queue.isEmpty()) {
            int[] spot = queue.poll();
            int y = spot[1];
            int x = spot[0];
            if(y >= 0 && x >= 0 && x < isFlood.length && y < isFlood.length && !isVisited[y][x] && !isFlood[y][x]) {
                isVisited[y][x] = true;
                for(int d = 0; d < dim.length; d++) {
                    int nextX = x + dim[d][0];
                    int nextY = y + dim[d][1];
                    queue.add(new int[] {nextX, nextY});
                }

            }

        }
    }
}
