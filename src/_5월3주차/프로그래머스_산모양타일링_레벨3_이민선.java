class 프로그래머스_산모양타일링_레벨3_이민선 {
    public int solution(int n, int[] tops) {
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = 2;
        dp2[0] = 1;
        if (tops[0] == 1){
            dp1[0] = 3;
        }

        for (int i=1;i<n;i++){
            if (tops[i] == 0){
                dp1[i] = (2 * dp1[i - 1] + dp2[i - 1]) % 10007;
            } else {
                dp1[i] = (3 * dp1[i - 1] + 2 * dp2[i - 1]) % 10007;
            }
            dp2[i] = (dp1[i - 1] + dp2[i - 1]) % 10007;
        }
        return (dp1[n - 1] + dp2[n - 1]) % 10007;
    }
}