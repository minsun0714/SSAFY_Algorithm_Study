import java.util.*;

class LeetCode_2503_MaximumNumberOfPointsFromGridQueries_이민선_130ms {
    static int m;
    static int n;
    public int[] maxPoints(int[][] grid, int[] queries) {
        m = grid.length;
        n = grid[0].length;

        int max = 0;

        for (int i=0;i<queries.length;i++){
            max = Math.max(queries[i], max);
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        boolean[][] visited= new boolean[m][n];
        visited[0][0] = true;

        int[] memo = new int[max + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(grid[a[0]][a[1]], grid[b[0]][b[1]]));

        pq.offer(new int[]{0, 0, grid[0][0]});

        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int currentMax = cur[2];

            if (currentMax > max) break;

            memo[currentMax]++;

            for (int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];


                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;

                pq.offer(new int[]{nx, ny, Math.max(currentMax, grid[nx][ny])});
            }
        }

        for (int i=1;i<memo.length;i++){
            memo[i] += memo[i - 1];
        }

        int[] answer = new int[queries.length];
        for (int i=0;i<answer.length;i++){
            answer[i] = memo[queries[i] - 1];
        }

        return answer;
    }
}