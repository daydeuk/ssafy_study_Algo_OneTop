

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * n*n 맵에서 두명의 일꾼이 채취가능한 수익의 경우의 수를 sum 배열에 저장해놓고
 * 최댓값을 찾았다~
 * 
 * @author 호우니
 *
 */
public class SW_2115_벌꿀채취_윤호운 {
	static int n,m,c;
	static int[][] map;
	static int ans;
	static int tmax=-1;
	

	public static void comb(int[] arr, int r, int[] output, int depth,int start,int sum) {
		//꿀의 양이 c보다 크면 안댐 탈출!
		if(sum>c)
			return;
    
		//조합 구했으면 
		if(depth==r) {
			int temp =0;
      //수익구해서 
			for(int i=0;i<r;i++) {
				temp+=Math.pow(output[i],2);
			}
			//선택한 벌통의 최대수익 tmax 업데이트
			if(tmax<temp)
				tmax=temp;
			
			return;
		}
    
		for(int i=start;i<arr.length;i++) {
			output[depth]=arr[i];
			comb(arr,r,output,depth+1,i+1,sum+arr[i]);
		}
	}
	
	public static int solve(int first,int last,int row) { //열 시작번호, 열 끝 번호, 행번호
		//인덱스 first~last인 길이가 m인 벌통
		int[] temp=Arrays.copyOfRange(map[row], first, last);
		
		//벌통에서 선택가능한 모든 경우의 수 탐색 => 부분집합 구하깅 (공집합제외)
		for(int i=1;i<=temp.length;i++) {
			comb(temp,i,new int[i],0,0,0);
		}

		if(tmax==-1) //해당 행의 꿀 획득 불가임
			return 0;
		return tmax;
		
	}
	public static void main(String[] args) throws IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringBuilder sb = new StringBuilder();
	      int tc = Integer.parseInt(br.readLine());
	      for(int t=1;t<=tc;t++) {
	    	  StringTokenizer st= new StringTokenizer(br.readLine());
		      n = Integer.parseInt(st.nextToken()); //맵 길이
		      m = Integer.parseInt(st.nextToken()); //벌통 길이
		      c = Integer.parseInt(st.nextToken()); //꿀 최대양
		      
          //수익값을 저장해놓을 sum 배열
		      //일꾼은 가로로 m길이만큼 꿀을 채취해야하기 때문에 수익값을 저장해놓을 sum 배열의 크기는  n행, n-m+1 열 
		      int[][] sum = new int[n][n-m+1];
		      map = new int[n][n];
		      
		      //입력받기
		      for(int i=0;i<n;i++) {
		    	   st= new StringTokenizer(br.readLine());
		    	  for(int j=0;j<n;j++) {
		    		  map[i][j]= Integer.parseInt(st.nextToken());
		    	  }
		      }
		      
		      // 1. 두명의 일꾼이 다른 행에서 꿀을 획득할 경우 (행마다 최대 수익을 구한다음에  그 값들 중, 첫번째로큰값+두번째로큰값이 정답)
		      int max1=0; 
		      int max2=0;
		      for(int i=0;i<n;i++) {
		    	  int max=0;
		    	  for(int j=0;j<n-m+1;j++) {
		    		  //해당 위치의 수익 저장해 놓기
		    		  sum[i][j]=solve(j,j+m,i);
              // i 행의 최대수익 업데이트
		    		  if(sum[i][j]>max) { 
		    			  max=sum[i][j]; 
		    		  }
		    		  tmax=-1; //전역변수 tmax값 초기화
		    	  }
		    	  
		    	  // 행 들의 수익중 최대수익 과 그 다음 최대수익 구하기
		    	  if(max>max1) {
		    		  max2=max1;
		    		  max1=max;
		    	  } else if(max>max2) {
		    		  max2=max;
		    	  }
		      }
		      
		      ans = max1+max2;
		      
		      // 2. 두 명의 일꾼이 같은 행에서 꿀을 획득할 경우
		      for(int i=0;i<n;i++) {
		    	  for(int j=0;j<n-m+1;j++) {
		    			 if(j+m+m<=n) { // 두명이 한 행에서 수확할 수 있는 경우만 고려
		    				 int first = sum[i][j];
		    				 int second = sum[i][j+m];
		    				 
		    				 //정답 업데이트
		    				 if(first+second>ans)
		    					 ans = first+second;
		    			 }
		    	  }
		      }

		      sb.append("#"+t+" "+ans+"\n");
	      }

	      System.out.println(sb);
	          

	}
}
