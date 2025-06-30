package _2월4주차;

import java.io.*;
import java.util.Arrays;

public class 백준_12852_1로만들기2_골드5_이민선_ms {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        int[] trace = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i=2;i<=n;i++){
            if (i % 3 == 0) {
                dp[i] = dp[i / 3];
                trace[i] = i / 3;
            }
            if (i % 2 == 0 && dp[i / 2] < dp[i]) {
                dp[i] = dp[i / 2];
                trace[i] = i / 2;
            }
            if (dp[i - 1] < dp[i]){
                dp[i] = dp[i - 1];
                trace[i] = i - 1;
            }
            dp[i]++;
        }
        System.out.println(dp[n]);
        while (n >= 1){
            sb.append(n).append(" ");
            n = trace[n];
        }
        System.out.println(sb);
    }
}
