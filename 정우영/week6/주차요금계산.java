import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
class Solution {
    public int[] solution(int[] fees, String[] records) {
      ArrayList<String[]> list = new ArrayList<>();
		for (int i = 0; i < records.length; i++) {
			String[] info = records[i].split(":| ");
			list.add(info);
		}
		Collections.sort(list,new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				// TODO Auto-generated method stub
				if(Integer.valueOf(o1[2])==Integer.valueOf(o2[2])) {
		
					if(Integer.valueOf(o1[0])==Integer.valueOf(o2[0])) {
						
						return Integer.valueOf(o1[1]).compareTo(Integer.valueOf(o2[1]));
					}
					return Integer.valueOf(o1[0]).compareTo(Integer.valueOf(o2[0]));
				}
				return Integer.valueOf(o1[2]).compareTo(Integer.valueOf(o2[2]));
			}
		});
		String s=list.get(0)[2];
		int carNum=1;
		for(int i=1;i<list.size();i++) {
			if(!s.equals(list.get(i)[2])) {
				carNum++;
				s=list.get(i)[2];
			}
		}
		int []time=new int [carNum];
		int []ans=new int [carNum];
		Arrays.fill(time, 0);
		int idx=0;
		s=list.get(0)[2];
		int in =0;
		int out=0;
		int min=0;
		for(int i=0;i<list.size();i++) {
			if(!s.equals(list.get(i)[2])) {
				s=list.get(i)[2];
				idx++;
	
			}
			if("IN".equals(list.get(i)[3])) {
				in=Integer.parseInt(list.get(i)[0])*60+Integer.parseInt(list.get(i)[1]);
				if(i==list.size()-1) {
					out=23*60+59;
					min=out-in;
					time[idx]+=min;
				}else if(!s.equals(list.get(i+1)[2])) {
					out=23*60+59;
					min=out-in;
					time[idx]+=min;
				}
			}
			else if("OUT".equals(list.get(i)[3])) {
				out=Integer.parseInt(list.get(i)[0])*60+Integer.parseInt(list.get(i)[1]);
				min=out-in;
				time[idx]+=min;
			}
			
		}
		for(int i=0;i<carNum;i++) {
			ans[i]=fees[1];
			if(time[i]>fees[0]) {
				ans[i]+=((int)(Math.ceil((time[i]-fees[0])/(double)fees[2])) * fees[3]);
			}
		}
        return ans;
    }
}