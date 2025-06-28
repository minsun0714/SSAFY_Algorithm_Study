package org.example;

import java.io.*;
import java.util.*;

//dp
public class 백준_11062_카드게임_골드3_한재경_520ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] cards = new int[n];
            int[] sum = new int[n + 1]; //i까지 누적합. 누적합은 0인덱스도 있어야 함!
            int[][] dp = new int[n][n]; //i에서 j까지 구간에서 건우의 최대합

            for (int i = 0; i < n; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
                sum[i + 1] += sum[i] + cards[i];
                dp[i][i] = cards[i]; //i에서 i까지는 단일값 (길이 1인 경우)
            }

            // 근우부터 시작
            // i~j구간에서의 건우 최대합은 (해당 구간합 - 한 턴 전의 dp값 중 min(즉 명우가 가지는 값))
            // 한 턴 전의 dp값 중 max는 건우가 가져야 하는 값이기 때문

            //길이 2개 이상인 경우
            for (int len = 2; len <= n; len++) { //길이는 n개까지 (0 ~ n-1인덱스)
                for (int l = 0; l + len - 1 < n; l++) { //왼쪽 인덱스
                    int r = l + len - 1; //오른쪽 인덱스
                    int nowSum = sum[r + 1] - sum[l]; //sum인덱스는 +1 상태이므로
                    dp[l][r] = nowSum - Math.min(dp[l + 1][r], dp[l][r - 1]);
                }
            }
            sb.append(dp[0][n-1] + "\n");
        }
        System.out.println(sb);
    }
}
