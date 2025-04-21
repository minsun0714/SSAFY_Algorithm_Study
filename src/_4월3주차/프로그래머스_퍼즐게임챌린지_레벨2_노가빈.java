/*
정확성  테스트
테스트 1 〉	통과 (0.03ms, 82.1MB)
테스트 2 〉	통과 (0.03ms, 81.1MB)
테스트 3 〉	통과 (0.04ms, 82.9MB)
테스트 4 〉	통과 (0.05ms, 75MB)
테스트 5 〉	통과 (0.04ms, 84MB)
테스트 6 〉	통과 (0.03ms, 86MB)
테스트 7 〉	통과 (0.03ms, 87.6MB)
테스트 8 〉	통과 (0.96ms, 90.3MB)
테스트 9 〉	통과 (0.69ms, 72.6MB)
테스트 10 〉	통과 (0.50ms, 79.3MB)
테스트 11 〉	통과 (0.58ms, 73.3MB)
테스트 12 〉	통과 (0.56ms, 90.3MB)
테스트 13 〉	통과 (0.66ms, 83.1MB)
테스트 14 〉	통과 (1.00ms, 90.7MB)
테스트 15 〉	통과 (37.02ms, 131MB)
테스트 16 〉	통과 (40.49ms, 120MB)
테스트 17 〉	통과 (37.58ms, 151MB)
테스트 18 〉	통과 (47.90ms, 115MB)
테스트 19 〉	통과 (29.54ms, 119MB)
테스트 20 〉	통과 (33.14ms, 138MB)
테스트 21 〉	통과 (22.94ms, 122MB)
 */

class 프로그래머스_퍼즐게임챌린지_레벨2_노가빈 {
    //level을 이분탐색으로 찾을 것
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int row = 1;
        int high = 100000; //레벨을 조정할 것
        int mid = (row + high) /2; //이것이 level임
        long sum = 0;

        while(row <= high){
            sum = 0;

            for(int i = 0; i < diffs.length; i++){
                // System.out.println("diff : " + diffs[i] + ", mid : " + mid + ", i : " + i);

                if(diffs[i] <= mid){
                    sum += times[i];
                } else{
                    sum += (diffs[i]-mid) * (times[i] + times[i-1]) + times[i];
                }
            }

            if(sum <= limit){
                answer = mid;
                high = mid-1;
                mid = (row + high)/2;
            } else {
                row = mid + 1;
                mid = (row + high)/2;
            }
        }
        return answer;
    }
}
