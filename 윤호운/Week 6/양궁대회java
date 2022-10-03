class Solution {
    static int[] answer = {-1};
    static int[] linfo = new int[11];
    static int max=1;

    public void solve(int n,int[] info,int cnt,int start){
        if(cnt==n){

            int lscore=0;
            int ascore=0;
            for(int i=0;i<=10;i++){
                if(linfo[i]==0 && info[i] ==0)
                    continue;
                    if(linfo[i]>info[i])
                        lscore+=(10-i);
                    else 
                        ascore+=(10-i);

            }
            if(lscore>ascore){
            if(lscore-ascore>max){
                    answer = linfo.clone();
                    max = lscore-ascore;
            }
            }


            return;

        }

        //완전탐색
        for(int i=start;i>=0;i--){
            linfo[i]++;
            solve(n,info,cnt+1,i);
            linfo[i]--;
        }


    } 

    public int[] solution(int n, int[] info) {
        //라이언이 무조건 지는 경우
        if(info[0]==n){
            return answer;
        }



        // 비기면 라이언이 진다
        solve(n,info,0,10);




        return answer;
    }
}
