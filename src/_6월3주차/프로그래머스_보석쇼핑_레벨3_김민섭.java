import java.io.*;
import java.util.*;

class 프로그래머스_보석쇼핑_레벨3_김민섭 {
    
    // [SOLUTION]
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        int gemNum = 0;
        int[] gemArr = new int[gems.length + 1];
        
        for (int i = 0; i < gems.length; i++) {
            if (map.get(gems[i]) == null) {
                map.put(gems[i], ++gemNum);
                gemArr[i + 1] = map.get(gems[i]);
                continue;
            }
            gemArr[i + 1] = map.get(gems[i]);
        }
        
        int[] answer = {1, 1};
        
        int gemsGot = 0;
        int[] gemsHave = new int[gemNum + 1];
        int minLength = 100_001;
        
        for (int right = 1; right < gemArr.length; right++) {
            gemsHave[gemArr[right]]++;
            if (gemsHave[gemArr[right]] == 1) {
                gemsGot++;
            }
            if (gemsGot == gemNum) {
                minLength = right;
                answer = new int[] {1, right};
                if (minLength == gemNum) {
                    return answer;
                }
                break;
            }
        }
        
        int left = answer[0];
        int right = answer[1];
        
        while(right < gemArr.length) {
            while(1 < gemsHave[gemArr[left]]) {
                gemsHave[gemArr[left]]--;
                left++;
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    answer = new int[] {left, right};
                }
            }
            right++;
            if (right < gemArr.length) {
                gemsHave[gemArr[right]]++;
            }
        }
        
        return answer;
    } // [SOLUTION]
    
}