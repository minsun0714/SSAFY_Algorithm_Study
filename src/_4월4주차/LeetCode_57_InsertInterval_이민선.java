import java.util.*;

// 이분탐색
class LeetCode_57_InsertInterval_이민선 {
    static class Interval {
        int s;
        int e;

        Interval (int s, int e){
            this.s = s;
            this.e = e;
        }

        public String toString(Interval o){
            return "(" + s + "," + e + ")";
        }
    }
    static Interval[] intervalList;
    public int[][] insert(int[][] intervals, int[] newInterval) {
        intervalList = new Interval[intervals.length];
        int idx = 0;
        for (int[] interval:intervals){
            intervalList[idx++] = new Interval(interval[0], interval[1]);
        }

        int a = binarySearchSmallerStart(newInterval[0]);

        int x = newInterval[0];
        if (a > -1){
            if (intervalList[a].e >= newInterval[0]) x = intervalList[a].s;
            else a++;
        }

        int b = binarySearchLargerStart(newInterval[1]);

        int y = newInterval[1];
        if (b > - 1 && b < intervals.length) {
            if (intervalList[b].e > newInterval[1]) y = intervalList[b].e;
        }

        List<int[]> answer = new ArrayList<>();

        for (int i=0;i<= a - 1;i++){
            answer.add(new int[]{intervalList[i].s, intervalList[i].e});
        }

        answer.add(new int[]{x, y});

        for (int i=b + 1;i<intervalList.length;i++){
            answer.add(new int[]{intervalList[i].s, intervalList[i].e});
        }

        return answer.toArray(new int[answer.size()][2]);
    }

    private static int binarySearchSmallerStart(int target){

        int s = 0;
        int e = intervalList.length - 1;

        int a = -1;

        while (s <= e){
            int mid = (s + e) / 2;

            if (intervalList[mid].s > target){
                e = mid - 1;

            } else {
                s = mid + 1;
                a = mid;
            }
        }
        return a;
    }

    private static int binarySearchLargerStart(int target){

        int s = 0;
        int e = intervalList.length - 1;

        int b = intervalList.length;

        while (s <= e){
            int mid = (s + e) / 2;

            if (intervalList[mid].s > target){
                e = mid - 1;
                b = mid;
            } else {
                s = mid + 1;

            }
        }
        return b - 1;
    }
}