package _2월2주차;

import java.util.*;

public class LeetCode_57_InsertInterval_Medium_이민선 {
    static int n;
    public int[][] insert(int[][] intervals, int[] newInterval) {
        n = intervals.length;

        List<int[]> intervalList = new ArrayList<>();
        int idx = 0;
        // 겹치는 구간 앞쪽까지
        while (idx < n && intervals[idx][1] < newInterval[0]){
            intervalList.add(intervals[idx++]);
        }

        // 겹치는 구간 하나로 머지
        while (idx < n && intervals[idx][0] <= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[idx][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[idx][1]);
            idx++;
        }

        intervalList.add(newInterval);

        // 겹치지 않는 나머지
        while (idx < n){
            intervalList.add(intervals[idx++]);
        }

        int[][] answer = new int[intervalList.size()][2];
        int i = 0;
        for (int[] interval:intervalList){
            answer[i++] = interval;
        }
        return answer;
    }
}
