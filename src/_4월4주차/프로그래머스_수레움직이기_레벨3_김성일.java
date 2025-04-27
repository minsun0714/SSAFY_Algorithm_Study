import java.util.*;
// BruteForce + DFS
class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static List<List<int[]>> redPaths;
    static List<List<int[]>> bluePaths;
    
    static int n, m;
    static int[][] board;
    static boolean[][] visited;
    
    static int redStartX, redStartY, redEndX, redEndY;
    static int blueStartX, blueStartY, blueEndX, blueEndY;
    
    public int solution(int[][] maze) {
        board = maze;
        n = board.length;
        m = board[0].length;
        
        redPaths = new ArrayList<>();
        bluePaths = new ArrayList<>();
        
        // 시작 위치와 도착 위치 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) { // 빨간 시작
                    redStartX = i;
                    redStartY = j;
                } else if (board[i][j] == 2) { // 파란 시작
                    blueStartX = i;
                    blueStartY = j;
                } else if (board[i][j] == 3) { // 빨간 도착
                    redEndX = i;
                    redEndY = j;
                } else if (board[i][j] == 4) { // 파란 도착
                    blueEndX = i;
                    blueEndY = j;
                }
            }
        }
        
        // 빨간 수레 경로 찾기
        visited = new boolean[n][m];
        List<int[]> redPath = new ArrayList<>();
        visited[redStartX][redStartY] = true;
        redPath.add(new int[]{redStartX, redStartY});
        dfs(redStartX, redStartY, redEndX, redEndY, redPath, redPaths);
        visited[redStartX][redStartY] = false;
        
        // 파란 수레 경로 찾기
        visited = new boolean[n][m];
        List<int[]> bluePath = new ArrayList<>();
        visited[blueStartX][blueStartY] = true;
        bluePath.add(new int[]{blueStartX, blueStartY});
        dfs(blueStartX, blueStartY, blueEndX, blueEndY, bluePath, bluePaths);
        visited[blueStartX][blueStartY] = false;
        
        // 경로 쌍 검증 및 mini 찾기
        int mini = Integer.MAX_VALUE;
        
        for (List<int[]> red : redPaths) {
            for (List<int[]> blue : bluePaths) {
                if (isValidPair(red, blue)) {
                    int result = Math.max(red.size(), blue.size());
                    mini = Math.min(mini, result);
                }
            }
        }
        
        return mini == Integer.MAX_VALUE ? 0 : mini-1;
    }
    
    private void dfs(int x, int y, int targetX, int targetY, List<int[]> path, List<List<int[]>> resultPaths) {
        if (x == targetX && y == targetY) {
            List<int[]> completedPath = new ArrayList<>();
            for (int[] p : path) {
                completedPath.add(new int[]{p[0], p[1]});
            }
            resultPaths.add(completedPath);
            return;
        }
        
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (!visited[nx][ny] && board[nx][ny] != 5) { // 5는 벽
                    visited[nx][ny] = true;
                    path.add(new int[]{nx, ny});
                    dfs(nx, ny, targetX, targetY, path, resultPaths);
                    path.remove(path.size() - 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }
    
    private boolean isValidPair(List<int[]> red, List<int[]> blue) {
        int time = 0;
        
        while (true) {
            boolean redArrived = time >= red.size();
            boolean blueArrived = time >= blue.size();
            
            if (redArrived && blueArrived) break;
            
            int[] redPos = redArrived ? red.get(red.size() - 1) : red.get(time);
            int[] bluePos = blueArrived ? blue.get(blue.size() - 1) : blue.get(time);
            
            // (1) 같은 칸에 있으면 실패
            if (redPos[0] == bluePos[0] && redPos[1] == bluePos[1]) {
                return false;
            }
            
            if (time + 1 < Math.max(red.size(), blue.size())) {
                int[] nextRedPos = (time + 1 >= red.size()) ? red.get(red.size() - 1) : red.get(time + 1);
                int[] nextBluePos = (time + 1 >= blue.size()) ? blue.get(blue.size() - 1) : blue.get(time + 1);
                
                // (2) 자리를 교차하면 실패
                if (redPos[0] == nextBluePos[0] && redPos[1] == nextBluePos[1]
                 && bluePos[0] == nextRedPos[0] && bluePos[1] == nextRedPos[1]) {
                    return false;
                }
            }
            
            time++;
        }
        
        return true;
    }
}
