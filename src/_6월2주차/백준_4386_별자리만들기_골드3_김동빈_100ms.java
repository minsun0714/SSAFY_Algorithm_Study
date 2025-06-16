package etc._6_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소 스패닝 트리
public class 백준_4386_별자리만들기_골드3_김동빈_100ms {
	
	static class Star {
		int id;
		double x;
		double y;
		public Star(int id, double x, double y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}
	}
	
	static class Node implements Comparable<Node>{
		
		int xId;
		int yId;
		double len;
		
		public Node(Star S1, Star S2) {
			this.xId = S1.id;
			this.yId = S2.id;
			
			double s1 = S1.x - S2.x; 
			double s2 = S1.y - S2.y; 
			this.len = Math.sqrt(Math.pow(s1, 2) + Math.pow(s2, 2));
		}
		@Override
		public int compareTo(Node o) {
			return Double.compare(this.len, o.len);
		}
	}
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		parents = new int[n];
		Star[] stars = new Star[n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(i, x, y);
			
			parents[i] = i;
		}
		
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				Node node = new Node(stars[i], stars[j]);
				pq.offer(node);
			}
		}
		
		double cost = 0;
		int sel = 0;
		 
		while (!pq.isEmpty() && sel < n - 1) {
			
			Node cur = pq.poll();
			
			int A = cur.xId;
			int B = cur.yId;
			if (union(A, B)) {
				cost += cur.len;
				sel++;
			}
			
		}
		System.out.println(cost);
	}
	
	static int find(int X) {
		
		int parentX = parents[X];
		if (X == parentX) return X;
		
		return parents[parentX] = find(parentX);
	}
	
	static boolean union(int A, int B) {
		
		int parentA = find(A);
		int parentB = find(B);
		
		if (parentA == parentB) return false;
		
		parents[parentA] = parentB;
		return true;
	}
}