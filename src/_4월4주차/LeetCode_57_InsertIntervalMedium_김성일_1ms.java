import java.util.*;
// [1ms | 45mb] 
// IMOS
class Solution {
    static int[] imos;
    static Set<Integer> spot;
    static public void preProcess(int[] interval){
        int start = interval[0];
        int end = interval[1];
        if(start==end){
            spot.add(start);
            return;
        }
        imos[start] +=1;
        imos[end] -=1;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int globalEnd = 0;
        if(intervals.length!=0){ 
            globalEnd = intervals[intervals.length-1][1];
        }
        globalEnd = Math.max(globalEnd, newInterval[1]);
        imos = new int[globalEnd+1]; 
        spot = new HashSet<>();
        List<int[]> result = new ArrayList<>();
        for(int[] interval : intervals){
            preProcess(interval);
        }
        preProcess(newInterval);
        int pointer = 0;
        boolean alreadyStart = false;
        int startIndex = 0;
        int endIndex = 0;
        for(int i=0; i< imos.length; i++){
            int value = imos[i];
            pointer += value;
            if(alreadyStart==false && pointer>0){
                alreadyStart = true;
                startIndex = i;
            } else if(alreadyStart && pointer==0){
                alreadyStart = false;
                endIndex = i;
                result.add(new int[] {startIndex, endIndex});
            } else if(alreadyStart == false && spot.contains(i)){ 
                result.add(new int[] {i,i}); 
            }
        }
        int[][] realResult = new int[result.size()][2];
        for(int i=0; i<result.size(); i++){
            realResult[i] = result.get(i);
        }
        return realResult;
    }
}

