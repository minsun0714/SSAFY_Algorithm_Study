import java.io.*;
import java.util.*;

// 구현

class LeetCode_57_InsertIntervalMedium_김민섭_1ms {
	
    // [INSERT]
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int length = intervals.length;

        int index = 0;

        int newLeft = newInterval[0];
        int newRight = newInterval[1];

        while (index < length && intervals[index][1] < newLeft) {
            index++;
        }

        if ( (index < length && newRight < intervals[index][0]) || index == length) {
            int[][] newIntervals = new int[length + 1][2];
            for (int i = 0; i < index; i++) {
                newIntervals[i] = intervals[i];
            }
            newIntervals[index] = newInterval;
            for (int i = index; i < length; i++) {
                newIntervals[i + 1] = intervals[i];
            }
            return newIntervals;
        }

        newLeft = (int) Math.min(newLeft, intervals[index][0]);
        int collapsed = 0;

        while (index < length && intervals[index][1] < newRight) {
            collapsed++;
            index++;
            if (index == length || newRight < intervals[index][0]) {
                int[][] newIntervals = new int[length - collapsed + 1][2];
                for (int i = 0; i < index - collapsed; i++) {
                    newIntervals[i] = intervals[i];
                }
                newIntervals[index - collapsed] = new int[] {newLeft, newRight};
                for (int i = index; i < length; i++) {
                    newIntervals[i - collapsed + 1] = intervals[i];
                }
                return newIntervals;
            }
        }

        newRight = (int) Math.max(newRight, intervals[index][1]);

        int[][] newIntervals = new int[length - collapsed][2];
        for (int i = 0; i < index - collapsed; i++) {
            newIntervals[i] = intervals[i];
        }
        newIntervals[index - collapsed] = new int[] {newLeft, newRight};
        for (int i = index + 1; i < length; i++) {
            newIntervals[i - collapsed] = intervals[i];
        }
        return newIntervals;
    } // [INSERT]

}