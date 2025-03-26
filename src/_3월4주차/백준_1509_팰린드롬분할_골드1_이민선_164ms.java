package _3월4주차;

import java.io.*;

public class 백준_1509_팰린드롬분할_골드1_이민선_164ms {
    static int n;
    static char[] chars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        n = input.length();
        chars = input.toCharArray();

        int[][] subPalindromeDp = new int[n + 1][n + 1];
        for (int i=n;i>0;i--){
            for (int j=i;j<=n;j++){
                if (i == j){
                    subPalindromeDp[i][j] = 1;
                    continue;
                }

                if (j == i + 1 && chars[j - 1] == chars[i - 1]) {
                    subPalindromeDp[i][j] = 2;
                    continue;
                }

                if (chars[i - 1] == chars[j - 1] && subPalindromeDp[i + 1][j - 1] > 0){
                    subPalindromeDp[i][j] = subPalindromeDp[i + 1][j - 1] + 2;
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int j=1;j<=n;j++){
            int min = Integer.MAX_VALUE;
            for (int i=1;i<=j;i++){
                int cur = subPalindromeDp[i][j];

                if (cur == 0) continue;
                min = Math.min(dp[j - cur] + 1, min);
            }
            dp[j] = min;
        }
        System.out.println(dp[n]);
    }
}
