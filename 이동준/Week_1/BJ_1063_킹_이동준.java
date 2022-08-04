import java.util.Scanner;

/**
 * BJ_1063_킹
 * 
 * 8*8 크기의 체스판에 왕이 하나 있다.
 * 
 * 알파벳하나와 숫자 하나로 이뤄져있는데 
 * 알파벳은 열(가장 왼쪽이 A, 가장 오른쪽 H), 숫자는 행(가장아래 1, 가장 위 8)
 * 킹은 다음과 같이 움직일 수 있다.
	
	R : 한 칸 오른쪽으로
	L : 한 칸 왼쪽으로
	B : 한 칸 아래로
	T : 한 칸 위로
	RT : 오른쪽 위 대각선으로
	LT : 왼쪽 위 대각선으로
	RB : 오른쪽 아래 대각선으로
	LB : 왼쪽 아래 대각선으로
	
 * @author djunnni
 *
 */
public class Main {
	static int dx[] = { 1, -1, 0, 0, 1, -1, 1, -1 }; // 열
	static int dy[] = { 0, 0, -1, 1, 1, 1, -1, -1 }; // 행
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		String king = sc.next();
		String stone = sc.next(); 
		int moving = sc.nextInt();
		
		for(int i = 0; i < moving; i++) {
			String input = sc.next();
			int key = -1;
			switch(input) {
				case "R":
					key = 0;
					break;
				case "L":
					key = 1;
					break;
				case "B":
					key = 2;
					break;
				case "T":
					key = 3;
					break;
				case "RT":
					key = 4;
					break;
				case "LT":
					key = 5;
					break;
				case "RB":
					key = 6;
					break;
				case "LB":
					key = 7;
					break;
			}
			String temp_king = king;
			String temp_stone = stone;
			king = move(king.charAt(0), king.charAt(1), key);
			if(king == "") {
				king = temp_king;
				continue;
			} else {
				if(stone.equals(king)) {
					stone = move(stone.charAt(0), stone.charAt(1), key);
					if(stone == "") {
						king = temp_king;
						stone = temp_stone;
						continue;
					}
				}
			}
		}

		System.out.printf("%s\n%s", king, stone);
	}
	public static String move(char x, char y, int key) {
		char n_x = (char)(x + dx[key]);
		char n_y = (char)(y + dy[key]);
		
		if(n_x < 'A' || n_x > 'H' || n_y < '1' || n_y > '8') {
			return "";
		}
		return n_x + "" +n_y;
	}
}
