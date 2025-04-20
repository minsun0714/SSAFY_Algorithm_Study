package etc._4_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 세그먼트 트리
public class Main_백준_23326_홍익투어리스트 {
	
	static class SegmentTree {
		
		int[] arr;
		int[] seg;

		public SegmentTree(int[] arr) {
			this.arr = arr;
			this.seg = new int[arr.length * 4];
			
			init(1, 1, arr.length);
		}
		
		public void init(int i, int tl, int tr) {
			
			if (tl >= tr) {
				seg[i] = arr[tl - 1];
				return;
			}
			
			int mid = tl + (tr - tl) / 2;
			init(i * 2, tl, mid);
			init(i * 2 + 1, mid + 1, tr);
			
			seg[i] = seg[i * 2] + seg[i * 2 + 1];
			return;
		}
		
		public int query(int l, int r) {
			return query(1, l, r, 1, arr.length);
		}
		
		private int query(int i, int l, int r, int tl, int tr) {
			
			// 밖
			if (r < tl || tr < l) {
				return 0 ;
			}
			
			// 속
			if (l <= tl && tr <= r) {
				return seg[i];
			}
			
			int mid = tl + (tr - tl) / 2;
			int left = query(i * 2, l, r, tl, mid);
			int right = query(i * 2 + 1, l, r, mid + 1, tr);
			
			return left + right;
		}
		
		public void update(int target, int value) {
			update(1, 1, arr.length, target, value);
		}
		
		private void update(int i, int tl, int tr, int target, int value) {
			
			// 밖 
			if (target < tl || tr < target) {
				return;
			}
			
			// 리프
			if (tl >= tr) {
				seg[i] = value;
				return;
			}
			
			// 리프 아님
			int mid = tl + (tr - tl) / 2;
			update(i * 2, tl, mid, target, value);
			update(i * 2 + 1, mid + 1, tr, target, value);
			
			seg[i] = seg[i * 2] + seg[i * 2 + 1];
			return;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		int N, Q;
		int[] place;
		int totalCount = 0;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		place = new int[N];
		
		st = new StringTokenizer(br.readLine());
		int index = 0;
		while(st.hasMoreTokens()) {
			place[index] = Integer.parseInt(st.nextToken());
			totalCount += place[index] == 1 ? 1 : 0;
			index++;
		}
		// 장소 입력 끝
		SegmentTree segmentTree = new SegmentTree(place);
		
		int cur = 1;
		// 쿼리 입력 시작
		for (int q = 0; q < Q; ++q) {
			
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			
			// 명소 지정 토글
			if (query == 1) {
				int i = Integer.parseInt(st.nextToken()); // 1-base
				
				place[i - 1] = (1 - place[i - 1]);
				segmentTree.update(i, place[i - 1]);
				
				totalCount += place[i - 1] == 1 ? 1 : -1;
			}
			
			// 도현이 x 만큼 이동
			if (query == 2) {
				int x = Integer.parseInt(st.nextToken());
				cur = ((cur-1) + x) % N + 1;
			}
			
			// 시계 방향 제일 가까운 명소 칸 수?
			if (query == 3) {
				// 예외
				if (totalCount <= 0) {
					answer.append(-1).append("\n");
					continue;
				}
				
				if (place[cur - 1] == 1) {
					answer.append(0).append("\n");
					continue;
				}
				
				int s = cur + 1;
				int e = N;
				
				// 순환 보정
				if (segmentTree.query(s, e) <= 0) {
					s = 1;
					e = cur + 1;
				}
				
				// 순환 필요 없음
				int nextX = lo(s, e, segmentTree);
				
				if (nextX >= cur) {
					answer.append(nextX - cur).append("\n");
				} else {
					answer.append(N - (cur - nextX)).append("\n");
				}
			}
		}
		System.out.println(answer);
	}
	
	public static int lo(int s, int e, SegmentTree seg) {
		int l = s;
		int r = e + 1;
		
		while(l < r) {
			int mid = l + (r - l) / 2;
			int pSumFromBaseToMid = seg.query(l, mid);
			if (pSumFromBaseToMid >= 1) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		
		return l;
	}
}