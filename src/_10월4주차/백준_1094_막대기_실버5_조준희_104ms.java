import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//우선순위큐 기본 문제
public class 백준_1094_막대기_실버5_조준희_104ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(64);
		
		int sum = 64;
		int target = Integer.parseInt(br.readLine());
		
		while(sum>target) {
			int stick= pq.poll();
			pq.add(stick/2);
			sum-=stick/2;
			if(sum<target) {
				pq.add(stick/2);
				sum+=stick/2;
			}
		}
		
		System.out.println(pq.size()+" "+sum);
	}
}
