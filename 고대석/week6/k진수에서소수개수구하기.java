class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = "";
        
        // 진수 변환
        while(n > 0) {
            s = (n % k) + s;
            n /= k;
        }
        
        // System.out.println(s);
        
        // 0 을 기준으로 문자열 자르기
        String[] arr = s.split("0");
        
        for(String num : arr) {
            if(num.equals("")) continue;
            long l = Long.parseLong(num);
            if(isPrime(l)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // 소수판별
    public boolean isPrime(long l) {
        if(l < 2) return false;
        if(l == 2) return true;
        if(l > 2) {
            for(int i = 2; i <= Math.sqrt(l); i++) {
                if(l % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}