class Solution {
    public long solution(int n, int k) {
       String binary="";
		
		while(n>0) {
			
			if(n%k==0) {
				binary+="0";
			}
			else {
				binary+=Integer.toString(n%k);
			}
			n/=k;
		}
		String rev=new StringBuffer(binary).reverse().toString();
		String [] number = rev.split("0");
		//int max=0;
		int cnt=0;
		for(int i=0;i<number.length;i++) {
			if(number[i].equals("")) {
				continue;
			}
			long a= Long.parseLong(number[i]);
			boolean flag=false;
			if(a==1) {
				continue;
			}
			if(a==2) {
				cnt++;
				continue;
			}
			for(int j=2;j<=Math.sqrt(a);j++) {
				
				if(a%j==0) {
					flag=true;
					break;
				}
			}
			if(!flag) {
				cnt++;
			}
			
		}
        return cnt;
    }
}