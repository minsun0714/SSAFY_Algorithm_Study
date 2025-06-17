import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [클래스] Main
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int[] group;
	static int N;
	
	// [메서드] main
	public static void main(String[] args) throws IOException {
		
		// [N]
		N = Integer.parseInt(br.readLine());
		
		// [group]
		group = new int[N+1];
		for (int i = 1; i <= N; i++) {
			group[i] = i;
		}
		
		// [star]
		double[][] star = new double[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}
		
		// [counter]
		int counter = 1;
		
		// [sum]
		double sum = 0;
		
		// [PQ]
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		for (int s = 1; s <= N-1; s++) {
			for (int e = s+1; e <= N; e++) {
				double dist = Math.sqrt((star[s][0] - star[e][0]) * (star[s][0] - star[e][0]) + (star[s][1] - star[e][1]) * (star[s][1] - star[e][1]));
				PQ.offer(new Node(s, e, dist));
			}
		}
		
		while (counter < N) {
			Node curr = PQ.poll();
			int a = curr.from;
			int b = curr.to;
			double dist = curr.dist;
			if (find(a) == find(b)) {
				continue;
			} else {
				union(a, b);
				counter++;
				sum += dist;
			}
		}
		
		
//		System.out.println(sum);
		sum = Math.round(sum * 100);
		System.out.println(sum/100);
		
	} // [메서드] main
	
	// [클래스] Node
	private static class Node implements Comparable<Node> {
		int from;
		int to;
		double dist;
		public Node() {
		}
		public Node(int from, int to, double dist) {
			super();
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return (int) (this.dist - o.dist)*100;
		}
	} // [클래스] Node
	
	// [메서드] find
	private static int find(int num) {
		if (group[num] == num) {
			return num;
		}
		return group[num] = find(group[num]);
	} // [메서드] find
	
	// [메서드] union
	private static void union(int a, int b) {
		group[find(b)] = find(a);
	} // [메서드] union
	
} // [클래스] Main
