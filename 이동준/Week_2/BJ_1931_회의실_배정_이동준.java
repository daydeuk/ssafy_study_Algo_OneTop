import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * BJ_1931_회의실_배정
 *
 * 2초 128MB;
 *
 * 데이터를 정렬 -> OK
 * 최선의 조건을 선택하려고 한 점 -> OK
 *
 * => 기준을 잘못잡은점 반성하기
 *
 * author djunnni
 */
public class Main {
    public static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(end == o.end) {
                return Integer.compare(start, o.start);
            }
            return Integer.compare(end, o.end);
        }
    }
    public static Meeting [] meetings;
    public static int answer, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        meetings = new Meeting[N];

        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end);
        }

        Arrays.sort(meetings);
        Meeting meet = meetings[0];
        answer++;
        for(int i = 1; i < N; i++) {
            if(meet.end <= meetings[i].start) {
                meet = meetings[i];
                answer++;
            }
        }

        System.out.println(answer);
    }
}
