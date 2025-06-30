package _2월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_11054_가장긴바이토닉부분수열_골드4_이민선_96ms {
    static int n;
    static int[] nums;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        int idx = 0;

        while (st.hasMoreTokens()){
            nums[idx++] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i=1;i<n;i++){
            for (int j=0;j<i;j++){
                if (nums[i] > nums[j]){
                    dp[i][0] = Math.max(dp[i][0], dp[j][0]);
                }
                if (nums[i] < nums[j]){
                    // j번째 dp[j][0]과 dp[j][1] 중 큰 수
                    int max = Math.max(dp[j][0], dp[j][1]);
                    dp[i][1] = Math.max(max, dp[i][1]);
                }
            }
            dp[i][0]++;
            dp[i][1]++;
        }
        int max = 0;
        for (int[] el:dp){
            max = Math.max(max, el[0]);
            max = Math.max(max, el[1]);
        }
        System.out.println(max);
    }
}
