package _3월2주차;

import java.util.*;

public class 프로그래머스_아이템줍기_레벨3_이민선 {
    static int[][] board = new int[102][102];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // board를 만든다. (단 좌표의 2배)
        for (int[] r:rectangle){
            int sx = r[0], sy = r[1], ex = r[2], ey = r[3];
            for (int i=sx * 2;i<=ex * 2;i++){
                for (int j=sy * 2;j<=ey * 2;j++){
                    // 모든 사각형 내부를 2로 채운다.
                    board[i][j] = 2;
                }
            }
        }
        // 외부 공간인 0과 마주한 좌표를 1로 채운다.
        for (int i=0;i<102;i++){
            for (int j=0;j<102;j++){
                if (isAdjacentToOuter(i, j)){
                    board[i][j] = 1;
                }
            }
        }

        // bfs 수행
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }

    private static boolean isAdjacentToOuter(int x, int y){
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

        for (int i=0;i<8;i++){
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx > 102 || ny > 102) continue;
            if (board[x][y] != 2) continue;
            if (board[nx][ny] == 0) return true;
        }

        return false;
    }

    private static int bfs(int sx, int sy, int ex, int ey){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] visited = new int[102][102];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});

        while (!q.isEmpty()){
            int[] cur = q.poll();

            int x = cur[0], y = cur[1];

            if (x == ex && y == ey) return visited[x][y];

            for (int i=0;i<4;i++){
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx > 102 || ny > 102) continue;
                if (board[nx][ny] != 1) continue;
                if (visited[nx][ny] != 0) continue;
                visited[nx][ny] = visited[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }
        return -1;
    }
}
