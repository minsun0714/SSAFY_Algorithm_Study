import java.util.*;

class 프로그래머스_게임맵최단거리_레벨3_조준희 {
    public int solution(int[][] maps) {
        int[] dc = {0, -1, 0, 1};
        int[] dr = {1, 0, -1, 0};
        
        int answer = -1;
        
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 1));
        
        while(!q.isEmpty()){
            Point curr = q.poll();
            if(curr.r==maps.length-1 && curr.c == maps[0].length-1){
                answer = curr.d;
                break;
            }
            for(int i = 0; i<4; i++){
                int newR = curr.r+dr[i];
                int newC = curr.c+dc[i];
                try{
                    if(!visited[newR][newC] && maps[newR][newC]==1){
                        visited[newR][newC]=true;
                        q.add(new Point(newR, newC, curr.d+1));
                    }
                }catch(Exception e){}
            }
        }
        return answer;
    }
    
    static class Point{
        int r;
        int c;
        int d;
        public Point(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
            
        }
    }
}