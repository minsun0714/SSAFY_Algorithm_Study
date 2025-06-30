package etc._4_3;

// 이분탐색
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int l = 1;
        int r = 100001;
        
        while(l < r) {
            
            int mid = l + (r - l) / 2;
            
            long curTime = getTime(mid, diffs, times);
            
            if (curTime <= limit){
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        
        answer = l;
        return answer;
    }
    
    public long getTime(int level, int[] diffs, int[] times){
        
        int puzzleCount = diffs.length;
        long totalTime = 0;
        
        for (int i = 0; i < puzzleCount; ++i){
            // 푼다
            if (diffs[i] <= level){
                totalTime += times[i];
            } else { // 못 푼다
                int retryCount = diffs[i] - level;
                totalTime += (retryCount) * times[i - 1]; // 이전 문제
                totalTime += (retryCount + 1) * times[i]; // 지금 문제
            }
        }
        
        return totalTime;
    }
}