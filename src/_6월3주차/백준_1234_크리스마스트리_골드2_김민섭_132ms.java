import java.io.*;
import java.util.*;

public class 백준_1234_크리스마스트리_골드2_김민섭_132ms {
	
	// STATIC
	static int N;
	static int[] col;
	static long[][][][] dp;
	// STATIC
	
	// PER
	static long per(int num) {
		if (num == 1) {
			return 1;
		}
		return per(num - 1) * num;
	}
	// PER
	
	// FUNC
	static long func(int height) {
		if (N + 1 <= height) {
			return 1;
		}
		
		if (dp[height][col[1]][col[2]][col[3]] != -1) {
			return dp[height][col[1]][col[2]][col[3]];
		}
		
		long sum = 0;
		
		if (height % 3 == 0) {
			int temp = height / 3;
			if (temp <= col[1] && temp <= col[2] && temp <= col[3]) {
				col[1] -= temp;
				col[2] -= temp;
				col[3] -= temp;
				sum += func(height + 1) * (per(temp * 3) / per(temp) / per(temp) / per(temp));
				col[1] += temp;
				col[2] += temp;
				col[3] += temp;
			}
		}
		
		if (height % 2 == 0) {
			int temp = height / 2;
			for (int c1 = 1; c1 <= 2; c1++) {
				for (int c2 = c1 + 1; c2 <= 3; c2++) {
					if (temp <= col[c1] && temp <= col[c2]) {
						col[c1] -= temp;
						col[c2] -= temp;
						sum += func(height + 1) * (per(temp * 2) / per(temp) / per(temp));
						col[c1] += temp;
						col[c2] += temp;
					}
				}
			}
		}
		
		for (int c = 1; c <= 3; c++) {
			if (height <= col[c]) {
				col[c] -= height;
				sum += func(height + 1);
				col[c] += height;
			}
		}
		
		return dp[height][col[1]][col[2]][col[3]] = sum;
	} // FUNC
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		
		col = new int[4];
		col[1] = Integer.parseInt(st.nextToken());
		col[2] = Integer.parseInt(st.nextToken());
		col[3] = Integer.parseInt(st.nextToken());
		
		dp = new long[N + 2][col[1] + 1][col[2] + 1][col[3] + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= col[1]; j++) {
				for (int k = 0; k <= col[2]; k++) {
					Arrays.fill(dp[i][j][k], -1);
				}
			}
		}
		
		System.out.println(func(1));
	} // [MAIN]
	
}
