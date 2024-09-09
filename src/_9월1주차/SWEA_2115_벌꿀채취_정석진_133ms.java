
/* 벌통 크기 N
 * 개수 M
 * 최대 채취 양 C
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취_정석진_133ms {
	public static int T;
	public static int N;
	public static int M;
	public static int C;
	public static int[][] map;
	public static int result;

	public static int getMaxHoney(int[] arr) {
		int maxProfit = 0;
		int n = arr.length;

		// 모든 부분집합 탐색
		for (int subset = 1; subset < (1 << n); subset++) {
			int honey = 0;
			int profit = 0;

			for (int i = 0; i < n; i++) {
				if ((subset & (1 << i)) != 0) {
					honey += arr[i];
					profit += arr[i] * arr[i];
				}
			}

			// 꿀 양이 C를 넘지 않는다면 최대 이익 갱신
			if (honey <= C) {
				maxProfit = Math.max(maxProfit, profit);
			}
		}

		return maxProfit;
	}

	public static void dfs(int cnt, int r, int c, int profit) {
		if (cnt == 2) {
			result = Math.max(result, profit);
			return;
		}

		for (int i = r; i < N; i++) {
			for (int j = (i == r ? c : 0); j < N - M + 1; j++) {
				// 선택한 벌통 영역에 대해 최대로 얻을 수 있는 이익 계산
				int[] selectedHoney = Arrays.copyOfRange(map[i], j, j + M);
				int maxProfitForThisSection = getMaxHoney(selectedHoney);
				dfs(cnt + 1, i, j + M, profit + maxProfitForThisSection);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			result = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			// 벌집 입력
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0, 0, 0, 0);
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}
