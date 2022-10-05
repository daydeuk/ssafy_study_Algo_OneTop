class Solution {
     public boolean isPrime(long n){
            if(n==1)
                return false;
	        for(int i=2;i<=Math.sqrt(n);i++){
	            if(n%i==0)
	                return false;
	        }
	        return true;
	    }
	    public int solution(int n, int k) {
	        int answer = 0;

	        //k진수 변환할때 이렇게
	        String temp = Integer.toString(n,k);
	        String[] arr = temp.split("0");
	        
	        
	        for(int i=0;i<arr.length;i++) {
	        	if(!arr[i].equals("")) {
	        		if(isPrime(Long.parseLong(arr[i]))) //되돌ㄹ릴때는 걍 이렇게 
	        			answer++;
	        	}
	        }
	        
	        
	        return answer;
	    }
}
