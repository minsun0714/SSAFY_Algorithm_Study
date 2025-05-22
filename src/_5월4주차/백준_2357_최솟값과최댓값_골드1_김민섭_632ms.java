import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2357_최솟값과최댓값_골드1_김민섭_632ms {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int M;
	static int[] array;
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		
		minTree minTree = new minTree(N);
		maxTree maxTree = new maxTree(N);
		
		minTree.init(1, 1, N, array);
		maxTree.init(1, 1, N, array);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(minTree.getMin(1, 1, N, a, b))
			.append(" ")
			.append(maxTree.getMax(1, 1, N, a, b))
			.append("\n");
		}
		
		System.out.println(sb);
		
		
	} // [MAIN]
	
	// [MAX TREE]
	private static class maxTree{
		static int height;
		static int[] maxTree;
		
		maxTree(int n) {
			height = (int) Math.ceil(Math.log(n) / Math.log(2));
			int length = (int) (Math.pow(2, height + 1) + 1);
			maxTree = new int[length];
		}
		
		int init(int node, int start, int end, int[] array) {
			if (start == end) {
				return maxTree[node] = array[start];
			}
			int mid = (start + end) / 2;
			return maxTree[node] = Math.max(
					init(node * 2, start, mid, array),
					init(node * 2 + 1, mid + 1, end, array));
		}
		
		int getMax(int node, int start, int end, int left, int right) {
			if (right < start || end < left) {
				return 1;
			}
			if (left <= start && end <= right) {
				return maxTree[node];
			}
			int mid = (start + end) / 2;
			return Math.max(
					getMax(node * 2, start, mid, left, right),
					getMax(node * 2 + 1, mid + 1, end, left, right));
		}
	} // [MAX TREE]
	
	// [MIN TREE]
	private static class minTree{
		static int height;
		static int[] minTree;
		
		minTree(int n) {
			height = (int) Math.ceil(Math.log(n) / Math.log(2));
			int length = (int) (Math.pow(2, height + 1) + 1);
			minTree = new int[length];
		}
		
		int init(int node, int start, int end, int[] array) {
			if (start == end) {
				return minTree[node] = array[start];
			}
			int mid = (start + end) / 2;
			return minTree[node] = Math.min(
					init(node * 2, start, mid, array),
					init(node * 2 + 1, mid + 1, end, array));
		}
		
		int getMin(int node, int start, int end, int left, int right) {
			if (right < start || end < left) {
				return 1_000_000_000;
			}
			if (left <= start && end <= right) {
				return minTree[node];
			}
			int mid = (start + end) / 2;
			return Math.min(
					getMin(node * 2, start, mid, left, right),
					getMin(node * 2 + 1, mid + 1, end, left, right));
		}
	} // [MIN TREE]
	
	
}