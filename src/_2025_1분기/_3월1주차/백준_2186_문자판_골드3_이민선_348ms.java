package _3월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2186_문자판_골드3_이민선_348ms {
    static int n, m, k;
    static char[][] board;
    static String word;
    static int[][][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board= new char[n][m];
        for (int i=0;i<n;i++){
            String input = br.readLine();
            for (int j=0;j<m;j++){
                board[i][j] = input.charAt(j);
            }
        }

        word = br.readLine();
        dp = new int[n][m][word.length()];
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        int answer = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (board[i][j] == word.charAt(0))
                    answer += dfs(i, j, 0);
            }
        }
        System.out.println(answer);
    }

    private static int dfs(int x, int y, int depth){
        if (dp[x][y][depth] != -1) return dp[x][y][depth];
        if (depth >= word.length() - 1) return 1;

        dp[x][y][depth] = 0;

        for (int i=0;i<4;i++){
            for (int d = 1;d<=k;d++){
                int nx = x + dx[i] * d, ny = y + dy[i] * d;

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (board[nx][ny] != word.charAt(depth + 1)) continue;
                dp[x][y][depth] += dfs(nx, ny, depth + 1);
            }
        }
        return dp[x][y][depth];
    }
}
