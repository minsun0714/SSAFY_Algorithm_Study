package _4월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_14442_벽부수고이동하기2_골드3_이민선_1712ms {
    static int n, m, k;
    static int[][] board;
    static int[][][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for (int i=0;i<n;i++){
            String input = br.readLine();
            for (int j=0;j<m;j++){
                board[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        dp = new int[n][m][k + 1];

        dp[0][0][0] = 1;

        bfs(n, m, k);
        int answer = -1;
        for (int num: dp[n - 1][m - 1]) if (num != 0) answer = num;
        System.out.println(answer);
    }

    private static void bfs(int n, int m, int k){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});

        while (!q.isEmpty()){
            int[] cur = q.poll();

            int x = cur[0], y = cur[1], depth = cur[2];

            if (x == n - 1 && y == m - 1) break;
            for (int i=0;i<4;i++){
                int nx = x + dx[i], ny = y + dy[i], nd = depth;

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (board[nx][ny] == 1) nd++;
                if (nd > k) continue;
                if (dp[nx][ny][nd] > 0) continue;

                dp[nx][ny][nd] = dp[x][y][depth] + 1;

                q.offer(new int[]{nx, ny, nd});
            }
        }
    }
}
