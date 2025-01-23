//bfs
import java.util.*;

class 프로그래머스_게임맵최단거리_레벨3_한재경 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    public int bfs(int[][] maps, int n, int m) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0, 1});
        int[][] visited = new int[n][m];
        visited[0][0] = 1;
        
        while (!q.isEmpty()) {
            int[] xyc = q.poll();
            int x = xyc[0];
            int y = xyc[1];
            int cnt = xyc[2];
            
            if (x == n - 1 && y == m - 1) {
                return cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && maps[nx][ny] == 1 && visited[nx][ny] == 0) {
                    visited[nx][ny] = 1;
                    q.add(new int[] {nx, ny, cnt + 1});
                }
            }
        }
        return -1;
    }
    public int solution(int[][] maps) {
        return bfs(maps, maps.length, maps[0].length);
    }
}
