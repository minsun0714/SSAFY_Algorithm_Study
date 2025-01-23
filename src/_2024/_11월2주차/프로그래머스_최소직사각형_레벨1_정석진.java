public class 프로그래머스_최소직사각형_레벨1_정석진 {
	class Solution {
	    public int solution(int[][] sizes) {
	        int v = Integer.MIN_VALUE;
	        int h = Integer.MIN_VALUE;
	        for(int i=0;i<sizes.length;i++){
	            int tempv = sizes[i][0]>=sizes[i][1] ? sizes[i][0]:sizes[i][1];
	            int temph = sizes[i][0]>=sizes[i][1] ? sizes[i][1]:sizes[i][0];
	            v=tempv<v?v:tempv;
	            h=temph<h?h:temph;
	        }
	        return v*h;
	    }
	}
}
