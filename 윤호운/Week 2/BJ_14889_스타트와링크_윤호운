import java.io.*;
import java.util.*;

//과거에 이문제를 풀엇지만 다시봐도 어떻게 이렇게 풀었는지 모르겠다~
//지금 이문제를 푼다면 조합 메서드 써서 스타트팀이랑 링크팀 차이 작은거 찾는식으로 풀거같당~!
public class BJ_14889_스타트와링크_윤호운{
    static int[][] arr;
    static int n;
    static int ans=Integer.MAX_VALUE;

    // solve (현재 인덱스: idx, start 팀원들의 인덱스가 담긴 arraylist: start , link 팀원들의 인덱스가 담긴 arraylist: link)
    public static void solve(int idx,ArrayList<Integer> start,ArrayList<Integer> link){
    	System.out.println(idx);
    	System.out.println("start:"+start);
    	System.out.println("link: "+link);
    	System.out.println();
    	
    	
    	//만약 전달받은 각 arraylist의 사이즈가 n/2보다 크다면 리턴
    	if(start.size()>n/2||link.size()>n/2){  
            return;
        }
    	//idx가 n 이라면 : n/2명 씩 두 팀으로 다 나누었다면
        if (idx==n){
        	
            int sum1=0;
            int sum2=0;
            // start 팀 능력치 합 구하기
            for(int i=0;i<start.size();i++){
                for(int j=0;j<start.size();j++){
                    sum1+=arr[start.get(i)][start.get(j)];
                }
            }
            // link 팀 능력치 합 구하기
            for(int i=0;i<link.size();i++){
                for(int j=0;j<link.size();j++){
                    sum2+=arr[link.get(i)][link.get(j)];
                }
            }
            // 최소값 구하기
            int result= Math.abs(sum1-sum2);
            if(result<ans)
                ans=result;
            return;

        }
        //일단 start팀에 idx 삽입
        start.add(idx);
        //다음 idx (idx+1)로 solve 호출 => start팀 구성
        solve(idx+1,start,link);
        // 사이즈 이슈로 리턴받았을거기 때문에  start 팀 한명 빼주기
        start.remove(start.size()-1);
        
        // 재귀 특성 이용 -> 위에서 뺀 팀원을 link에 삽입
        link.add(idx);
        // 다음 idx(idx+1) 로 solve 호출
        solve(idx+1,start,link);
        // 사이즈 이슈문제 처리
        link.remove(link.size()-1);


    }

  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());  
        arr=new int[n][n];  

        for(int i=0;i<n;i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        solve(0,new ArrayList<Integer>(),new ArrayList<Integer>());

       System.out.println(ans);


    }  
}




