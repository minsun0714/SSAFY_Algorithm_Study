class 프로그래머스_산모양타일링_레벨3_김민섭 {
    
    // [SOLUTION]
    public int solution(int n, int[] tops) {
        long[][] dp = new long[n][4];
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;
        dp[0][3] = tops[0];

        for (int idx = 1; idx < n; idx++) { // idx loof
            for (int shape = 0; shape < 4; shape++) { // shape loof
            // 0 : none / 1 : left / 2 : right / 3 : uppper
                if (shape == 0) {
                    dp[idx][shape] = (dp[idx - 1][0] + dp[idx - 1][1] + dp[idx - 1][2] + dp[idx - 1][3]) % 10007;
                    continue;
                }
                if (shape == 1) {
                    dp[idx][shape] = (dp[idx - 1][0] + dp[idx - 1][1] + dp[idx - 1][3]) % 10007;
                    continue;
                }
                if (shape == 2) {
                    dp[idx][shape] = (dp[idx - 1][0] + dp[idx - 1][1] + dp[idx - 1][2] + dp[idx - 1][3]) % 10007;
                    continue;
                }
                if (shape == 3) {
                    dp[idx][shape] = tops[idx] * ((dp[idx - 1][0] + dp[idx - 1][1] + dp[idx - 1][2] + dp[idx - 1][3]) % 10007);
                    continue;
                }
            } // shape loop
        } // index loop
        
        // PRINT
        return (int) ((dp[n-1][0] + dp[n-1][1] + dp[n-1][2] + dp[n-1][3]) % 10007);
    } // [SOLUTION]
}
