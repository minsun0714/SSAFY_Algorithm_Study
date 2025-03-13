package _3월2주차;

import java.io.*;
import java.util.*;

// dfs, dp
public class 백준_20166_문자열지옥에빠진호석_골드4_이민선_196ms {
    static int n, m, k;
    static char[][] board;
    static int[][][] dp;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[n][m];

        for (int i=0;i<n;i++){
            String row = br.readLine();
            for (int j=0;j<m;j++){
                board[i][j] = row.charAt(j);
            }
        }

        while (k-- > 0){
            String word = br.readLine();
            dp = new int[n][m][word.length()];
            for (int i=0;i<n;i++){
                for (int j=0;j<m;j++){
                    Arrays.fill(dp[i][j], -1);
                }
            }
            int answer = 0;
            for (int i=0;i<n;i++){
                for (int j=0;j<m;j++){
                    if (board[i][j] == word.charAt(0)) {
                        answer += dfs(i, j, 0, word);
                    }
                }

            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int x, int y, int depth, String word){
        if (dp[x][y][depth] != -1) return dp[x][y][depth];

        if (depth == word.length() - 1) return 1;

        dp[x][y][depth] = 0;

        for (int i=0;i<8;i++){
            int nx = (n + x + dx[i]) % n, ny = (m + y + dy[i]) % m;

            if (board[nx][ny] != word.charAt(depth + 1)) continue;

            dp[x][y][depth] += dfs(nx, ny, depth + 1, word);
        }

        return dp[x][y][depth];
    }
}
