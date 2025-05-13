import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [CLASS]
public class 백준_2042_구간합구하기_골드1_김민섭_544ms {
	
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
				long original = array[index];
				long diff = second - original;
				array[index] += diff;
				tree.update(1, 1, N, index, diff);
				break;
			case 2:
				sb.append(tree.sum(1, 1, N, (int) first, (int) second)).append("\n");
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
			this.treeSize = (int) (Math.pow(2, height + 1) + 1);
			this.tree = new long[treeSize];
		}
		
		public long init(long[] array, int node, int start, int end) {
			if (start == end) {
				return tree[node] = array[start];
			}
			return tree[node] =
					init(array, node * 2, start, (start + end) / 2) +
					init(array, node * 2 + 1, (start + end) / 2 + 1, end);
		}
		
		public void update(int node, int start, int end, int index, long diff) {
			if (end < index || index < start) {
				return;
			}
			tree[node] += diff;
			if (start == end) {
				return;
			}
			update(node * 2, start, (start + end) / 2, index, diff);
			update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
		}
		
		public long sum(int node, int start, int end, int left, int right) {
			if (end < left || right < start) {
				return 0;
			}
			if (left <= start && end <= right) {
				return tree[node];
			}
			return sum(node * 2, start, (start + end) / 2, left, right) +
					sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}
	} // [SEGMENT TREE]
	
} // [CLASS]
