import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

// [클래스] 메인
public class 백준_최소비용구하기2_김민섭_388ms {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	// [메서드] 메인
	public static void main(String[] args) throws IOException {
		
		// 도시의 수 (1 ~ 1,000)
		int N = Integer.parseInt(br.readLine());
		
		// 버스의 수 (1 ~ 100,000)
		int M = Integer.parseInt(br.readLine());
		
		// 노선
		List<List<int[]>> bus = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			bus.add(new ArrayList<>());
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			bus.get(from).add(new int[] {to, cost});
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		// 출발
		int start = Integer.parseInt(st.nextToken());
		
		// 도착
		int end = Integer.parseInt(st.nextToken());
		
		// 최소거리
		int[] dist = new int[N+1];
		Arrays.fill(dist, 1_000_000_000);
		dist[start] = 0;
		
		// 경로기록
		int[] history = new int[N+1];
		
		// 다익스트라
		PriorityQueue<int[]> PQ = new PriorityQueue<>( (a, b) -> { return a[1] - b[1]; } );
		PQ.offer(new int[] {start, 0});
		a : while (!PQ.isEmpty()) {
			int[] curr = PQ.poll();
			int S = curr[0];
			if (S == end) {
				break a;
			}
			for (int[] i : bus.get(S)) {
				int E = i[0];
				if (dist[E] > dist[S] + i[1]) {
					dist[E] = dist[S] + i[1];
					history[E] = S;
					PQ.offer(new int[] {E, dist[E]});
				}
			}
		}
		
		// 역추적
		Stack<Integer> stack = new Stack<>();
		int val = end;
		stack.push(val);
		while (val != start) {
			val = history[val];
			stack.push(val);
		}
		
		// 어펜드 & 출력
		sb.append(dist[end]).append("\n");
		sb.append(stack.size()).append("\n");
		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb);
		
	} // [메서드] 메인
	
} // [클래스] 메인
