package _3월1주차;

import java.io.*;
import java.util.*;

public class 백준_2342_DanceDanceRevolution_골드3_이민선_460ms {
    static int n;
    static int[] steps;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = st.countTokens();
        steps = new int[n];
        int idx = 0;
        while (st.hasMoreElements()){
            steps[idx++] = Integer.parseInt(st.nextToken());
        }

        dp = new int[5][5][n];
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        int answer = dfs(0, 0, 0);
        System.out.println(answer);
    }

    private static int dfs(int left, int right, int depth){
        if (dp[left][right][depth] != -1) return dp[left][right][depth];
        if (depth == n - 1) {
            return 0;
        }

        dp[left][right][depth]
                    = Math.min(dfs(steps[depth], right, depth + 1) + getPower(left, steps[depth]),
                                    dfs(left, steps[depth], depth + 1) + getPower(right, steps[depth]));

        return dp[left][right][depth];
    }

    private static int getPower(int prev, int cur){
        if (prev == 0) return 2;
        if (prev == cur) return 1;
        else if (Math.abs(cur - prev) == 1 || Math.abs(cur - prev) == 3) return 3;
        else return 4;
    }
}
