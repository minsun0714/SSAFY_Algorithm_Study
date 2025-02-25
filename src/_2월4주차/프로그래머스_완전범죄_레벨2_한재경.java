import java.util.*;

//dp
class 프로그래머스_완전범죄_레벨2_한재경 {
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        int l = info.length;
        int[][] dp = new int[l+1][m];
        //i번째아이템까지 b가 j만큼 흔적 남겼을때 a흔적 메모.
        for(int i=0;i<=l;i++){
            Arrays.fill(dp[i], 100000);
        }
        dp[0][0] = 0;
        for (int i=1;i<=l;i++){
            //i번째 아이템 b가 j만큼 흔적 남기는 경우
            for(int j=0;j<m;j++){
                //a선택: min(현재값유지, a흔적 더하기)
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+info[i-1][0]);
                if (j+info[i-1][1]<m) {
                    //b선택: min(b흔적더하기, 현재값유지=이전아이템에서 b흔적그대로)
                    dp[i][j+info[i-1][1]] = Math.min(dp[i][j+info[i-1][1]], dp[i-1][j]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i=0;i<m;i++){
            ans = Math.min(ans, dp[l][i]);
        }
        return ans >= n ? -1 : ans;
    }
}
