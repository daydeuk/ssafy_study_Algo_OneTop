import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Collections;

class Solution {
    	public static class Car implements Comparable<Car>{
		String time;
		String number;
		String state;
		int cost;
		int checkTime;
		public Car() {
		}
		public Car(String time, String number, String state) {
			this.time = time;
			this.number = number;
			this.state = state;
		}
		@Override
		public int compareTo(Car o) {
			return this.number.compareTo(o.number);
		}
	};

	public static int[] solution(int[] fees, String[] records) {
		int[] answer = {};
//		System.out.println(fees[0]); // 기본시간
//		System.out.println(fees[1]); // 기본요금
//		System.out.println(fees[2]); // 단위시간
//		System.out.println(fees[3]); // 단위요금
		List<Car> carList=new ArrayList<>();
		
		for(int i=0;i<records.length;i++) {
			StringTokenizer st = new StringTokenizer(records[i], " ");
			// 시각 차량번호 내역
			String time=st.nextToken();
			String number=st.nextToken();
			String state=st.nextToken();
			
			boolean flag=true;
			if(state.equals("IN")) {
				for(Car c:carList) {
					if(c.number.equals(number)) {
						flag=false;
						c.time=time;
						c.state="IN";
						break;
					}
				}
				if(flag)
					carList.add(new Car(time,number,state));
			} else if(state.equals("OUT")) {
				for(Car c:carList) {
					if(c.number.equals(number)){
						// 출차 하는 순간.
						st= new StringTokenizer(c.time,":");
						int startHour=Integer.parseInt(st.nextToken());
						int startMinute=Integer.parseInt(st.nextToken());
						st= new StringTokenizer(time,":");
						int endHour=Integer.parseInt(st.nextToken());
						int endMinute=Integer.parseInt(st.nextToken());
						int checkTime=endHour*60+endMinute-(startHour*60+startMinute);
						c.checkTime+=checkTime;
						c.state="OUT";
					}
				}
			}
		}
		answer=new int[carList.size()];
		int count=0;
		Collections.sort(carList);
		for(Car c:carList) {
//			System.out.println(c.number);
			// 입차만 하고 출차를 안한 경우
			if(c.state.equals("IN")) {
				StringTokenizer st= new StringTokenizer(c.time,":");
				int startHour=Integer.parseInt(st.nextToken());
				int startMinute=Integer.parseInt(st.nextToken());
				int endHour=Integer.parseInt("23");
				int endMinute=Integer.parseInt("59");
				int checkTime=endHour*60+endMinute-(startHour*60+startMinute);
				c.checkTime+=checkTime;
				c.state="OUT";
			}
		}
		for(Car c:carList) {
			if(c.checkTime<=fees[0]) {
				c.cost+=fees[1];
				c.state="OUT";
			} else {
				c.cost+=fees[1];
				c.checkTime-=fees[0];
				while(c.checkTime>0) {
					c.checkTime-=fees[2];
					c.cost+=fees[3];
				}
			}
			answer[count]=c.cost;
			count++;
		}
		return answer;
	}
}