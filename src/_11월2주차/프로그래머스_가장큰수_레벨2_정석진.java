import java.util.*;
public class 프로그래머스_가장큰수_레벨2_정석진 {
	class Solution {
	    public String solution(int[] numbers) {
	        StringBuilder sb=  new StringBuilder();
	        Integer[] integers = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
	        boolean flag = false;
	        Arrays.sort(integers, new Comparator<Integer>() {
	            @Override
	            public int compare(Integer o1, Integer o2) {
	                String str1 = o1.toString() + o2.toString();
	                String str2 = o2.toString() + o1.toString();
	                return str2.compareTo(str1);
	            }    
	        });
	        for(int i=0;i<integers.length;i++){
	            sb.append(integers[i]);
	            if(integers[i]!=0){
	                flag = true;
	            }
	        }
	        if(flag) return sb.toString();
	        return "0";
	    }
	}
}
