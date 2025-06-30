package _2월4주차;

// 누적합
public class 프로그래머스_파괴되지않은건물_레벨3_이민선 {
    static int n, m;
    public int solution(int[][] board, int[][] skill) {
        n = board.length;
        m = board[0].length;
        int[][] temp = new int[n + 1][m + 1];
        for (int[] s:skill){
            int type = s[0], r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4], degree = s[5];
            int val = type == 1 ? -degree : degree;
            temp[r1][c1] += val;
            temp[r1][c2 + 1] += -val;
            temp[r2 + 1][c1] += -val;
            temp[r2 + 1][c2 + 1] += val;
        }

        for (int i=0;i<n;i++){
            for (int j=1;j<m;j++){
                temp[i][j] += temp[i][j - 1];
            }
        }

        for (int i=1;i<n;i++){
            for (int j=0;j<m;j++){
                temp[i][j] += temp[i - 1][j];
            }
        }

        int answer = 0;

        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (board[i][j] + temp[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}
