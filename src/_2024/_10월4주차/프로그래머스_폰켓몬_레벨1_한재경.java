import java.io.*;
import java.util.*;

class 프로그래머스_폰켓몬_레벨1_한재경 {
    public int solution(int[] nums) {
        //n마리 중 반띵 선택. 최대한 많은 종류 개수
        //분류번호
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        if (set.size() >= nums.length / 2) { // 이미 종류가 반 이상
            return nums.length / 2;
        }
        return set.size();
    }
}
