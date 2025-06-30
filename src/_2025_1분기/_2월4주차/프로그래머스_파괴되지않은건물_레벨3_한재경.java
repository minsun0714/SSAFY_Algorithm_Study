//누적합 주요 문제!
class 프로그래머스_파괴되지않은건물_레벨3_한재경 {
    static int n;
    static int[][] board;
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] sum = new int[n+1][m+1]; //누적합 배열
        Solution.board = board;
        //skill: [타입, 시작점x, y, 종료점x, y, 값]. 타입1 적, 2 아군
        for (int[] s : skill) {
            int type = s[0], sx = s[1], sy = s[2], ex = s[3], ey = s[4], degree = s[5];
            int d;
            if (type == 2) {d = degree; }
            else {d = -degree; }
            // 0행 1열 ~ 1행 3열까지 칠할때
            // 0 1 1 1 0  sxsy+, sxey-누적합복구
            // 0 0 0 0 0
            // 0 -1 -1 -1 -1 exsy-, exey+제거복구
            // 0 0 0 0 0
            sum[sx][sy] += d;
            sum[sx][ey+1] -= d;
            sum[ex+1][sy] -= d;
            sum[ex+1][ey+1] += d;
        }
        //가로 누적합
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i][j+1] += sum[i][j];
            }
        }
        //세로 누적합
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i+1][j] += sum[i][j];
            }
        }
        
        int cnt = 0;
        for (int i =0 ; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + sum[i][j] > 0) {cnt++; }
            }
        }
        return cnt;
    }
}
