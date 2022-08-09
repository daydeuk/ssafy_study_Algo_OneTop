import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1063_킹_신대득 {

	int[][] matrix = new int[9][9]; // 8*8 크기의 체스판
	static int kingR, kingC, rockR, rockC = 0;

	// 현재 r,c가 체스판 안인지 확인하는 메서드
	public static boolean check(int r, int c) {
		if (r < 1 || r > 8 || c < 1 || c > 8)
			return false;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		String king = st.nextToken();
		kingC = (int) king.charAt(0) - 64;
		kingR = (int) king.charAt(1) - 48;
		String rock = st.nextToken();
		rockC = (int) rock.charAt(0) - 64;
		rockR = (int) rock.charAt(1) - 48;
		int N = Integer.parseInt(st.nextToken());
		for (int n = 0; n < N; n++) {
			String s = in.readLine();
			switch (s) {
			case "R":// 오른쪽으로 (열++)
				// 돌과 킹이 움직일 곳이 체스판에 안인지 확인
				if (check(kingR, kingC + 1))
					kingC++;
				if (kingR == rockR && kingC == rockC) // 돌이 있는 곳이면
				{
					if (check(rockR, rockC + 1))
						rockC++;
					else
						kingC--;
				}
				break;
			case "L": // 왼쪽으로 (열 --)
				if (check(kingR, kingC - 1))
					kingC--;
				if (kingR == rockR && kingC == rockC) // 돌이 있는 곳이면
				{
					if (check(rockR, rockC - 1))
						rockC--;
					else
						kingC++;
				}
				break;
			case "B": // 아래쪽으로 (행 --)
				if (check(kingR - 1, kingC))
					kingR--;
				if (kingR == rockR && kingC == rockC) // 돌이 있는 곳이면
				{
					if (check(rockR - 1, rockC))
						rockR--;
					else
						kingR++;
				}
				break;
			case "T": // 위쪽으로 (행 --)
				if (check(kingR + 1, kingC))
					kingR++;
				if (kingR == rockR && kingC == rockC) // 돌이 있는 곳이면
				{
					if (check(rockR + 1, rockC))
						rockR++;
					else
						kingR--;
				}
				break;
			case "RT": // 오른쪽 위 대각선으로 (행++, 열++)
				if (check(kingR + 1, kingC + 1)) {
					kingR++;
					kingC++;
				}
				if (kingR == rockR && kingC == rockC) // 돌이 있는 곳이면
				{
					if (check(rockR + 1, rockC + 1)) {
						rockR++;
						rockC++;
					} else {
						kingR--;
						kingC--;
					}
				}
				break;
			case "LT": // 왼쪽 위 대각선으로 (행++, 열--)
				if (check(kingR + 1, kingC - 1)) {
					kingR++;
					kingC--;
				}
				if (kingR == rockR && kingC == rockC) // 돌이 있는 곳이면
				{
					if (check(rockR + 1, rockC - 1)) {
						rockR++;
						rockC--;
					} else {
						kingR--;
						kingC++;
					}
				}
				break;
			case "RB": // 오른쪽 아래 대각선으로 (행--, 열++)
				if (check(kingR - 1, kingC + 1)) {
					kingR--;
					kingC++;
				}
				if (kingR == rockR && kingC == rockC) // 돌이 있는 곳이면
				{
					if (check(rockR - 1, rockC + 1)) {
						rockR--;
						rockC++;
					} else {
						kingR++;
						kingC--;
					}
				}
				break;
			case "LB": // 왼쪽으로 아래 대각선으로 (행--, 열--)
				if (check(kingR - 1, kingC - 1)) {
					kingR--;
					kingC--;
				}
				if (kingR == rockR && kingC == rockC) // 돌이 있는 곳이면
				{
					if (check(rockR - 1, rockC - 1)) {
						rockR--;
						rockC--;
					} else {
						kingR++;
						kingC++;
					}
				}
				break;
			}
		}
		// 65(A)가 1임!
		System.out.printf("%c%d\n", kingC + 64, kingR);
		System.out.printf("%c%d\n", rockC + 64, rockR);
	}

}
