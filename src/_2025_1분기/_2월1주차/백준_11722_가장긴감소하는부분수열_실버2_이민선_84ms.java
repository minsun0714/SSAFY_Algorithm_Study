package _2월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_11722_가장긴감소하는부분수열_실버2_이민선_84ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreTokens()){
            nums[idx++] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];

        for (int i=0;i<n;i++){
            for (int j=0;j<i;j++){
                if (nums[i] < nums[j]){
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
        }
        int max = 0;
        for (int num:dp){
            max = Math.max(max, num);
        }
        System.out.println(max);
    }
}
