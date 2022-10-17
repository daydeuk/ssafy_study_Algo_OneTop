import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, W, H, arr[][], visited[], min_val,
    dx[] = {-1, 1, 0, 0},
    dy[] = {0, 0, -1, 1}
    ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int t = 1; t < TC + 1; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            arr = new int[H][W];
            visited = new int[W];
            min_val = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            perm(0, arr);

            System.out.printf("#%d %d\n", t, min_val);
//            bfs(1, 0, arr);
//            for (int[] ints : arr) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println();
//            bfs(2, 0, arr);
//            for (int[] ints : arr) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println();
//            for (int i = 0; i < W; i++) {
//                down(H, i, 0, arr);
//            }
//
//            for (int[] ints : arr) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println();
        }
    }

    public static void perm(int cnt, int[][] arr) {
        if (cnt == N) {
            min_val = Math.min(min_val, count(arr));

//            System.out.println();
//            for (int[] ints : arr) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println();
//            System.out.println("==============");
//            System.out.println();
            return;
        }

        int[][] newArr = new int[H][W];
        for (int i = 0; i < W; i++) {
            copy(arr, newArr);
            shoot(i, newArr);
            visited[i] = 1;
//            System.out.println(Arrays.toString(visited) + (cnt + 1) + " 번째");
            perm(cnt + 1, newArr);
            visited[i] = 0;
        }
    }

    public static int count(int[][] arr) {
        int cnt = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (arr[i][j] > 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void copy(int[][] arr, int[][] newArr) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
    }

    public static void shoot(int p, int[][] arr) {
        int h = 0;

        while (true) { // 벽돌을 만날 때 까지..
            if (h == H) {
                break;
            }

            if (arr[h][p] > 0) {
                bfs(h, p, arr);
                for (int i = 0; i < W; i++) {
                    down(H, i, 0, arr);
                }
                break;
            }
            h++;
        }
    }

    public static void bfs(int r, int c, int[][] arr) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int range = arr[x][y];

            arr[x][y] = 0;

            if (range == 1) { // 폭발범위가 1인 경우
                continue;
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < range; j++) {
                    int nx = x + dx[i] * j;
                    int ny = y + dy[i] * j;
                    // 범위를 벗어나지 않고, 다음 벽돌이 있는경우에
                    if (isIn(nx, ny) && arr[nx][ny] >= 1) {
                        // 그 벽돌을 제거하고 큐에 넣는다.
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }


    // 가장 밑에서부터 0을 찾는다. 그 위치를 기준으로 1을 찾을때까지 위로 올라간다.
    // 1을 찾았다면 0과 swap!
    public static int down(int H, int y, int flag, int[][] arr) {
        for (int j = H - 1; j >= 0; j--) {
            if (arr[j][y] == 0 && flag == 0) {
                int idx = down(j, y, 1, arr);// 0 보다 한칸 위 부터 재귀..

                int tmp = arr[j][y];
                arr[j][y] = arr[idx][y];
                arr[idx][y] = tmp;
            }

            if (arr[j][y] > 0 && flag == 1) {
                return j;
            }
        }

        return 0;
    }

    public static boolean isIn(int nx, int ny) {
        if (0 <= nx && nx < H && 0 <= ny && ny < W) {
            return true;
        }
        return false;
    }
}

// 벽돌깨기
// 1. N번 구슬을 쏠 수 있다!
// 1-1. W개 중에서 N개를 뽑는다. 중복으로 뽑을 수 있으며, 순서에 상관있다. (중복 순열)

// 2. 구슬에 맞은 벽돌은 4방향으로 (숫자 - 1) 만큼 다른 벽돌을 제거시킨다. 이 때, 제거되는 벽돌은
// 또 다른 벽돌을 제거시킨다.
// 2-1. 벽돌에 구슬이 맞으면, 방문처리 후 해당 범위의 제거할 벽돌들을 맵에서 제거한 뒤 큐에 담는다.
// 큐에서 제거한 벽돌들을 뽑아낸다.
// 그 후에 벽돌을 방문처리 후 해당 범위의 제거할 벽돌을 맵에서 제거한 뒤 큐에 담는다.
// 지금 벽돌 방문처리 -> 4방향 탐색 -> 배열범위내의 반경의 벽돌들(0보다 큰) 제거 -> 제거한 벽돌들 큐에 담기

// 3. 연쇄작용이 끝나면, 해당 남아있는 벽돌들을 밑으로 떨어트린다.
// 3-1. 가장 밑에서부터 0을 찾는다. 그 위치를 기준으로 1을 찾을때까지 위로 올라간다.
// 1을 찾았다면 0과 swap!
// 그 다음 위치부터 또 0을 찾는다.. 반복

// 3. 반복
