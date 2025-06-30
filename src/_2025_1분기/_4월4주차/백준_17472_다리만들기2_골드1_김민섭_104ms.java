import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 6달 전에 작성한 코드라 가독성이 매우 구리니 따로 말씀 주시면 새로 풀어서 올립니다
// BFS + 유니온파인드 + 최소신장트리

// [클래스] Main
public class 백준_17472_다리만들기2_골드1_김민섭_104ms {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int[][] dir = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
	
	static int N;
	static int M;
	static int[][] arr;
	static int counter;
	static int[] group;
	static PriorityQueue<Node> PQ;
	
	// [메서드] main
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine(), " ");
		// [N]
		N = Integer.parseInt(st.nextToken());
		// [M]
		M = Integer.parseInt(st.nextToken());
		
		// [arr]
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = -Integer.parseInt(st.nextToken());
			}
		}
		
		// [counter]
		counter = 0;
		
		// COUNT ISLAND
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == -1) {
					counter++;
					BFS(i, j);
				}
			}
		}
		
		// [group]
		group = new int[counter+1];
		for (int i = 1; i <= counter; i++) {
			group[i] = i;
		}
		
		// [checker]
		int checker = 1;
		
		// [PQ]
		PQ = new PriorityQueue<>();
		
		// FIND NODE
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0) {
					for (int d = 0; d < 4; d++) {
						link(i, j, arr[i][j], 0, d);
					}
				}
			}
		}
		
		// [sum]
		int sum = 0;
		
		// WORK
		while (checker < counter && !PQ.isEmpty()) {
			Node curr = PQ.poll();
			int a = curr.from;
			int b = curr.to;
			int cost = curr.cost;
			if (find(a) != find(b)) {
				union(a, b);
				sum += cost;
				checker++;
			}
		} // WORK
		
		// PRINT
		if (checker != counter) {
			System.out.println(-1);
		} else {
			System.out.println(sum);
		}
		
	} // [메서드] main
	
	// [메서드] link
	private static void link(int r, int c, int s, int l, int d) {
		int R = r + dir[d][0];
		int C = c + dir[d][1];
		if (inRange(R, C) && arr[R][C] != s) {
			if (arr[R][C] == 0) {
				link(R, C, s, l+1, d);
			} else if (l >= 2) {
				PQ.offer(new Node(s, arr[R][C], l));
			}
		}
	}
	
	// [클래스] Node
	private static class Node implements Comparable<Node> {
		int from;
		int to;
		int cost;
		public Node() {
		}
		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	} // [클래스] Node
	
	// [메서드] union
	private static void union(int a, int b) {
		group[find(b)] = find(a);
	} // [메서드] union
	
	// [메서드] find
	private static int find(int num) {
		if (group[num] == num) {
			return num;
		}
		return group[num] = find(group[num]);
	} // [메서드] find
	
	// [메서드] BFS
	private static void BFS(int r, int c) {
		Queue<int[]> Q = new ArrayDeque();
		arr[r][c] = counter;
		Q.offer(new int[] {r, c});
		while (!Q.isEmpty()) {
			int[] curr = Q.poll();
			for (int[] d : dir) {
				int R = curr[0] + d[0];
				int C = curr[1] + d[1];
				if (inRange(R, C) && arr[R][C] == -1) {
					arr[R][C] = counter;
					Q.offer(new int[] {R, C});
				}
			}
		}
	} // [메서드] BFS
	
	// [메서드] inRange
	private static boolean inRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	} // [메서드] inRange
	
} // [클래스] Main
