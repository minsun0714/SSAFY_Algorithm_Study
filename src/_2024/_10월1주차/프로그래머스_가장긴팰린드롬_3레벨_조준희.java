public class 프로그래머스_가장긴팰린드롬_3레벨_조준희 {
static String[] strArr;
    
    public int solution(String s)
    {
        int answer = 1;
        strArr = s.split("");
        
            
        for(int i = 0; i<strArr.length; i++){
        	//짝수, 홀수 확인
            int curMax = Math.max(getLen(i-1, i+1)+1, getLen(i, i+1));
            answer = Math.max(answer, curMax);
        }

        return answer;
    }
    public int getLen(int left, int right){
        int length = 0;
        while(right<strArr.length && left>=0){
            if(!strArr[left].equals(strArr[right])){
                break;
            }
            right++;
            left--;
            
            length+=2;
        }
        return length;
    }
}
