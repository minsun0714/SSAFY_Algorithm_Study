package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1937_욕심쟁이판다_골드3_이민선_404ms {
    static int n;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] dp;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                dfs(i, j);
            }
        }
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer);
    }

    public static int dfs(int x, int y){
        if (dp[x][y] != 0) return dp[x][y];

        dp[x][y] = 1;

        for (int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

            if (board[nx][ny] <= board[x][y]) continue;

            dfs(nx, ny);

            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);

        }
        return dp[x][y];
    }
}
