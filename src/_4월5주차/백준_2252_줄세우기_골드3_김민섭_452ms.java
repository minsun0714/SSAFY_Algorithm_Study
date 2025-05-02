import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [CLASS]
public class 백준_2252_줄세우기_골드3_김민섭_452ms {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int M;
	static Node[] nodes;
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		
		// INPUT
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		
		nodes = new Node[N + 1];
		for (int n = 1; n <= N; n++) {
			nodes[n] = new Node(n);
		}
		
		M = Integer.parseInt(st.nextToken());
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			nodes[prev].connections.add(next);
			nodes[next].depNum++;
		}
		// INPUT
		
		topologySort();
		
		System.out.println(sb);
		
	} // [MAIN]
	
	// [NODE]
	private static class Node implements Comparable<Node> {
		int value;
		int depNum;
		List<Integer> connections;
		public Node(int value) {
			this.value = value;
			this.depNum = 0;
			this.connections = new ArrayList<>();
		}
		@Override
		public int compareTo(Node o) {
			return this.depNum - o.depNum;
		}
	} // [NODE]
	
	// [TOPOLOGY SORT]
	static void topologySort() {
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (nodes[i].depNum == 0) {
				priorityQueue.offer(nodes[i]);
			}
		}
		while (!priorityQueue.isEmpty()) {
			Node curr = priorityQueue.poll();
			for (int con : curr.connections) {
				nodes[con].depNum--;
				if (nodes[con].depNum == 0) {
					priorityQueue.offer(nodes[con]);
				}
			}
			sb.append(curr.value).append(" ");
		}
	} // [TOPOLOGY SORT]
	
} // [CLASS]
