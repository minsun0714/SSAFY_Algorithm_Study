class 프로그래머스_타겟넘버_레벨2_조준희 {
    static int answer;
    static int len;
    static int[] nums;
    public int solution(int[] numbers, int target) {
        answer = 0;
        nums=numbers;
        len=numbers.length;
        comb(0, 0, target);
        return answer;
    }
    
    public void comb(int idx, int num, int target){
        if(idx>=len){
            if(num==target){
                answer++;
            }
            return;
        }
        comb(idx+1, num+nums[idx], target);
        comb(idx+1, num-nums[idx], target);
    }
}