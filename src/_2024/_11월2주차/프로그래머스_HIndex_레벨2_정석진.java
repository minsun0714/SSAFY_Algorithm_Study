class 프로그래머스_HIndex_레벨2_정석진 {
	class Solution {
	    public int solution(int[] citations) {
	        int answer = 0;
	        int[] freq = new int[10001];
	        for(int i=0;i<citations.length;i++){
	            freq[citations[i]]++;
	        }
	        int num = 0;
	        for(int i=10000;i>=0;i--){
	            num += freq[i];
	            if(num>=i){
	                return i;
	            }
	        }
	        return -1;
	    }
	}
}