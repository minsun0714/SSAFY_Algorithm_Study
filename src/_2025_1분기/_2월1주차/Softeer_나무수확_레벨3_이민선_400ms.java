package _2월1주차;

import java.io.*;
import java.util.*;

public class Softeer_나무수확_레벨3_이민선_400ms {
    static int n;
    static int[][] board;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        board= new int[n][n];
        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[n + 1][n + 1][3];

        for (int i=1;i<=n;i++){
            for (int j=1;j<=n;j++){
                int upperNotYet = dp[i - 1][j][0];
                int leftNotYet = dp[i][j - 1][0];

                int upperAlready = Math.max(dp[i - 1][j][1], dp[i - 1][j][2]);
                int leftAlready = Math.max(dp[i][j - 1][1], dp[i][j - 1][2]);
                // 과거에 설치된 것을 사용하지도 않고 설치하지도 않음.
                dp[i][j][0] = Math.max(upperNotYet, leftNotYet) + board[i - 1][j - 1];

                // 과거에 설치된 것을 사용
                dp[i][j][1] = Math.max(upperAlready, leftAlready) + board[i - 1][j - 1];

                // 위 아래에서 설치한 걸 포기하고 다시 설치
                dp[i][j][2] = Math.max(upperNotYet, leftNotYet) + 2 * board[i - 1][j - 1];
            }
        }
        System.out.println(Math.max(dp[n][n][1], dp[n][n][2]));
    }
}
