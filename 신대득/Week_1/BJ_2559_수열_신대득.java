import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2559_수열_신대득 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine(), " ");
		Queue<Integer> que = new LinkedList<>();
		int tempSum=0;
		for (int i= 1; i<=K;i++) {
			int temp = Integer.parseInt(st.nextToken());
			que.offer(Integer.valueOf(temp));
			tempSum+=temp;
		}
		int maxSum=tempSum;
		for (int i = K+1; i <= N; i++) {
			tempSum-=que.poll();
			int temp =Integer.parseInt(st.nextToken());
			que.offer(Integer.valueOf(temp));
			tempSum +=temp;
			if(tempSum>maxSum)
				maxSum=tempSum;
		}
		System.out.printf("%d", maxSum);
	}

}
