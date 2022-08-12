import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * BJ_14889_스타트와링크
 * 제한시간 2초, 메모리 제한 512MB
 * 
 * 소요시간: 20분
 * 
 * @author djunnni
 *
 */
public class BJ_14889_스타트와링크_이동준 {
	public static int N, answer;
	public static char[] teams;
	public static int[][] matrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// teams 배열은 해당 인덱스 선수가 어느 팀에 속하는지 체크하기 위함.
		teams = new char[N];
		// 배열 초기화
		matrix = new int[N][N];
		for(int i = 0; i < N; i++) {
			matrix[i] = Stream.of(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();	
		}
		answer = Integer.MAX_VALUE;
		
		teamSelect(0, 0);
		
		System.out.println(answer);
	}
	// 1. 조합으로 2명 뽑기
	// 2. 뽑힌 사람의 index, 안뽑힌 사람들의 index를 통해서 능력치를 비교합니다.
	// 3. 능력치가 최소일 때, answer에 업데이트 합니다.
	public static void teamSelect(int cnt, int start) {
		if(cnt >= N / 2) {
			int[] teamIndexes = new int[N];
			int l_team = 0;
			int s_team = N / 2;
			
			for(int i = 0; i < N; i++) {
				if(teams[i] == 'l') {
					teamIndexes[l_team++] = i;
				} else {
					teamIndexes[s_team++] = i;
				}
			}
			l_team = 0;
			s_team = 0;
			for(int i = 0; i < N / 2; i++) {
				for(int j = i + 1; j < N / 2; j++) {
					l_team += matrix[teamIndexes[i]][teamIndexes[j]] + matrix[teamIndexes[j]][teamIndexes[i]];
				}
			}
			for(int i = N / 2; i < N; i++) {
				for(int j = i + 1; j < N; j++) {
					s_team += matrix[teamIndexes[i]][teamIndexes[j]] + matrix[teamIndexes[j]][teamIndexes[i]];
				}
			}
			// N / 2명이 뽑힌 상황 -> 나머지  N / 2명을 알 수 있으니 그걸 가지고 능력치를 구한다.
			answer = Math.min(answer, Math.abs(s_team - l_team));
			return;
		}
		
		// 뽑히는 사람은 'link' 팀에 속한다.
		for(int i = start; i < N; i++) {
			teams[i] = 'l';
			teamSelect(cnt + 1, i + 1);
			teams[i] = ' ';
		}
	}

}
