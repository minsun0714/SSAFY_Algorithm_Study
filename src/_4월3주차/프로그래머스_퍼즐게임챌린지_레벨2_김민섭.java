package _4월3주차;
import java.io.*;
import java.util.*;
    
class Solution {
    
    // [DECLARATION]
    static long calculator(int level, int[] diffs, int[] times) {
        long sum = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                sum += times[i];
            } else {
                sum += (diffs[i] - level) * (times[i - 1] + times[i]) + times[i];
            }
        }
        
        return sum;
    }
    // [DECLARATION]
    
    // [SOLUTION]
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 200_000;
        int mid = (left + right) / 2;
        
        int answer = 200_000;
        
        while (left <= right) {
            if (calculator(mid, diffs, times) <= limit) {
                answer = mid;
                
                right = mid - 1;
                mid = (left + right) / 2;
            } else {
                left = mid + 1;
                mid = (left + right) / 2;
            }
        }
        
        return answer;
    } // [SOLUTION]
    
}
