import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * swexpert.SW_5653_줄기세포배양
 * author djunnni
 * 256MB, 0.6sec
 */
public class SW_5653_줄기세포배양_이동준 {
    static class Spot {
        int r, c, life, from;
        boolean status;

        Spot(int r, int c, int life) {
            this.r = r;
            this.c = c;
            this.life = life;
        }

        Spot(int r, int c, int life, int from) {
            this(r, c, life);
            this.from = from;
        }

        @Override
        public String toString() {
            return "Spot{" +
                    "r=" + r +
                    ", c=" + c +
                    ", life=" + life +
                    ", from=" + from +
                    ", status=" + status +
                    '}';
        }
    }

    static int answer, K;
    static Map<String, Spot> currentMap;
    static Map<String, Boolean> deadCellMap;
    static int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            // N, M, K
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); // 세로
            int M = Integer.parseInt(st.nextToken()); // 가로
            K = Integer.parseInt(st.nextToken()); // 배양 시간

            // 셀 맵 그리기
            currentMap = new HashMap<>();
            deadCellMap = new HashMap<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life > 0) {
                        currentMap.put(i + "," + j, new Spot(i, j, life, 0 + life));
                    }
                }
            }
            // 현재 가지고 있는 세포 출력해보기
            // System.out.println(cells);

            // 정답 초기화
            answer = 0;

            culture(0);

            answer = currentMap.size();

            System.out.println("#" + tc + " " + answer);
        }
    }

    public static void culture(int time) {
        // 정해진 배양시간을 넘으면 리턴
        if (time > K) {
            return;
        }
        HashMap<String, Spot> newCellsMap = new HashMap<>();
        List<String> removeCells = new ArrayList<>();
        Iterator iter = currentMap.keySet().iterator();
        while (iter.hasNext()) {
            String sKey = (String) iter.next();
            Spot s = currentMap.get(sKey);
            // 활성상태에서
            if (s.status && (s.from + s.life) == time) {
                removeCells.add(sKey);
                deadCellMap.put(s.r + "," + s.c, true);

                continue;
            }
            if (!s.status && time == s.from) {
                // 줄기세포 활성화
                s.status = true;

                // 4방으로 새로운 Cell 생성하기
                for (int i = 0; i < dr.length && time < K; i++) {
                    int nr = s.r + dr[i];
                    int nc = s.c + dc[i];
                    String nKey = nr + "," + nc;
                    // 이미 죽은 경우나 이미 있다면 continue;
                    if (deadCellMap.getOrDefault(nKey, false) || currentMap.getOrDefault(nKey, null) != null) {
                        continue;
                    }
                    Spot nSpot = newCellsMap.getOrDefault(nKey, null);
                    if (nSpot != null) {
                        // 이미 등록된 애가 있다면
                        if (nSpot.life < s.life) { // 등록된 애의 life가 번식할 애보다 크다면
                            nSpot.life = s.life;
                            nSpot.from = time + s.life + 1;
                            newCellsMap.put(nKey, nSpot);
                        }
                    } else {
                        // 등록된 애가 없다면
                        newCellsMap.put(nKey, new Spot(nr, nc, s.life, time + s.life + 1));
                    }
                }
            }
        }
        for (String _key : removeCells) {
            currentMap.remove(_key);
        }
        currentMap.putAll(newCellsMap);

        // System.out.println(time);
        // System.out.println(currentMap);
        // System.out.println("=============");

        culture(time + 1);

    }
}
