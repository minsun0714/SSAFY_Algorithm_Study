package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_11053_가장긴증가하는부분수열_실버2_이민선_84ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = 1;
        for (int i=1;i<n;i++){
            // i이전에 i보다 작은 수가 있다면, dp[i] = dp[j] + 1
            for (int j=0;j<i;j++){
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += 1;
        }
        int max = 0;
        for (int i=0;i<n;i++){
                if (max < nums[i]){
                    max = Math.max(max, dp[i]);
                }
        }
        System.out.println(max);
    }
}
