package _3월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2169_로봇조종하기_골드2_이민선_508ms {
    static int n, m;
    static int[][] board;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];

        for (int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n + 1][m + 1][2];
        for (int i=0;i<=n;i++){
            for (int j=0;j<=m;j++){
                dp[i][j][0] = dp[i][j][1] = Integer.MIN_VALUE;
            }
        }

        for (int j=1;j<=m;j++) {
            dp[1][j][0] = board[1][j];
            if (j > 1) dp[1][j][0] = dp[1][j - 1][0] + board[1][j];
        }

        for (int i=2;i<=n;i++){
            for (int j=1;j<=m;j++){
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1]) + board[i][j];
                if (j > 1) dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j - 1][0] + board[i][j]);
            }
            for (int j=m;j>=1;j--){
                dp[i][j][1] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1]) + board[i][j];
                if (j < m) dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j + 1][1] + board[i][j]);
            }
        }

        System.out.println(dp[n][m][0]);
    }
}
