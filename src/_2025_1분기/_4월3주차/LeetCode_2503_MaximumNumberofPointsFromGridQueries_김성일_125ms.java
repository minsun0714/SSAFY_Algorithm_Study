// DP
class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int[] results = new int[queries.length];
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>(); // Set<hash>
        int r = grid.length;
        int c = grid[0].length;
        int[][] delta4 = { {0,1},{0,-1}, {1,0},{-1,0} };
        Map<Integer, Integer> dp = new HashMap<>();
        TreeSet<Integer> sortedQueries = new TreeSet<>();
        for(int query : queries){
            sortedQueries.add(query);
        }
        PriorityQueue<int[]> tempQ = new PriorityQueue<>(Comparator.comparingInt( (int[] o) -> (o[2]) ));
        boolean firstToken = false;
        for(int query : sortedQueries){
            while(tempQ.isEmpty()==false &&tempQ.peek()[2]<query){
                int[] tempNow = tempQ.poll();
                q.offer(tempNow);
                visited.add(tempNow[0]*c+tempNow[1]);
            }
            if(grid[0][0]<query && !firstToken){
                q.offer(new int[] {0,0,grid[0][0]});
                visited.add(0);
                firstToken = true;
            }
            while(q.isEmpty()==false){
                int[] now = q.poll();
                int row = now[0];
                int col = now[1];
                for(int[] delta : delta4){
                    int nrow = row + delta[0];
                    int ncol = col + delta[1];
                    if( (0<=nrow && nrow<r) && (0<=ncol && ncol<c) ){
                    } else{
                        continue;
                    }
                    if(visited.contains(nrow*c+ncol)){
                        continue;
                    }
                    if(grid[nrow][ncol]>=query){
                        tempQ.offer(new int[] {nrow, ncol, grid[nrow][ncol]});
                        continue;
                    }
                    q.offer(new int[] {nrow, ncol, grid[nrow][ncol]});
                    visited.add(nrow*c+ncol);
                }
            }
            dp.put(query, visited.size());
        }
        for(int i=0; i<queries.length; i++){
            int pushValue = dp.get(queries[i]);
            results[i] = pushValue;
        }
        return results;
    }
}
