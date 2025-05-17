// dp
class 프로그래머스_산모양타일링_레벨3_한재경 {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n][2]; //0우측셀 안채우기 1채우기
        dp[0][0] = tops[0] == 0 ? 2:3;
        dp[0][1] = 1;
        
        for (int i = 1; i < n; i++) {
            //우측셀 안채우는 경우 곱할 값
            int mul1 = tops[i] == 0 ? 2:3; //전턴 우측셀 안채울 시
            int mul2 = tops[i] == 0 ? 1:2; //전턴 우측셀 채울 시
            dp[i][0] = (dp[i-1][0] * mul1 + dp[i-1][1] * mul2) % 10007;
            
            //이번턴 우측셀 채우는 단 하나의 경우만
            dp[i][1] = (dp[i-1][0] + dp[i-1][1]) % 10007;
        }
        return (dp[n-1][0] + dp[n-1][1]) % 10007;
    }
}
