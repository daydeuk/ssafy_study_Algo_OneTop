import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * author djunnni
 */
public class Solution {
    static int[][] map;
    static int[] dx = { -1, 1, 0, 0 }; // 좌, 우, 하, 상
    static int[] dy = { 0, 0, 1, -1 };
    static int answer, N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        // 테스트케이스 개수
        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            // 지도 한 변의 길이 N
            N = Integer.parseInt(st.nextToken());
            // 최대 공사 가능 깊이 K
            K = Integer.parseInt(st.nextToken());

            // 지도 크기 N x N
            map = new int[N][N];

            // 봉우리 리스트
            List<int[]> montblacs = new ArrayList<>();
            // 봉우리 최대높이
            int max = 0;

            // 지도에 데이터 넣기
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // max보다 더 큰 봉우리를 찾으면 현재까지 찾은 내역 clear 후 재 삽입
                    if(max < map[i][j]) {
                        montblacs.clear();
                        montblacs.add(new int[] {i, j});
                        max = map[i][j];
                    } else if(max == map[i][j]) {
                        // max와 같으면 추가 삽입.
                        montblacs.add(new int[] {i, j});
                    }
                }
            }

//            // 지도 출력
//            for(int [] row : map) {
//                System.out.println(Arrays.toString(row));
//            }
//            System.out.println("=======================");
//
//            // 봉우리 출력
//            for(int[] montblac : montblacs) {
//                System.out.println(Arrays.toString(montblac));
//            }
//            System.out.println("=======================");

            answer = 0;
            // 봉우리에서부터 내려가기
            for(int[] montblac : montblacs) {
                int[][] copyMap = copyOfMap(map);
                boolean[][] visited = new boolean[N][N];
                dfs(copyMap, visited, montblac[0], montblac[1], 1, false);

            }

            out.append("#").append(testCase).append(" ").append(answer).append("\n");
        }
        System.out.println(out);
    }
    /**
     * 4방 탐색을 실시한다.
     * 만약 상하좌우 중 값이 현재 위치보다 작다면 해당 방향으로 dfs를 실시한다.
     * 만약 현재위치와 동일한 높이거나 더 큰 값을 만났을 때, 딱 한곳에 최대 깊이 K만큼 깎는 공사를 할 수 있다.
     * // 즉, 0부터 K만큼 높이를 깎아가는 작업 후 DFS 수행
     */
    public static void dfs(int[][] map, boolean[][] visited, int r, int c, int cnt, boolean already) {
        visited[r][c] = true;
//        for(int [] row : map) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println("=======================");
//        for(boolean [] row : visited) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println("=======================");

        boolean canGo = false;
        for(int i = 0; i < dx.length; i++) {
            int nextR = r + dy[i];
            int nextC = c + dx[i];

            // 범위를 벗어나면 다음 스팟을 확인.
            if(isOver(nextR, nextC) || visited[nextR][nextC]) {
                continue;
            }
            // 현재 위치보다 작은 값이면 해당 위치로 탐색
            if(map[nextR][nextC] < map[r][c]) {
                canGo = true;
                dfs(map, visited, nextR, nextC, cnt + 1, already || false);
            } else {
                // 현재 위치와 값이 같거나 클 경우 k만큼 줄여서 dfs 진행해보기
                for(int k = 1; k <= K && !already; k++) {
                    map[nextR][nextC] -= k;
                    if(map[nextR][nextC] >= 0 && map[nextR][nextC] < map[r][c]) {
                        canGo = true;
                        dfs(map, visited, nextR, nextC, cnt + 1, already || true);
                    }
                    map[nextR][nextC] += k;
                }
            }
        }
        if(!canGo) {
            answer = Math.max(answer, cnt);
//            System.out.println(answer);
//            System.out.println("================ end ==============");
        }
        visited[r][c] = false;
    }
    public static boolean isOver(int r, int c) {
        if(r >= N || c >= N || r < 0 || c < 0) {
            return true;
        }
        return false;
    }
    public static int[][] copyOfMap(int[][] map) {
        int[][] temp = new int[N][N];

        for(int i = 0; i < map.length; i++) {
            temp[i] = Arrays.copyOf(map[i], N);
        }

        return temp;
    }
}
