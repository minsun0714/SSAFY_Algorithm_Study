import java.util.*;

/*
정확성  테스트
테스트 1 〉	통과 (1.82ms, 81.4MB)
테스트 2 〉	통과 (1.74ms, 77.5MB)
테스트 3 〉	통과 (2.31ms, 85.6MB)
테스트 4 〉	통과 (1.08ms, 74.6MB)
테스트 5 〉	통과 (1.44ms, 90.4MB)
테스트 6 〉	통과 (3.00ms, 85.3MB)
테스트 7 〉	통과 (1.75ms, 79MB)
테스트 8 〉	통과 (2.11ms, 85.4MB)
테스트 9 〉	통과 (5.14ms, 91.9MB)
효율성  테스트
테스트 1 〉	통과 (24.77ms, 64.6MB)
테스트 2 〉	통과 (61.46ms, 69.3MB)
테스트 3 〉	통과 (61.36ms, 69.7MB)
테스트 4 〉	통과 (21.04ms, 65.3MB)
테스트 5 〉	통과 (63.07ms, 69.3MB)
테스트 6 〉	통과 (18.32ms, 64.8MB)
*/

// bfs
class 프로그래머스_석유시추_레벨2_조준희 {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    class Solution {
        public int solution(int[][] land) {
            int row_size = land.length;
            int col_size = land[0].length;

            //각 열의 석유 양
            int[] oil_per_col = new int[col_size];

            boolean[][] visited = new boolean[row_size][col_size];
            boolean[] visited_col= new boolean[col_size];

            for(int i = 0; i<row_size; i++){
                for(int j = 0; j<col_size; j++){
                    if(!visited[i][j] && land[i][j]==1){
                        //bfs
                        Deque<Node> bfs_q = new ArrayDeque<>();
                        bfs_q.offer(new Node(i, j));
                        int count = 0;
                        visited[i][j]=true;

                        while(!bfs_q.isEmpty()){
                            Node curr = bfs_q.poll();
                            visited_col[curr.c]=true;
                            count++;
                            for(int d = 0; d<4;  d++){
                                int new_r = curr.r+dr[d];
                                int new_c = curr.c+dc[d];

                                if(new_r<0 || new_c<0 || new_r>=row_size || new_c>=col_size)continue;
                                if(visited[new_r][new_c])continue;
                                if(land[new_r][new_c]!=1)continue;

                                visited[new_r][new_c]=true;
                                bfs_q.add(new Node(new_r, new_c));                                            }
                        }
                        //bfs 끝

                        //석유 분포한 열에 석유 양 더하기
                        for(int idx = 0; idx<col_size; idx++){
                            if(visited_col[idx]){
                                oil_per_col[idx]+=count;
                                visited_col[idx]=false;
                            }
                        }
                    }
                }
            }
            //가장 석유 양 많은 열 반환
            return  Arrays.stream(oil_per_col)
                    .max().orElse(0);
        }

        public class Node{
            int c;
            int r;
            public Node(int r, int c){
                this.r= r;
                this.c = c;
            }
        }

    }
}
