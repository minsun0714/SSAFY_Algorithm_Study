import java.io.*;
import java.util.*;

class Solution {
    
    // STATIC
    static int[][] dir = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    static int width;
    static int height;
    int[][] underworld;
    boolean[][] visited;
    int[] gain;
    
    // [CHECKER]
    private static boolean checker(int row, int col) {
        return 0 <= row && row < height && 0 <= col && col < width;
    }
    
    // [BFS]
    private void bfs(int row, int col) {
        int amount = 0;
        Set<Integer> colSet = new HashSet<>();
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[] {row, col});
        visited[row][col] = true;
        amount++;
        colSet.add(col);
        
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int rowCurr  = curr[0];
            int colCurr = curr[1];
            
            for (int[] d : dir) {
                int rowNext = rowCurr + d[0];
                int colNext = colCurr + d[1];
                
                if (checker(rowNext, colNext) && !visited[rowNext][colNext]) {
                    queue.offer(new int[] {rowNext, colNext});
                    visited[rowNext][colNext] = true;
                    amount++;
                    colSet.add(colNext);
                }
            }
        }
        
        for (int c : colSet) {
            gain[c] += amount;
        }
    }
    
    // [SOLUTION]
    public int solution(int[][] land) {
        int answer = 0;
        
        width = land[0].length;
        height = land.length;
        underworld = new int[height][width];
        visited = new boolean[height][width];
        gain = new int[width];
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                underworld[row][col] = land[row][col];
                if (underworld[row][col] == 0) {
                    visited[row][col] = true;
                }
            }
        }
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (!visited[row][col]) {
                    bfs(row, col);
                }
            }
        }
        
        for (int col = 0; col < width; col++) {
            answer = Math.max(answer, gain[col]);
        }
        
        return answer;
    }
    
}
