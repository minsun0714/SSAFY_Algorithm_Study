// dfs

package _2월1주차;

import java.io.*;
import java.util.*;

public class 소프티어_함께하는효도_레벨3_이민선_99ms {
    static int n, m;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] starts;
    static int[][][] visited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new int[n][n][2];

        starts = new int[m][2];
        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            starts[i][0] = x;
            starts[i][1] = y;
            visited[x][y][0] = i + 1;
            visited[x][y][1] = 1;
        }

        dfs(starts[0][0], starts[0][1], 1, board[starts[0][0]][starts[0][1]], 0);
        System.out.println(answer);
    }
    public static void dfs(int x, int y, int depth, int sum, int idx){
        answer = Math.max(answer, sum);
        if (depth == 4){
            idx++;
            if (idx == m) return;
            x = starts[idx][0];
            y = starts[idx][1];
            depth = 1;
            sum += board[x][y];
        }

        for (int i=0;i<4;i++){
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (visited[nx][ny][0] == idx + 1) continue;
            if (visited[nx][ny][1] == depth + 1) continue;

            int prevIdx = visited[nx][ny][0];
            int prevDepth = visited[nx][ny][1];
            visited[nx][ny][0] = idx + 1;
            visited[nx][ny][1] = depth + 1;
            dfs(nx, ny, depth + 1, prevIdx > 0 ? sum : sum + board[nx][ny], idx);
            visited[nx][ny][0] = prevIdx;
            visited[nx][ny][1] = prevDepth;
        }
    }
}
