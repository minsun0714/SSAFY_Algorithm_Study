import java.util.*;

// bfs
class 프로그래머스_석유시추_레벨2_한재경 {
    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[] memo;
    
    //좌측지점, 우측지점 배열 반환
    public void bfs(int[][] grid, int n, int m, int sx, int sy) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        grid[sx][sy] = 2;
        int left = Integer.MAX_VALUE;
        int right = 0;
        int cnt = 0;
        
        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0];
            int y = xy[1];
            left = Math.min(left, y);
            right = Math.max(right, y);
            cnt++;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && grid[nx][ny] == 1) {
                    q.add(new int[]{nx, ny});
                    grid[nx][ny] = 2;
                }
            }
        }
        for (int i = left; i <= right; i++) {
            memo[i] += cnt;
        }
    }
    public int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int m = land[0].length;
        memo =  new int[m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1) {
                    bfs(land, n, m, i, j);
                }
            }
        }
        int max = 0;
        for (int j = 0; j < m; j++) {
            max = Math.max(memo[j], max);
        }
        return max;
    }
}
