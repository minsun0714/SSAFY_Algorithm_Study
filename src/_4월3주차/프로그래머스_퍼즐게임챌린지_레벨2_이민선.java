package _4월3주차;

public class 프로그래머스_퍼즐게임챌린지_레벨2_이민선 {
    static int max = 0;
    static long answer = 0;
    public long solution(int[] diffs, int[] times, long limit) {
        for (int num: diffs){
            max = Math.max(max, num);
        }

        binarySearch(diffs, times, limit);
        return answer;
    }

    private static void binarySearch(int[] diffs, int[] times, long limit){
        int l = 1;
        int r = max;

        while (l <= r){
            int level = (l + r) / 2;



            long totalTime = times[0];
            for (int i=1;i<diffs.length;i++){
                int diff = diffs[i];
                int curTime = times[i];
                int prevTime = times[i - 1];

                if (diff <= level) totalTime += curTime;
                else totalTime += (diff - level) * (curTime + prevTime) + curTime;
            }

            if (totalTime <= limit){
                r = level - 1;
                answer = level;
            } else {
                l = level + 1;
            }
        }
    }
}
