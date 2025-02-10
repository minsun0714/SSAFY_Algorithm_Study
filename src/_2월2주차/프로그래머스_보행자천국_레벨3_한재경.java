//dp
class 프로그래머스_보행자천국_레벨3_한재경 {
    int MOD = 20170805;
  
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2]; //우측0 하측1;
        //행열 0줄: 벽까지 [0]에 1
        for (int i = 0; i < n; i++) { //0행 우측 직진
            if (cityMap[0][i] == 1) {
                break;
            }
            dp[0][i][0] = 1;
        }
        for (int i = 0; i < m; i++) { //0열 하측 직진
            if (cityMap[i][0] == 1) {
                break;
            }
            dp[i][0][1] = 1;
        }
      
        //행열 1줄~: 전칸 회전불가칸 아니면 [1]+=, 직진시 [0]+=
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (cityMap[i][j] == 1) {continue;} //벽
                
                //위->아래 (하측)[1
                if (cityMap[i-1][j] != 2) { //회전하는 경우 (이전에 우측[0]으로 온 애)
                    dp[i][j][1] += dp[i-1][j][0] % MOD;
                }
                dp[i][j][1] += dp[i-1][j][1] % MOD; //직진경우 (이전에 하측[1]으로 온 애)
                
                //왼->오 (우측)[0]
                if (cityMap[i][j-1] != 2) { //회전금지 아닐 때만 (이전에 하측[1]으로 온 애)
                    dp[i][j][0] += dp[i][j-1][1] % MOD;
                }
                dp[i][j][0] += dp[i][j-1][0] % MOD; //직진경우 (이전에 우측[0]으로 온 애면)
            }
        }
        return (dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD;
    }
}
