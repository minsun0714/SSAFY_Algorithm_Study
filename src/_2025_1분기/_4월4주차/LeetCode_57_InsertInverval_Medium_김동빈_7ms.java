package etc._4_4;

import java.io.*;
import java.util.*;

// 반복문
class Solution {
	public int[][] insert(int[][] intervals, int[] newInterval) {
	    int n = intervals.length;
	    int ns = newInterval[0];
	    int ne = newInterval[1];
	    
	    int start = 0;
	    while (start < n && intervals[start][1] < ns) {
	        start++;
	    }

	    int end = start;
	    while (end < n && intervals[end][0] <= ne) {
	        end++;
	    }
        end--;

        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        if (start <= end){
            newStart = Math.min(newInterval[0], intervals[start][0]);
            newEnd = Math.max(newInterval[1], intervals[end][1]);
        }


        // 결과 리스트
        ArrayList<int[]> result = new ArrayList<>();

        // 전
        for (int i = 0; i < start; i++) {
            result.add(intervals[i]);
        }

        // new
        result.add(new int[]{newStart, newEnd});

        // 후
        for (int i = end + 1; i < n; i++) {
            result.add(intervals[i]);
        }
        
        int[][] answer = new int[result.size()][2];
        for (int i = 0; i < result.size(); ++i) {
        	answer[i] = result.get(i);
        }
        return answer;
	}
}