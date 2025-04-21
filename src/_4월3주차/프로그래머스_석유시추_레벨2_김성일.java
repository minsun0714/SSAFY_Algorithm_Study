// BFS + IMOS
/*
정확성  테스트
테스트 1 〉	통과 (0.38ms, 76MB)
테스트 2 〉	통과 (0.58ms, 72MB)
테스트 3 〉	통과 (0.24ms, 92.6MB)
테스트 4 〉	통과 (0.30ms, 77.2MB)
테스트 5 〉	통과 (0.25ms, 75.7MB)
테스트 6 〉	통과 (1.42ms, 90MB)
테스트 7 〉	통과 (1.76ms, 88MB)
테스트 8 〉	통과 (0.82ms, 82.7MB)
테스트 9 〉	통과 (5.46ms, 84.6MB)
효율성  테스트
테스트 1 〉	통과 (46.09ms, 76.4MB)
테스트 2 〉	통과 (74.63ms, 76.5MB)
테스트 3 〉	통과 (101.08ms, 76.9MB)
테스트 4 〉	통과 (66.44ms, 74.7MB)
테스트 5 〉	통과 (133.00ms, 89.2MB)
테스트 6 〉	통과 (63.14ms, 90.9MB)
*/

import java.util.*;
class Solution {
    static int rowSize;
    static int colSize;
    static int[] getOil;
    static int[][] delta4 = { {0,1},{0,-1}, {1,0},{-1,0} };
    public int solution(int[][] land) {
        rowSize = land.length;
        colSize = land[0].length;
        getOil = new int[colSize];
        for (int r=0; r<rowSize; r++){
            for (int c=0; c<colSize; c++){
                int cell = land[r][c];
                if(cell==1){
                    BFS(Arrays.asList(r,c), land);
                }
            }
        }
        int imosSum = 0;
        int maxValue = 0;
        for (int value : getOil){
            imosSum += value;
            maxValue = Math.max(maxValue, imosSum);
        }
        return maxValue;
    }
    static public void BFS(List<Integer> cell, int[][] land) {
        int size = 0;
        int minRange = Integer.MAX_VALUE;
        int maxRange = Integer.MIN_VALUE;
        Queue<List<Integer>> q = new LinkedList<>();
        q.offer(cell);
        land[cell.get(0)][cell.get(1)] = 0;
        while(q.isEmpty()==false){
            List<Integer> now = q.poll();
            size++;
            int row = now.get(0);
            int col = now.get(1);
            minRange = Math.min(minRange, col);
            maxRange = Math.max(maxRange, col);
            for(int[] delta : delta4){
                int nrow = row + delta[0];
                int ncol = col + delta[1];
                if((0<=nrow && nrow<rowSize) && (0<=ncol && ncol<colSize) ){
                } else{
                    continue;
                }
                List<Integer> next = new ArrayList<>();
                next.add(nrow);
                next.add(ncol);
                if(land[nrow][ncol]==1 ){
                } else { 
                    continue;
                }
                q.offer(next);
                land[nrow][ncol] = 0;
            }
        }
        getOil[minRange] += size;
        if(maxRange+1>=colSize){
        } else{
            getOil[maxRange+1] -= size;
        }
    }

}
