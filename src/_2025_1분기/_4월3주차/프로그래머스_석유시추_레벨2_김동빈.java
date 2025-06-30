import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// bfs
public class 석유시추 {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private ArrayList<Integer> blockInfo;

    public int solution(int[][] land) {
        int answer = 0;

        int n = land.length;
        int m = land[0].length;

        blockInfo = new ArrayList<>();
        blockInfo.add(-1); // 더미값

        findOilBlock(land); // 오일 블럭 찾기

        for (int i = 0; i < m; ++i) { // 가로
            int local = 0;
            boolean[] visited = new boolean[blockInfo.size()];

            for (int j = 0; j < n; ++j) { // 세로
                if (land[j][i] == 0) continue;

                int curBlockKey = land[j][i];
                if (visited[curBlockKey]) continue;

                local += blockInfo.get(curBlockKey);
                visited[curBlockKey] = true;
            }

            answer = Math.max(answer, local);
        }

        return answer;
    }

    private void findOilBlock(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (visited[i][j] || land[i][j] == 0) continue;
                bfs(i, j, land, visited);
            }
        }
    }

    private void bfs(int r, int c, int[][] land, boolean[][] visited) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int n = land.length;
        int m = land[0].length;

        visited[r][c] = true;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));

        int BLOCK_KEY = blockInfo.size();
        land[r][c] = BLOCK_KEY;
        int totalArea = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int x = cur.x, y = cur.y;

            for (int d = 0; d < 4; ++d) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny] || land[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                land[nx][ny] = BLOCK_KEY;
                q.offer(new Point(nx, ny));
                totalArea++;
            }
        }

        blockInfo.add(totalArea);
    }
}