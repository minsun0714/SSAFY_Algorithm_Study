package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 115ms, LIS, DP
public class Softeer_징검다리_레벨3_김동빈_115ms {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] stones = new int[N];
		
		for (int i = 0; i < N; ++i) {
			stones[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 완료
		
		
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		int answer = 1;
		
		for (int i = 0; i < N; ++i) {
			int curStone = stones[i];
			
			for (int j = 0; j < i; ++j) {
				int lastStone = stones[j];
				
				// 현재 위치의 돌이 이전 특정 위치의 돌 보다 높으면, dp 갱신 할 수 있으면
				if ((lastStone < curStone) && (dp[j] + 1 > dp[i])) {
					dp[i] = dp[j] + 1;
				}
			}
			
			if (answer < dp[i]) {
				answer = dp[i];
			}
		}
		System.out.println(answer);
	}
}
