package _4월2주차;

import java.io.*;
import java.util.*;

public class 백준_1695_팰린드롬만들기_골드3_이민선_212ms {
    static int n;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        nums = new int[n];
        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][n];

        for (int i=n - 1;i>=0;i--){
            for (int j=i;j<n;j++){
                if (i == j){
                    dp[i][j] = 1;
                    continue;
                }
                if (j == i + 1){
                    dp[i][j] = nums[i] == nums[j] ? 2 : 1;
                    continue;
                }
                if (nums[i] == nums[j]){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        System.out.println(n - dp[0][n - 1]);
    }
}
