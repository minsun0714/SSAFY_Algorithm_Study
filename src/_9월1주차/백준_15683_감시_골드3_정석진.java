import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//208ms
public class 백준_15683_감시_골드3_정석진 {
	public static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static class Location { // 카메라 위치 정보, 번호 저장
		int R;
		int C;
		int num;

		public Location(int r, int c, int num) {
			this.R = r;
			this.C = c;
			this.num = num;
		}

	}

	public static int[][] map;
	public static int[][] isWatched;
	public static int numCamera = 0; // 카메라 숫자
	public static int N;
	public static int M;
	public static int result = Integer.MAX_VALUE;
	public static Location[] arr;

	public static boolean isBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;

	}

	public static void backtrack(int step) {
		if (step == numCamera) {
			int temp = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (isWatched[r][c] == 0 && map[r][c]==0) {
						temp++;
					}
				}
			}
			result = result < temp ? result : temp;
			return;
		}

		Location cur = arr[step];

		switch (cur.num) {
		case 1: // 카메라 번호에 따라 순회하고 되돌리기
			for (int i = 0; i < 4; i++) {
				watching(cur.R, cur.C, i, false);
				backtrack(step + 1);
				watching(cur.R, cur.C, i, true);
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				watching(cur.R, cur.C, i, false);
				watching(cur.R, cur.C, i + 2, false);
				backtrack(step + 1);
				watching(cur.R, cur.C, i, true);
				watching(cur.R, cur.C, i + 2, true);
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				watching(cur.R, cur.C, i, false);
				watching(cur.R, cur.C, i + 1, false);
				backtrack(step + 1);
				watching(cur.R, cur.C, i, true);
				watching(cur.R, cur.C, i + 1, true);
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (i != j) {
						watching(cur.R, cur.C, j, false);
					}
				}
				backtrack(step + 1);
				for (int k = 0; k < 4; k++) {
					if (i != k) {
						watching(cur.R, cur.C, k, true);
					}
				}
			}
			break;
		case 5:
			for (int i = 0; i < 4; i++) {
				watching(cur.R, cur.C, i, false);
			}
			backtrack(step + 1);
			for (int i = 0; i < 4; i++) {
				watching(cur.R, cur.C, i, true);
			}
			break;
		}

	}

	public static void watching(int r, int c, int d, boolean reverse) {
		int curR = r;
		int curC = c;
		d %= 4;
		while (isBound(curR, curC) && map[curR][curC] != 6) {
			if (!reverse) {
				isWatched[curR][curC]++;
			} else {
				isWatched[curR][curC]--;
			}
			curR += dir[d][0];
			curC += dir[d][1];

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isWatched = new int[N][M];
		arr = new Location[8];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0 && map[r][c] != 6) {
					arr[numCamera++] = new Location(r, c, map[r][c]);
				}
			}
		}
		backtrack(0);
		System.out.println(result);
	}
}
