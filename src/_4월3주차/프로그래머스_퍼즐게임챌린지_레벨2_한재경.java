class 프로그래머스_퍼즐게임챌린지_레벨2_한재경 {
    static int maxDiff; //최대난이도
    static int n;
    
    //소요시간 계산. 난이도, 현재시간, 이전시간, 레벨, 제한시간
    long getTime(int diff, int time_cur, int time_prev, int level, long time) {
        if (diff <= level) {
            return time_cur;
        }
        return (time_cur + time_prev) * (diff - level) + time_cur;
    }
    
    //특정 숙련도로 전체 문제 풀이 가능여부
    boolean canSolve(int[] diffs, int[] times, int level, long time) {
        for (int i = 1; i < n; i++) {
            time -= getTime(diffs[i], times[i], times[i-1], level, time);
            if (time < 0) {
                return false;
            }
        }
        return true;
    }
    
    //숙련도 이진탐색
    int binary_search(int[] diffs, int[] times, long time) {
        int l = 1;
        int r = maxDiff;
        int mid = 0;
        while(l <= r) {
            mid = (l + r) / 2;
            boolean solve = canSolve(diffs, times, mid, time);
            if (solve) { //가능하면
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        n = diffs.length;
        long time = limit;
        time -= times[0];
        
        for (int i : diffs) {
            maxDiff = Math.max(i, maxDiff);
        }
        
        //숙련도 이진탐색 0 ~ max(diff)
        return binary_search(diffs, times, time);
    }
}
