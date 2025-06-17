import java.io.*;
import java.util.*;

public class Main {
	
	// [Main]
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] array = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		segmentTree segTree = new segmentTree(N);
		segTree.init(1, 1, N, array);
		
//		System.out.println(Arrays.toString(segTree.tree));
		
		int M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int order = Integer.parseInt(st.nextToken());
			if (order == 1) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				segTree.update(1, from, to, 1, N, value);
			} else {
				int index = Integer.parseInt(st.nextToken());
				long ans = segTree.query(1, index, 1, N);
				sb.append(ans).append("\n");
			}
		}
		
		System.out.println(sb);
		
//		System.out.println(Arrays.toString(segTree.tree));
	} // [Main]
	
	// [SegmentTree]
	private static class segmentTree {
		long[] tree;
		
		segmentTree(int n) {
			int height = (int) Math.ceil(Math.log(n) / Math.log(2));
			int length = (int) Math.pow(2, height + 1);
			this.tree = new long[length];
		}
		
		void init(int node, int left, int right, int[] array) {
			if (left == right) {
				this.tree[node] = array[left];
				return;
			}
			int mid = (left + right) / 2;
			init(node * 2, left, mid, array);
			init(node * 2 + 1, mid + 1, right, array);
		}
		
		void update(int node, int left, int right, int start, int end, int value) {
			if (right < start || end < left) {
				return;
			}
			if (left <= start && end <= right) {
				this.tree[node] += value;
				return;
			}
			int mid = (start + end) / 2;
			update(node * 2, left, right, start, mid, value);
			update(node * 2 + 1, left, right, mid + 1, end, value);
		}
		
		long query(int node, int index, int start, int end) {
			if (index < start || end < index) {
				return 0;
			}
			if (start == end) {
				return tree[node];
			}
			int mid = (start + end) / 2;
			return tree[node] +
					query(node * 2, index, start, mid) +
					query(node * 2 + 1, index, mid + 1, end);
		}
	} // [SegmentTree]
	
}
