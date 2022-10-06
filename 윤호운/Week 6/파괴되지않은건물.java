// 컨닝했음

class Solution {
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] cumul_skill = new int[board.length+1][board[0].length+1];
        
        
        for( int[] s : skill){
            int r1 =s[1];
            int c1= s[2];
            int r2 =s[3];
            int c2= s[4];
            switch(s[0]){
                case 1: // 적  - 낮춤
                  // board= solve(s[1],s[2],s[3],s[4],-s[5],board);
                    cumul_skill[r1][c1] += -s[5];
                    cumul_skill[r2+1][c2+1] += -s[5];
                    cumul_skill[r1][c2+1] += s[5];
                    cumul_skill[r2+1][c1] += s[5];
                    break;
                case 2: // 아군 - 높임
                  // board= solve(s[1],s[2],s[3],s[4],s[5],board);
                    cumul_skill[r1][c1] += s[5];
                    cumul_skill[r2+1][c2+1] += s[5];
                    cumul_skill[r1][c2+1] += -s[5];
                    cumul_skill[r2+1][c1] += -s[5];
                    break;
            }
        }
        
   
        
        //가로 누적합
            for(int i=0;i<board.length;i++){
                int sum =0;
                for(int j=0;j<board[0].length;j++){
                    sum += cumul_skill[i][j];
                    cumul_skill[i][j] = sum;
                }
            }
        
         //세로 누적합
            for(int i=0;i<board[0].length;i++){
                int sum =0;
                for(int j=0;j<board.length;j++){
                    sum += cumul_skill[j][i];
                    cumul_skill[j][i] = sum;
                }
            }
            
        // 파괴되지 않은 건물 구하기
          for(int i=0;i<board.length;i++){
 
                for(int j=0;j<board[0].length;j++){
                    if(board[i][j]+cumul_skill[i][j]>0)
                        answer++;
                }
            }
            
        return answer;
    }
}
