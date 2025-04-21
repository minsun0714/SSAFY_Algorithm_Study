import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 오래전에 푼거라 코드 질이 떨어질 수 있습니다.

// [클래스] 메인
public class 백준_1167_트리의지름_김민섭_840ms {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int V;
	static List<List<int[]>> link;
	static int MAX;
	static int TO;
	static final int INF = 1_000_000_000;
	
	// [메서드] 다익스트라
	static void Dijkstra(int start) {
		int[] dist = new int[V+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		boolean[] visited = new boolean[V+1];
		
		PriorityQueue<int[]> PQ = new PriorityQueue<>((a, b) -> { return a[1] - b[1]; });
		PQ.offer(new int[] {start, 0});
		
		while (!PQ.isEmpty()) {
			int[] curr = PQ.poll();
			
			int S = curr[0];
			if (visited[S]) {
				continue;
			}
			visited[S] = true;
			
			for (int[] i : link.get(S)) {
				int to = i[0];
				int cost = i[1];
				if (dist[to] > dist[S] + cost) {
					dist[to] = dist[S] + cost;
					PQ.offer(new int[] {to, dist[to]});
				}
			}
		}
		
		MAX = 0;
		for (int i = 1; i <= V; i++) {
			if (dist[i] != INF && MAX < dist[i]) {
				MAX = dist[i];
				TO = i;
			}
		}
	} // [메서드] 다익스트라
		
	// [메서드] 메인
	public static void main(String[] args) throws IOException {
		
		// [V] 정점 수 (2 ~ 100,000)
		V = Integer.parseInt(br.readLine());
		
		// [link] 간선 (1 ~ 10,000)
		link = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			link.add(new ArrayList<>());
		}
		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			while (to != -1) {
				int cost = Integer.parseInt(st.nextToken());
				link.get(from).add(new int[] {to, cost});
				to = Integer.parseInt(st.nextToken());
			}
		}
		
		// 임의의 점에서 가장 먼 [TO] 탐색
		Dijkstra(1);
		
		// [TO] 에서 가장 먼 정점까지의 길이 [MAX] 탐색
		Dijkstra(TO);
		
		// 출력
		System.out.println(MAX);
		
	} // [메서드] 메인
	
} // [클래스] 메인
