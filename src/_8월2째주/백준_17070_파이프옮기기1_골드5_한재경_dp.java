package _8월2째주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 104ms, dp
public class 백준_17070_파이프옮기기1_골드5_한재경_dp  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][n]; //원본 그래프
        int[][][] dp = new int[n][n][3]; //dp에 파이프위치(우측기준)와 가로세로대각선에 따른 카운팅

        for (int i = 0; i < n; i++) { //그래프 채우기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n; i++) { //첫 행, 첫 열 채우기
            if (graph[0][i] == 0) {
                dp[0][i][0] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (graph[i][j] == 0) {
                    dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2]; //왼 - 가로,대각선값
                    dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2]; //위 - 세로, 대각선값

                    //대각선 - 삼면빌 때 가능, 대각선, 가로, 세로값
                    if (graph[i - 1][j] == 0 && graph[i][j - 1] == 0) {
                        dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                    }
                }
            }
        }
        System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);
    }
}