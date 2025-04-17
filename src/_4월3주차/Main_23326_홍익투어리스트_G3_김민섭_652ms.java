import java.io.*;
import java.util.*;

public class Main_23326_홍익투어리스트_G3_김민섭_652ms {
	
	// [SEGMENT TREE]
	private static class SegmentTree {
		int[] tree;
		int end;
		
		SegmentTree(int n) {
			int height = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
			int length = (int) Math.pow(2, height);
			this.tree = new int[length];
			this.end = length / 2;
		}
		
		void update(int num, int numIndex) {
			update(num, numIndex, 1, 1, this.end);
		}
		void update(int num, int numIndex, int index, int left, int right) {
			if (numIndex < left || right < numIndex) {
				return;
			}
			
			this.tree[index] += num;
			
			if (left == right) {
				return;
			}
			
			int mid = (left + right) / 2;
			
			if (numIndex <= mid) {
				update(num, numIndex, index * 2, left, mid);
			} else {
				update(num, numIndex, index * 2 + 1, mid + 1, right);
			}
		}
		
		int find(int start, int end) {
			return find(start, end, 1, 1, this.end);
		}
		int find(int start, int end, int index, int left, int right) {
			if (right < start || end < left) {
				return 1_000_000_000;
			}
			
			if (left == right) {
				return left;
			}
			
			int mid = (left + right) / 2;
			
			int ans = 1_000_000_000;
			
			if (0 < this.tree[index * 2]) {
				ans = Math.min(ans, find(start, end, index * 2, left, mid));
			}
			
			if (0 < this.tree[index * 2 + 1] && ans == 1_000_000_000) {
				ans = Math.min(ans, find(start, end, index * 2 + 1, mid + 1, right));
			}
			
			return ans;
		}
	}
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int dh = 1;
		boolean[] hong = new boolean[N * 2 + 1];
		SegmentTree sTree = new SegmentTree(N * 2);
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int n = 1; n <= N; n++) {
			hong[n] = Integer.parseInt(st.nextToken()) == 1;
			hong[n + N] = hong[n];
			if (hong[n]) {
				sTree.update(1, n);
				sTree.update(1, n + N);
			}
		}
		
		int order = 0;
		int number = 0;
		
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine(), " ");
			order = Integer.parseInt(st.nextToken());
			switch (order) {
			
			case 1:
				number = Integer.parseInt(st.nextToken());
				hong[number] = !hong[number];
				hong[number + N] = hong[number];
				if (hong[number]) {
					sTree.update(1, number);
					sTree.update(1, number + N);
					break;
				}
				sTree.update(-1, number);
				sTree.update(-1, number + N);
				break;
			
			case 2:
				number = Integer.parseInt(st.nextToken());
				dh = N < dh + number ? (dh + number) % N : dh + number;
				dh = dh == 0 ? N : dh;
				break;
			
			case 3:
				int coor = sTree.find(dh, dh + N - 1);
				if (coor == 1_000_000_000) {
					sb.append(-1).append("\n");
					break;
				}
				sb.append(coor - dh).append("\n");
				break;
			
			default:
				break;
			}
		}
		
		System.out.println(sb);
	}
	
}