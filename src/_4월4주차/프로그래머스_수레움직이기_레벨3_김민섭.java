import java.io.*;
import java.util.*;

class 프로그래머스_수레움직이기_레벨3_김민섭 {
    
    // STATIC
    static int[][] dir = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    static final int BIGINT = 1_000_000_000;
    
    static int height;
    static int width;
    static int[][] map;
    
    static int[] redGoal;
    static int[] blueGoal;
    static boolean[][] visitedRed;
    static boolean[][] visitedBlue;
    static boolean isRedGoal;
    static boolean isBlueGoal;
    
    static int minimum;
    static int time;

    // [CHECKER]
    private static boolean checker(int[] coor) {
        boolean result = 0 <= coor[0] && coor[0] < height && 0 <= coor[1] && coor[1] < width && map[coor[0]][coor[1]] != 5;
        return result;
    } // [CHECKER]
    
    // [DFS]
    private static void dfs(int[] red, int[] blue) {
        if (isRedGoal && isBlueGoal) {
            minimum = (int) Math.min(minimum, time);
            return;
        } else if (!isRedGoal && !isBlueGoal) {
            int[] redNext = new int[2];
            int[] blueNext = new int[2];
            
            for (int[] dr : dir) {
                redNext[0] = red[0] + dr[0];
                redNext[1] = red[1] + dr[1];
                
                if (checker(redNext) && !visitedRed[redNext[0]][redNext[1]]) {
                    for (int[] db : dir) {
                        blueNext[0] = blue[0] + db[0];
                        blueNext[1] = blue[1] + db[1];
                        
                        if (checker(blueNext) && !visitedBlue[blueNext[0]][blueNext[1]]) {
                            if ( !(redNext[0] == blueNext[0] && redNext[1] == blueNext[1]) && (redNext[0] != blue[0] || redNext[1] != blue[1] || blueNext[0] != red[0] || blueNext[1] != red[1]) )  {
                                if (redNext[0] == redGoal[0] && redNext[1] == redGoal[1]) {
                                    isRedGoal = true;
                                }
                                if (blueNext[0] == blueGoal[0] && blueNext[1] == blueGoal[1]) {
                                    isBlueGoal = true;
                                }
                                visitedRed[redNext[0]][redNext[1]] = true;
                                visitedBlue[blueNext[0]][blueNext[1]] = true;
                                time++;

                                dfs(redNext, blueNext);

                                isRedGoal = false;
                                isBlueGoal = false;
                                visitedRed[redNext[0]][redNext[1]] = false;
                                visitedBlue[blueNext[0]][blueNext[1]] = false;
                                time--;
                            }
                        }
                    }
                    
                }
            }
        } else if (!isRedGoal && isBlueGoal) {
            int[] redNext = new int[2];
            
            for (int[] d : dir) {
                redNext[0] = red[0] + d[0];
                redNext[1] = red[1] + d[1];
                
                if (checker(redNext) && !visitedRed[redNext[0]][redNext[1]] && (redNext[0] != blue[0] || redNext[1] != blue[1])) {
                    if (redNext[0] == redGoal[0] && redNext[1] == redGoal[1]) {
                        isRedGoal = true;
                    }
                    visitedRed[redNext[0]][redNext[1]] = true;
                    time++;
                    
                    dfs(redNext, blue);
                    
                    isRedGoal = false;
                    visitedRed[redNext[0]][redNext[1]] = false;
                    time--;
                }
            }
        } else if (isRedGoal && !isBlueGoal) {
            int[] blueNext = new int[2];
            
            for (int[] d : dir) {
                blueNext[0] = blue[0] + d[0];
                blueNext[1] = blue[1] + d[1];
                
                if (checker(blueNext) && !visitedBlue[blueNext[0]][blueNext[1]] && (blueNext[0] != red[0] || blueNext[1] != red[1])) {
                    if (blueNext[0] == blueGoal[0] && blueNext[1] == blueGoal[1]) {
                        isBlueGoal = true;
                    }
                    visitedBlue[blueNext[0]][blueNext[1]] = true;
                    time++;
                    
                    dfs(red, blueNext);
                    
                    isBlueGoal = false;
                    visitedBlue[blueNext[0]][blueNext[1]] = false;
                    time--;
                }
            }
        }
    } // [DFS]
    
    // [SOLUTION]
    public int solution(int[][] maze) {
        height = maze.length;
        width = maze[0].length;
        map = new int[height][width];
        
        visitedRed = new boolean[height][width];
        visitedBlue = new boolean[height][width];
        redGoal = new int[2];
        blueGoal = new int[2];
        isRedGoal = false;
        isBlueGoal = false;
        
        minimum = BIGINT;
        time = 0;
        
        int[] red = new int[2];
        int[] blue = new int[2];
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                map[row][col] = maze[row][col];
                if (map[row][col] == 1) {
                    red[0] = row;
                    red[1] = col;
                    visitedRed[row][col] = true;
                }
                if (map[row][col] == 2) {
                    blue[0] = row;
                    blue[1] = col;
                    visitedBlue[row][col] = true;
                }
                if (map[row][col] == 3) {
                    redGoal[0] = row;
                    redGoal[1] = col;
                }
                if (map[row][col] == 4) {
                    blueGoal[0] = row;
                    blueGoal[1] = col;
                }
            }
        }
        
        dfs(red, blue);
        
        return minimum == BIGINT ? 0 : minimum;
    } // [SOLUTION]
    
}