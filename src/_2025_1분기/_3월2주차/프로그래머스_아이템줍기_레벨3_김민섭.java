import java.util.*;
import java.io.*;

class Solution {
    
    // [DECLARATION]
    static int[][] around = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
    static int[][] around2 = { {0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };
    // [DECLARATION]
    
    // [SOULTION]
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        int[][] map = new int[102][102];
        for (int i = 0; i < rectangle.length; i++) {
            int startX = rectangle[i][0] * 2;
            int startY = rectangle[i][1] * 2;
            int endX = rectangle[i][2] * 2;
            int endY = rectangle[i][3] * 2;
            for (int row = startX; row <= endX; row++) {
                for (int col = startY; col <= endY; col++) {
                    map[row][col] = 1;
                }
            }
        }
        
        for (int row = 2; row <= 100; row++) {
            for (int col = 2; col <= 100; col++) {
                if (map[row][col] == 1) {
                    a : for (int[] aro : around2) {
                        int R = row + aro[0];
                        int C = col + aro[1];
                        if (map[R][C] == 0) {
                            map[row][col] = 2;
                            break a;
                        }
                    }
                }
            }
        }
        
        
        
        boolean[][] visited = new boolean[102][102];
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[] {characterX * 2, characterY * 2});
        visited[characterX * 2][characterY * 2] = true;
        
        w : while (!queue.isEmpty()) {
            answer++;
            
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int row = curr[0];
                int col = curr[1];
                for (int[] aro : around) {
                    int R = row + aro[0];
                    int C = col + aro[1];
                    if (map[R][C] == 2 && visited[R][C] == false) {
                        
                        // System.out.println(answer + " | " + R + " | " + C);
                        
                        if (R == itemX * 2 && C == itemY * 2) {
                            break w;
                        }
                        
                        queue.offer(new int[] {R, C});
                        visited[R][C] = true;
                    }
                }
            }
        }
        
        return answer / 2;
    }// [SOULTION]
    
}
