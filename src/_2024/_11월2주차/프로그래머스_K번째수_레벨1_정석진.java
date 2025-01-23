import java.util.*;
public class 프로그래머스_K번째수_레벨1_정석진 {
	class Solution {
	    public int[] solution(int[] array, int[][] commands) {
	        int[] answer = new int[commands.length];
	        for(int i=0;i<commands.length;i++) {
	            int[]temp = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
	            Arrays.sort(temp);
	            answer[i] = temp[commands[i][2]-1];
	        }          
	           return answer;
	        }
	}
}
