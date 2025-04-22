package _4월4주차;

class 프로그래머스_수레움직이기_레벨3_이민선 {
    static int n, m;
    static int rx, ry;
    static int bx, by;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visitedR;
    static int[][] visitedB;
    static int answer = Integer.MAX_VALUE;
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;

        visitedR = new int[n][m];
        visitedB = new int[n][m];

        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (maze[i][j] == 1) {
                    rx = i;
                    ry = j;
                }
                else if (maze[i][j] == 2){
                    bx = i;
                    by = j;
                }
                if (maze[i][j] == 5){
                    visitedR[i][j] = Integer.MAX_VALUE;
                    visitedB[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        visitedR[rx][ry] = 1;
        visitedB[bx][by] = 1;

        dfs(maze, 0, rx, ry, bx, by);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private static void dfs(int[][] maze, int depth, int rx, int ry, int bx, int by){
        if (maze[rx][ry] == 3 && maze[bx][by] == 4) {
            answer = Math.min(answer, Math.max(visitedR[rx][ry], visitedB[bx][by]) - 1);
            return;
        }
        if (maze[rx][ry] == 3) {
            for (int j=0;j<4;j++){
                int nbx = bx + dx[j];
                int nby = by + dy[j];

                if (nbx < 0 || nby < 0 || nbx >= n || nby >= m) continue;
                if (visitedB[nbx][nby] > 0) continue;

                if (nbx == rx && nby == ry) continue;

                visitedB[nbx][nby] = visitedB[bx][by] + 1;
                dfs(maze, depth + 1, rx, ry, nbx, nby);
                visitedB[nbx][nby] = 0;
            }
        }

        else if (maze[bx][by] == 4) {
            for (int j=0;j<4;j++){
                int nrx = rx + dx[j];
                int nry = ry + dy[j];

                if (nrx < 0 || nry < 0 || nrx >= n || nry >= m) continue;
                if (visitedR[nrx][nry] > 0) continue;

                if (nrx == bx && nry == by) continue;

                visitedR[nrx][nry] = visitedR[rx][ry] + 1;
                dfs(maze, depth + 1, nrx, nry, bx, by);
                visitedR[nrx][nry] = 0;
            }
        }

        else {
            for (int i=0;i<4;i++){
                int nrx = rx + dx[i];
                int nry = ry + dy[i];

                if (nrx < 0 || nry < 0 || nrx >= n || nry >= m) continue;

                if (visitedR[nrx][nry] > 0) continue;

                for (int j=0;j<4;j++){
                    int nbx = bx + dx[j];
                    int nby = by + dy[j];

                    if (nbx < 0 || nby < 0 || nbx >= n || nby >= m) continue;
                    if (visitedB[nbx][nby] > 0) continue;

                    if (nrx == nbx && nry == nby) continue;
                    if (nrx == bx && nry == by && nbx == rx && nby == ry) continue;

                    visitedR[nrx][nry] = visitedR[rx][ry] + 1;
                    visitedB[nbx][nby] = visitedB[bx][by] + 1;
                    dfs(maze, depth + 1, nrx, nry, nbx, nby);
                    visitedR[nrx][nry] = 0;
                    visitedB[nbx][nby] = 0;
                }
            }
        }
    }
}