import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// dp, 560ms
public class 백준_2169_로봇조종하기_골드2_김동빈_560ms {
	public static void main(String[] args) throws IOException {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;
		
		int N, M;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] table = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; ++j) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N][M];
		for (int i = 0 ; i < N; ++i) {
			Arrays.fill(dp[i], Integer.MIN_VALUE);
		}
		
		// 첫째줄
		dp[0][0] = table[0][0];
		for (int j = 1; j < M; ++j) {
			dp[0][j] = dp[0][j - 1] + table[0][j];
		}
		
		// dp 계산 수행
		for (int i = 1; i < N; ++i) {
			int[] tempDp = new int[M];
			
			// =>
			// 첫 칸
			dp[i][0] = Math.max(dp[i - 1][0] + table[i][0], dp[i][0]); // 위
			tempDp[0] = dp[i][0];
			
			for (int j = 1; j < M; ++j) {
				dp[i][j] = Math.max(dp[i - 1][j] + table[i][j], dp[i][j]); // 위
				tempDp[j] = dp[i][j]; 
				dp[i][j] = Math.max(dp[i][j - 1] + table[i][j], dp[i][j]); // 왼
			}
			// <=
			for (int j = M - 2; j >= 0; --j) {
				tempDp[j] = Math.max(tempDp[j + 1] + table[i][j], tempDp[j]); // 오
			}
			// 실반영
			for (int j = 0; j < M; ++j) {
				dp[i][j] = Math.max(tempDp[j], dp[i][j]);
				
			}
		}
		
		System.out.println(dp[N - 1][M - 1]);
	}
}
