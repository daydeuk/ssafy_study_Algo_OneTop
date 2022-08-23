import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SW_1952_수영장
 * 
 * @author djunnni
 *
 */
public class Solution {
	static int answer;
	static int ticket[]; // 이용권 가격
	static int month[]; // 매달 수영장 참여 횟수
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			ticket = new int[4]; // 입장권 이용료, 일, 달, 3개월, 1년
			month = new int[12]; // 매달 수영장 참여횟수
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < ticket.length; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}
			
			// 티켓 값 확인하기
//			System.out.println(Arrays.toString(ticket));
//			System.out.println("==============");
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < month.length; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			// 매월 참여수 확인하기
//			System.out.println(Arrays.toString(month));
//			System.out.println("==============");
			
			answer = Integer.MAX_VALUE;
			
			findMinimumCost(0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	public static void findMinimumCost(int m, int price) {
		if(m >= 12) {
			answer = Math.min(answer, price);
			return;
		}
		// 해당월에 참여 횟수를 확인합니다.
		if(month[m] == 0) {
			findMinimumCost(m + 1, price);
			// 참여횟수가 없다면 다음월로 넘어갑니다.
		} else {
			for(int i = 0; i < ticket.length; i++) {
			// 참여횟수가 있다면
				switch(i) {
					case 0:
						// 해당월에 1일권을 선택할 경우,
						findMinimumCost(m + 1, price + ticket[0] * month[m]);
						break;
					case 1:
						// 1달 권을 선택할 경우,
						findMinimumCost(m + 1, price + ticket[1]);
						break;
					case 2:
						// 3달 권을 선택할 경우,
						findMinimumCost(m + 3, price + ticket[2]);
						break;
					case 3:
						// 1년 권을 선택한 경우,
						findMinimumCost(m + 12, price + ticket[3]);
						break;
				}
			}
		}
	}
}
