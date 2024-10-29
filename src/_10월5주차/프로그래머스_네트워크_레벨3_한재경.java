//bfs
import java.util.*;
class 프로그래머스_네트워크_레벨3_한재경 {
    public static int[] visited;
    public void bfs(int x, int n, int[][] computers) { //현재노드
        Queue<Integer> q = new ArrayDeque<>(); //방문할 노드
        q.add(x);
        
        visited[x] = 1;
        
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i < n; i++) { //i번 노드와의 연결 여부 확인
                if (computers[now][i] == 1 && visited[i] == 0) { //연결되고 i번 노드 미방문
                    visited[i] = 1;
                    q.add(i);
                }
            }
        }
    }
    public int solution(int n, int[][] computers) { //개수, i번 j번 노드 연결 1/0
        int cnt = 0;
        visited = new int[n]; //i번 노드 방문여부
        
        //덩이 개수 bfs
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) { //미방문이면
                bfs(i, n, computers);
                cnt++;
            }
        }
        
        return cnt;
    }
}
