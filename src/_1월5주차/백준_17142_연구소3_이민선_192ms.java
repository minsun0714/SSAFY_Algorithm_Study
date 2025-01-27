// backtracking, bfs

package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_17142_연구소3_이민선_192ms {
    static int n;
    static int m;
    static int[][] board;
    static List<int[]> virusList;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static long answer = 1000000000;
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
        virusList = new ArrayList<>();
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (board[i][j] == 2){
                    virusList.add(new int[]{i, j});
                }
            }
        }
        int[][] selected = new int[m][2];
        backtracking(0, selected, 0);
        System.out.println(answer == 1000000000 ? -1 : answer - 1);
    }

    private static void backtracking(int depth, int[][] selected, int start) {
        if (depth == m) {
            long max_val = spreadVirus(selected);
            if (max_val != -1) answer = Math.min(answer, max_val);
            return;
        }
        for (int i=start;i<virusList.size();i++){
            selected[depth] = virusList.get(i);
            backtracking(depth + 1, selected, i + 1);
            selected[depth] = new int[]{0, 0};
        }
    }

    private static long spreadVirus(int[][] selected){
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[n][n];
        for (int[] s : selected){
            q.offer(s);
            visited[s[0]][s[1]] = 1;
        }

        while (!q.isEmpty()){
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];

            for (int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if (visited[nx][ny] > 0) continue;

                if (board[nx][ny] == 1) continue;

                visited[nx][ny] = visited[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }
        long max_val = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (visited[i][j] == 0 && board[i][j] == 0) return -1;
                if (board[i][j] != 2) max_val = Math.max(max_val, visited[i][j]);
                else max_val = Math.max(max_val, 1);
            }
        }
        return max_val;
    }
}
