// bfs

package _2월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2638_치즈_골드3_이민선_196ms {
    static int n;
    static int m;
    static int[][] board;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true){
            int meltCount = bfs();
            if (meltCount == 0) break;
            answer++;
        }
    }
    public static int bfs(){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int[][] visited = new int[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        while (!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int i=0;i<4;i++){
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (board[nx][ny] == 0 && visited[nx][ny] == 1) continue;
                visited[nx][ny]++;

                // 현재 q에 넣는 값이 다음 turn에서 x, y가 될 때 다음 turn에서의 x, y가 0이어야 함.
                if (board[nx][ny] == 0) q.offer(new int[]{nx, ny});
            }
        }
        int count = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (visited[i][j] >= 2){
                    board[i][j] = 0;
                    count++;
                }
            }
        }
        if (count == 0) System.out.println(answer);
        return count;
    }
}