package _8월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dp
public class 백준_17070_파이프옮기기1_골드5_이민선 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];
        for (int i=0;i<n;i++){
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            for (int j=0;j<n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][][] dp = new int[n][n][3];
        dp[0][1][0] = 1;

        for (int j=2;j < n;j++) {
            if (board[0][j] == 1) continue;
            dp[0][j][0] = dp[0][j - 1][0];
        }

        for (int i=1;i<n;i++){
            for (int j=1;j<n;j++){
                if (board[i][j] == 1) continue;
                // 가로로 놓일 파이프
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

                // 세로로 놓일 파이프
                dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

                // 비스듬히 놓일 파이프
                if (board[i - 1][j] == 0 && board[i][j - 1] == 0)
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
            }
        }
        int answer = 0;
        for (int i=0;i < 3;i++){
            answer += dp[n - 1][n - 1][i];
        }
        System.out.println(answer);
    }
}
