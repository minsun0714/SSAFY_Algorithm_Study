package org.example;

import java.io.*;
import java.util.*;

//dfs + dp (bfs + dp는 큐로 인해 메모리초과)
public class 백준_1937_욕심쟁이판다_골드3_한재경_424ms {
    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{-1, 1, 0, 0};
    static int n;
    static int[][] grid;
    static int[][] dp; //"해당 지점에서 출발"할 때의 최대 방문수

    public static int dfs(int x, int y) {
        if (dp[x][y] != 0) { //현재위치가 이미 메모된 위치면
            return dp[x][y]; //즉시 반환
        }
        dp[x][y] = 1; //1스텝으로 시작
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < n && 0 <= ny && ny < n && grid[nx][ny] > grid[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1); //현재지점 = 현재갱신된 dp / next 진행된 dp
                //dp[x][y]도 이미 for문 내에서 갱신된 값이므로 둘 중 최댓값으로 채움
            }
        }
        return dp[x][y];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        dp = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }
        int ans = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ans < dp[i][j]) {
                    ans = dp[i][j];
                }
            }
        }
        System.out.println(ans);
    }
}
