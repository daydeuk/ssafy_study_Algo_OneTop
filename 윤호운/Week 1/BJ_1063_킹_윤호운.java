package ps.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Index {
	int i;
	int j;
	Index(int i, int j){
		this.i=i;
		this.j=j;
	}
}
public class BJ_1063_킹_윤호운 {
	public static Index king;
	public static Index rock;
	public static void solve(String order) {
		switch(order) {
		case "R": //한칸 오른 쪽
			if(king.j<7) {
				if(king.j+1==rock.j && king.i==rock.i) { //킹이 돌과 같은 곳으로 이동할때
					if(rock.j+1>7)  //돌이 떨어지면 break
						break;
					rock.j++; //돌이동
				}
				king.j++; //킹 이동
			}
			break;
		case "L": //한 칸 왼쪽 
			if(king.j>0) {
				if(king.j-1==rock.j && king.i==rock.i) { //킹이 돌과 같은 곳으로 이동할때
					if(rock.j-1<0)  //돌이 떨어지면 break
						break;
					rock.j--; //돌이동
				}
				king.j--; //킹 이동
			}
			break;
		case "B": //한칸 아래로
			if(king.i<7) {
				if(king.i+1==rock.i && king.j==rock.j) { //킹이 돌과 같은 곳으로 이동할때
					if(rock.i+1>7)  //돌이 떨어지면 break
						break;
					rock.i++; //돌이동
				}
				king.i++; //킹 이동
			}
			break;
		case "T": // 한칸 위로
			if(king.i>0) {
				if(king.i-1==rock.i && king.j==rock.j) { //킹이 돌과 같은 곳으로 이동할때
					if(rock.i-1<0)  //돌이 떨어지면 break
						break;
					rock.i--; //돌이동
				}
				king.i--; //킹 이동
			}
			break;
		case "RT": // 오른쪽 위 대각선
			if(king.i>0&&king.j<7) {
				if(king.j+1==rock.j && king.i-1==rock.i) { //킹이 돌과 같은 곳으로 이동할때
					if(rock.j+1>7 || rock.i-1<0)  //돌이 떨어지면 break
						break;
					rock.j++; //돌이동
					rock.i--;
				}
				king.j++; //킹 이동
				king.i--;
			}
			break;
		case "LT": // 왼쪽 위 대각선
			if(king.i>0&&king.j>0) {
				if(king.j-1==rock.j && king.i-1==rock.i) { //킹이 돌과 같은 곳으로 이동할때
					if(rock.j-1<0 || rock.i-1<0)  //돌이 떨어지면 break
						break;
					rock.j--; //돌이동
					rock.i--;
				}
				king.j--; //킹 이동
				king.i--;
			}

			break;
		case "RB": // 오른쪽 아래 대각선
			if(king.i<7&&king.j<7) {
				if(king.j+1==rock.j && king.i+1==rock.i) { //킹이 돌과 같은 곳으로 이동할때
					if(rock.j+1>7 || rock.i+1>7)  //돌이 떨어지면 break
						break;
					rock.j++; //돌이동
					rock.i++;
				}
				king.j++; //킹 이동
				king.i++;
			}

			break;
		case "LB": //왼쪽 아래 대각선
			if(king.i<7&&king.j>0) {
				if(king.j-1==rock.j && king.i+1==rock.i) { //킹이 돌과 같은 곳으로 이동할때
					if(rock.j-1<0 || rock.i+1>7)  //돌이 떨어지면 break
						break;
					rock.j--; //돌이동
					rock.i++;
				}
				king.j--; //킹 이동
				king.i++;
			}
			break;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    
		StringTokenizer st = new StringTokenizer(br.readLine());
		//킹과 돌의 좌표를 담는 클래스 만들어서 객체 생성
		String temp= st.nextToken();
		king = new Index(8-(temp.charAt(1)-'0'),temp.charAt(0)-'A');
		temp= st.nextToken();
		rock = new Index(8-(temp.charAt(1)-'0'),temp.charAt(0)-'A');
		
		int move = Integer.parseInt(st.nextToken());
		
		//명령어 처리
		for(int i=0;i<move;i++) {
			solve(br.readLine());
		}

		System.out.println((char)(king.j+'A')+""+(8-king.i));
		System.out.println((char)(rock.j+'A')+""+(8-rock.i));
		

	}
}
