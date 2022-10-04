import java.util.StringTokenizer;

class Solution {
    
    public int solution(int n, int k) {
        int answer = 0;
        String s=ntoK(n,k);
        StringTokenizer st = new StringTokenizer(s, "0");
		while(st.hasMoreTokens()) {
			long cur=Long.parseLong(st.nextToken());
			if(isPrime(cur))
				answer++;
		}
        return answer;
    }
    
    public static boolean isPrime(long n) {
		if(n<2) return false;
		
		else if(n==2 || n==3) return true;
		
		for(int i=2;i<=Math.sqrt(n);i++) {
			if(n%i==0)
				return false;
		}
		return true;
	}
	
	public static String ntoK(int n, int k) {
		String s="";
		while(n>0) {
			s=n%k+s;
			n=n/k;
		}
		return s;
	}
}