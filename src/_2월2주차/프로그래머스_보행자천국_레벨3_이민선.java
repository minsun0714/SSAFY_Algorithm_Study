package _2월2주차;

public class 프로그래머스_보행자천국_레벨3_이민선 {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        if (m == 1 && n == 1) return 1;
        int[][][] dp = new int[m + 1][n + 1][2];
        dp[0][1][0] = dp[1][0][1] = 1;

        int[][] board = new int[m + 1][n + 1];
        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                board[i][j] = cityMap[i - 1][j - 1];
            }
        }

        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                int cur = board[i][j];
                int upper = board[i - 1][j];
                int left = board[i][j - 1];

                if (cur == 1) continue;

                dp[i][j][0] = dp[i - 1][j][0] % MOD;
                dp[i][j][1] = dp[i][j - 1][1] % MOD;

                if (j > 1 && upper == 0) dp[i][j][0] += dp[i - 1][j][1] % MOD;
                if (i > 1 && left == 0) dp[i][j][1] += dp[i][j - 1][0] % MOD;
            }
        }

        return (dp[m][n][0] + dp[m][n][1]) % MOD;
    }
}
