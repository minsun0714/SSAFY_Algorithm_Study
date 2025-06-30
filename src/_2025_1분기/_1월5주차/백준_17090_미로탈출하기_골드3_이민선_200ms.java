// dfs, dp
package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 백준_17090_미로탈출하기_골드3_이민선_200ms {
    static int n, m;
    static char[][] board;
    static Map<Character, int[]> dir = new HashMap<>();
    static {
        dir.put('D', new int[]{1, 0});
        dir.put('U', new int[]{-1, 0});
        dir.put('L', new int[]{0, -1});
        dir.put('R', new int[]{0, 1});
    }
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for (int i=0;i<n;i++){
            String input = br.readLine();
            for (int j=0;j<m;j++){
                board[i][j] = input.charAt(j);
            }
        }
        dp = new int[n][m];
        for (int i=0;i<n;i++){
            Arrays.fill(dp[i], -1);
        }
        int answer = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                answer += dfs(i, j);
            }
        }
        System.out.println(answer);
    }

    public static int dfs(int x, int y){
        if (x < 0 || y < 0 || x >= n || y >= m) return 1;

        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        int nx = x + dir.get(board[x][y])[0];
        int ny = y + dir.get(board[x][y])[1];

        dp[x][y] = dfs(nx, ny);
        return dp[x][y];
    }
}
