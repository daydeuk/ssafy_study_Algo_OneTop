package ws;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

class Flower implements Comparable<Flower>{
	int sm,sd,em,ed;

	public Flower(int sm, int sd, int em, int ed) {
		this.sm = sm;
		this.sd = sd;
		this.em = em;
		this.ed = ed;
	}

	@Override // 일단 시작 날짜 빠른 순으로 정렬
	public int compareTo(Flower o) {
		if(this.sm==o.sm) {
			return this.sd-o.sd; 
		}
		return this.sm-o.sm;
	}
	
}
//브루트포스
public class BJ_2457_공주님의정원_윤호운 {
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
	    //꽃 담을 어레이 리스트
	    ArrayList<Flower> flowers = new ArrayList<Flower>();
	    
	    for(int i=0;i<n;i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int sm =Integer.parseInt(st.nextToken());
	    	int sd =Integer.parseInt(st.nextToken());
	    	int em = Integer.parseInt(st.nextToken());
	    	int ed = Integer.parseInt(st.nextToken());
	    	
	    	if(em<3||(em==3&&ed==1)||sm>11) //꽃이 3월 1일이나 이전에 지거나, 12월에 피면 무시 필요없음~
	    		continue;
	    	flowers.add(new Flower(sm,sd,em,ed));
	    }
	    
	    Collections.sort(flowers); //일단 정렬
	    
	    Stack<Flower> s = new Stack<Flower>(); //안만들어도 될거같지만 만듦  
	    
	    Flower temp=null;
	   
	    for(int i=0;i<flowers.size();i++) {
	    	int sm =flowers.get(i).sm;
	    	int sd = flowers.get(i).sd;
	    	int em = flowers.get(i).em;
	    	int ed = flowers.get(i).ed;
	    	
	    	if(i==0) {
	    		if(sm>3 ||(sm==3&&sd>1) ) { //처음 꽃이 3월 1일 이후에 피면... 안댐
	    			System.out.println(0);
	    			return;
	    		}
	    		else {
	    			s.add(flowers.get(i)); //일단 맨처음꺼 넣어
	    			if(em==12) { // 12월까지 피면 성공
						break;
					}
	    		}
	    		continue;
	    	}
	    	
	    	Flower now = s.peek();
	    	if(sm<3||(sm==3&&sd==1)) { //맨 처음 꽃 세팅 (맨 처음 꽃은 언제피든 상관없이(입력받을때 이미 처리해줫음), 늦게만 지면됨)
	    		if(em>now.em || (em==now.em&&ed>now.ed)) {  //3월 1일이나 이전에 피는 꽃중에 제일 늦게 지는 꽃을 심어야함
	    			s.pop();
	    			s.push(flowers.get(i));
	    			if(em==12) {
						break;
					}
	    		}
	    		continue;
	    	}
	    	///////////////////////////////////////////////첫번째 꽃 심기 완료
	    	
	    	
	    	//새로운 꽃의 시작 날짜가 지금 꽃의 마지막 날과 같거나 그 이전이라면 
	    	if(sm<now.em || (sm==now.em&&sd<=now.ed)) {
    			if(temp==null||temp.em<em||temp.em==em&&temp.ed<ed) { //temp랑 비교해서 더 늦게 지는거를 넣는다 => 제ㅔㅔㅔㅔㅔ일 늦게 지는거 찾기 위해성 
    					temp = flowers.get(i);
    					if(em==12) { //만약 12월에 지면 성공 ! 더 탐색안해도댐
    						s.push(flowers.get(i));
    						break;
    					}
    				}
    			
    		}
	    	// 만약 새로운 꽃이 지금 꽃이 질때보다 나중에 핀다면? => temp에 있는게 지금 시점에서 제ㅔㅔㅔㅔㅔ일 늦게 지는거니까 temp를 스택에 넣고, 다음 꽃을 찾는다 
	    	else if(sm>now.em || (sm==now.em &&sd>now.ed)){ 
	    		if(temp==null||sm>temp.em||(sm==temp.em&&sd>temp.ed)) { //만약 새로운 꽃이 피는시기가 temp의 지는시기보다 늦다면 실패임
	    			System.out.println(0);
	    			return;
	    		}
	    		else { //스택에 temp 넣기
	    			s.push(temp);
	    			temp=flowers.get(i);
	    			
	    			if(em==12) { //만약 그게 12월에 지면 성공! 더 탐색 안해도됨
						s.push(temp);
						break;
					}
	    		}
	    		
	    	}

	    	
	    }
	    if(s.peek().em!=12) { 	//만약 마지막 꽃이 12월에 안지면 실패임
	    	System.out.println(0);
	    }else {
	    	System.out.println(s.size()); //스택의 사이즈가 답임
	    }
	    

	}
}
