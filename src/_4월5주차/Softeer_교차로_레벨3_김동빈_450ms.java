package etc._4_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_소프티어_교차로_450ms {
	
	static class Car implements Comparable<Car>{
		
		int index;
		int t;
		int w;
		
		public Car(int index, int t, String w) {
			super();
			this.index = index;
			this.t = t;
			this.w = set(w);
		}
		
		// CBAD
		// 0123
		private int set(String w) {
			switch (w) {
			case "A":
				return 2;
			case "B":
				return 1;
			case "C":
				return 0;
			case "D":
				return 3;
			default: return - 1;
			}
		}

		@Override
		public int compareTo(Car o) {
			return Integer.compare(this.t, o.t);
		}
	}
	
	static Queue<Integer>[] carQ;
	static int[] answerArr;
	static int time;
	static int curCarNumber;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Car> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			String w = st.nextToken();			
			pq.offer(new Car(i, t, w));
		}
		
		answerArr = new int[N]; // 정답 배열
		Arrays.fill(answerArr, -1);
		carQ = new ArrayDeque[4];
		carQ[0] = new ArrayDeque<>();
		carQ[1] = new ArrayDeque<>();
		carQ[2] = new ArrayDeque<>();
		carQ[3] = new ArrayDeque<>();
		
		// 자동차 지나가자
		time = pq.peek().t;
		
		while (!pq.isEmpty()) {
			
			while (!pq.isEmpty() && pq.peek().t == time) {
				Car cur = pq.poll();
				int index = cur.index;
				int arrivedDir = cur.w;
				carQ[arrivedDir].offer(index);
			}
			
			if (out() == 0) { // 나가기
				if (pq.isEmpty()) continue;
				time = pq.peek().t;
			} else {
				time++;
			}
		}
		
		while (out() != 0) {
			time++;
		}
		
		// 정답 출력
		for (int i = 0; i < N; ++i) {
			answer.append(answerArr[i]).append("\n");
		}
		System.out.println(answer);
	}

	private static int out() {
		int outCount = 0;
		
		// 4교차로 보고
		for (int d = 3; d >= 0; --d) {
			if (carQ[d].isEmpty()) continue; // 그 교차로 차 없음
			
			int right = (d + 1) % 4;
			if (!carQ[right].isEmpty()) continue; // 오른쪽 교차로에 차 있음
				
			int outCarNumber = carQ[d].poll(); // OUT!!!
			outCount++;
			d--;
			answerArr[outCarNumber] = time;
		}
		
		return outCount;
	}
}