package _3월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1600_말이되고픈원숭이_골드3_이민선_492ms {
    static int w, h;
    static int[][] board;
    static int[][][] visited;
    static int[] dmx = {-1, 1 ,0, 0};
    static int[] dmy = {0, 0, -1, 1};
    static int[] dhx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dhy = {-1, 1, -2, 2, -2, 2, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        for (int i=0;i<h;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<w;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new int[h][w][k + 1];

        bfs(k);
        System.out.println(-1);
    }

    private static void bfs(int k){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});
        Arrays.fill(visited[0][0], 1);

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], idx = cur[2];



            if (x == h - 1 && y == w - 1) {
                System.out.println(visited[x][y][idx] - 1);
                System.exit(0);
            }
            for (int i=0;i<4;i++){
                int nx = x + dmx[i], ny = y + dmy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;

                if (visited[nx][ny][idx] >= 1) continue;
                if (board[nx][ny] == 1) continue;

                visited[nx][ny][idx] = visited[x][y][idx] + 1;

                q.offer(new int[]{nx, ny, idx});
            }
            if (idx == k) continue;
            for (int i=0;i<8;i++){
                int nx = x + dhx[i], ny = y + dhy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;

                if (visited[nx][ny][idx + 1] >= 1) continue;
                if (board[nx][ny] == 1) continue;

                visited[nx][ny][idx + 1] = visited[x][y][idx] + 1;

                q.offer(new int[]{nx, ny, idx + 1});
            }
        }
    }
}
