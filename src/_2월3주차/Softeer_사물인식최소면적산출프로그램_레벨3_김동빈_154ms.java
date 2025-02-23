package etc._2_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// backtracking, 154ms
public class Softeer_사물인식최소면적산출프로그램_레벨3_김동빈_154ms {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static int N;
	private static int K;
	private static ArrayList<Point>[] pointClusters;

	public static void main(String[] args) throws Exception {

		InputStreamReader irs = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(irs);
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		pointClusters = new ArrayList[K];

		for (int i = 0; i < K; ++i) {
			pointClusters[i] = new ArrayList<Point>();
		}

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int x, y, k;
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken()) - 1;

			pointClusters[k].add(new Point(x, y)); // 클러스터에 포인트 추가
		}
		
		dfs(0, 0, INF, -INF, -INF, INF, 0);

		System.out.println(answer);
	}

	static int INF = 100001;
	static int answer = 40000001;

	static void dfs(int depth, int cur, int l, int r, int u, int d, int curSize) {
		// 가지 치기
		if (curSize >= answer) {
			return;
		}

		// 기저
		if (depth == K) {

			if (curSize < answer) {
				answer = curSize;
			}

			return;
		}

		// 탐색
		for (int i = 0; i < pointClusters[depth].size(); ++i ) {

			Point curPoint = pointClusters[depth].get(i);
			int x = curPoint.x;
			int y = curPoint.y;

			int nl = x > l ? l : x;
			int nr = x < r ? r : x;
			int nu = y < u ? u : y;
			int nd = y > d ? d : y;

			int w = (nr - nl);
			int h = (nu - nd);

			int nSize =  w * h;
			dfs(depth + 1, 0, nl, nr, nu, nd, nSize);
		}

		return;
	}

}
