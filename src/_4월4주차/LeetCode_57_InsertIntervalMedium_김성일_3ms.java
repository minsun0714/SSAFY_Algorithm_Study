import java.util.*;
// [3ms | 45mb] 
// Merge Intervals
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        if(intervals.length==0){
            return new int[][] {{newStart, newEnd}};
        }
        List<int[]> temp = new ArrayList<>();
        int mergeMin = newStart;
        int mergeMax = newEnd;
        for (int[] interval : intervals){
            if( newStart <= interval[0] && interval[0] <= newEnd ){  
                mergeMax = Math.max(mergeMax, interval[1]);
            }
            else if( newStart <= interval[1] && interval[1] <= newEnd ){ 
                mergeMin = Math.min(mergeMin, interval[0]);
            }
            else if( interval[0] <= newStart && newStart <= interval[1] ){ 
                mergeMin = Math.min(mergeMin, interval[0]);
                mergeMax = Math.max(mergeMax, interval[1]);
            }
            else if( interval[0] <= newEnd && newEnd <= interval[1] ){  
                mergeMin = Math.min(mergeMin, interval[0]);
                mergeMax = Math.max(mergeMax, interval[1]);
            }
            else{
                temp.add(interval);
            }
        }
        temp.add(new int[] {mergeMin, mergeMax});
        temp.sort(Comparator.comparing(o->o[0]));
        int[][] result = new int[temp.size()][2];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }
        return result;
    }
}

