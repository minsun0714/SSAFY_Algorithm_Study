import java.util.*;

class 프로그래머스_레벨3_코딩테스트공부_한재경 {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;
        
        // 모든 문제를 풀 수 있게 요구하는 최대 알고력, 코딩력 구하기
        for (int[] p : problems) { //a요구값,c요구값,a보상,c보상,cost
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }
        
        // 이미 충분한 능력이면 굳이 올릴 필요 없음
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        // dp[i][j]: 알고력 i, 코딩력 j를 달성하는 최소 소요 시간
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;
        
        // dp 테이블 채우기
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 공부해서 알고력 1 올리기
                if (i < maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                // 공부해서 코딩력 1 올리기
                if (j < maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                
                // 문제 풀기
                for (int[] p : problems) {
                    if (i >= p[0] && j >= p[1]) {
                        int nextAlp = Math.min(maxAlp, i + p[2]);
                        int nextCop = Math.min(maxCop, j + p[3]);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + p[4]);
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}
