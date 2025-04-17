import java.io.*;
import java.util.*;

class 릿코드_2503_MaximumNumberofPointsFromGridQueries_김민섭 {

    // STATIC
    static int[][] dir = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    static int height;
    static int width;

    // CHECKER
    private static boolean checker(int row, int col) {
        return 0 <= row && row < height && 0 <= col && col < width;
    }

    // [MAX POINTS]
    public int[] maxPoints(int[][] grid, int[] queries) {
        height = grid.length;
        width = grid[0].length;
        
        int qSize = queries.length;
        int[][] qArray = new int[qSize][2];
        for (int q = 0; q < qSize; q++) {
            qArray[q][0] = queries[q];
            qArray[q][1] = q;
        }

        Arrays.sort(qArray, (a, b) -> {return a[0] - b[0];} );

        int[] answer = new int[qSize];

        int point;
        boolean[][] visited = new boolean[height][width];

        Queue<int[]> queue = new ArrayDeque<>();
        PriorityQueue<int[]> queueForNext = new PriorityQueue<>((a, b) -> { return grid[a[0]][a[1]] - grid[b[0]][b[1]];} );
        
        point = 0;
        
        if (grid[0][0] < qArray[0][0]) {
        	queue.offer(new int[] {0, 0});
        	visited[0][0] = true;
        	point++;
        } else {
        	queueForNext.offer(new int[] {0, 0});
        }
        
        for (int q = 0; q < qSize; q++) {
        	int qNum = qArray[q][0];
        	int qIndex = qArray[q][1];
        	
        	Queue<int[]> tempQueue = new ArrayDeque<>();
        	
        	int qfnSize = queueForNext.size();
        	for (int qfn = 0; qfn < qfnSize; qfn++) {
				int[] bef = queueForNext.poll();
				int rowBef = bef[0];
				int colBef = bef[1];
				
				if (!visited[rowBef][colBef] && grid[rowBef][colBef] < qNum) {
                    queue.offer(new int[] {rowBef, colBef});
                    visited[rowBef][colBef] = true;
                    point++;
                } else if (!visited[rowBef][colBef]) {
                	tempQueue.offer(bef);
                	break;
                }
			}
        	
        	while(!tempQueue.isEmpty()) {
        		queueForNext.offer(tempQueue.poll());
        	}
        	
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int rowCurr = curr[0];
                int colCurr = curr[1];
                
                for (int[] d : dir) {
                    int rowNext = rowCurr + d[0];
                    int colNext = colCurr + d[1];

                    if (checker(rowNext, colNext) && !visited[rowNext][colNext] && grid[rowNext][colNext] < qNum) {
                        queue.offer(new int[] {rowNext, colNext});
                        visited[rowNext][colNext] = true;
                        point++;
                    } else if (checker(rowNext, colNext) && !visited[rowNext][colNext]) {
                    	queueForNext.offer(new int[] {rowNext, colNext});
                    }
                }
            }
            
            answer[qIndex] = point;
        }
        
        return answer;
    }
}