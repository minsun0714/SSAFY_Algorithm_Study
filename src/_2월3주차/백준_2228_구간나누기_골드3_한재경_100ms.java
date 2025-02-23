import java.io.*;
import java.util.*;

public class 백준_2228_구간나누기_골드3_한재경_100ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 전체 원소 수
        int m = Integer.parseInt(st.nextToken()); // 선택할 구간 수
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long[][][] dp = new long[n][m+1][2]; //i번째 수, j번째 구간, 선택여부
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j][0] = Integer.MIN_VALUE;
                dp[i][j][1] = Integer.MIN_VALUE;
            }
        }

        dp[0][0][0] = 0;
        dp[0][1][1] = arr[0];

        for (int i = 1; i < n; i++) {
            // 구간 0개: 유지
            dp[i][0][0] = dp[i-1][0][0];
            for (int j = 1; j <= m; j++) {
                // 선택x: 이전값 선택o, 선택x 중 최댓값 유지
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1]);
                // 선택o: 이전값 선택o + 현재값 / 선택x + 현재값
                dp[i][j][1] = Math.max(dp[i-1][j][1] + arr[i], dp[i-1][j-1][0] + arr[i]);
            }
        }

        long ans = Math.max(dp[n-1][m][0], dp[n-1][m][1]);
        System.out.println(ans);
    }
}
