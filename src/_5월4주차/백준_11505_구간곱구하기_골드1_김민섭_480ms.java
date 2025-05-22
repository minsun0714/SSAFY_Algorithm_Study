import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [CLASS]
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int M;
	
	static long[] array;
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		
		array = new long[N + 1];
		for (int n = 1; n <= N; n++) {
			array[n] = Long.parseLong(br.readLine());
		}
		
		sTree tree = new sTree(N);
		tree.init(array, 1, 1, N);
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			long first = Long.parseLong(st.nextToken());
			long second = Long.parseLong(st.nextToken());
			switch (order) {
			case 1:
				int index = (int) first;
				tree.update(1, 1, N, index, array[(int) first], second);
				array[index] = second;
				break;
			case 2:
				sb.append(tree.product(1, 1, N, (int) first, (int) second)).append("\n");
				break;
			}
		}
		
		System.out.println(sb);
		
	} // [MAIN]
	
	// [SEGMENT TREE]
	private static class sTree {
		long[] tree;
		int treeSize;
		
		public sTree(int n) {
			int height = (int) Math.ceil(Math.log(N) / Math.log(2));
			treeSize = (int) (Math.pow(2, height + 1) + 1);
			tree = new long[treeSize];
			Arrays.fill(tree, 1);
		}
		
		public long init(long[] array, int node, int start, int end) {
			if (start == end) {
				return tree[node] = array[start];
			}
			return tree[node] =
					( init(array, node * 2, start, (start + end) / 2)
					* init(array, node * 2 + 1, (start + end) / 2 + 1, end) )
					% 1000000007;
		}
		
		public long update(int node, int start, int end, int index, long original, long value) {
			if (start == end) {
				if (start == index) {
					return tree[node] = value;
				} else {
					return tree[node];
				}
			}
			if (index < start || end < index) {
				return tree[node];
			}
			return tree[node] = 
			( update(node * 2, start, (start + end) / 2, index, original, value)
			* update(node * 2 + 1, (start + end) / 2 + 1, end, index, original, value) )
			% 1000000007;
		}
		
		public long product(int node, int start, int end, int left, int right) {
			if (end < left || right < start) {
				return 1;
			}
			if (left <= start && end <= right) {
				return tree[node];
			}
			return ( product(node * 2, start, (start + end) / 2, left, right)
					* product(node * 2 + 1, (start + end) / 2 + 1, end, left, right) )
					% 1000000007;
		}
	} // [SEGMENT TREE]
	
} // [CLASS]
