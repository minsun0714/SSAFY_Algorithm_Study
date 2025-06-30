package algorithm;

// dp, 배낭문제
public class 프로그래머스_완전범죄_레벨2_김동빈 {
	
	public int solution(int[][] info, int n, int m) {
		int INF = 121;
        int answer = INF;
        
        int itemCount = info.length;
        int totalA = 0;
        int totalB = 0;
        
        for (int i = 0; i < itemCount; ++i) {
        	totalA += info[i][0];
        	totalB += info[i][1];
        }
        
        int[][] dp = new int[itemCount][totalB + 1];
        
        // 기저
        dp[0][info[0][1]] = info[0][0];
        
        for (int i = 1; i < itemCount; ++i) {
        	
        	for (int w = 0; w <= totalB; ++w) {
        		if (w - info[i][1] < 0) {
        			dp[i][w] = dp[i - 1][w];
        			continue;
        		}
        		
        		// 골라, 안골라
        		int stolen = dp[i - 1][w - info[i][1]] + info[i][0];
        		int notStolen = dp[i - 1][w];
        		dp[i][w] = Math.max(stolen, notStolen); 
        	}
        }
        
        // 답 찾기
        for (int w = 0; w < m; ++w) {
        	int v = totalA - dp[itemCount - 1][w];
        	if (v < n && v < answer) {
        		answer = v;
        	}
        }
        
        return answer == INF ? -1 : answer;
    }
}
