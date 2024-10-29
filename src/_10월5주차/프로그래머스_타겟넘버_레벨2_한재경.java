//조합
class 프로그래머스_타겟넘버_레벨2_한재경 {
    static int cnt;
    public void combinations(int[] numbers, int depth, int target, int n, int sum) {
        if (depth == n) {
            if (sum == target) {
                System.out.println(sum);
                cnt++;
            }
            return;
        }

        //depth번째 +
        combinations(numbers, depth + 1, target, n, sum + numbers[depth]);
        //depth번째 -
        combinations(numbers, depth + 1, target, n, sum - numbers[depth]);
    }
    
    public int solution(int[] numbers, int target) {
        cnt = 0;
        //n개 정수 +-해서 타겟넘버 만드는 경우의 수
        combinations(numbers, 0, target, numbers.length, 0);
        
        return cnt;
    }
}
