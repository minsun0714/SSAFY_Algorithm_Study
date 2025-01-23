import java.util.*;

//bfs + 브루트포스
//트리면 무조건 하나 끊기면 나머지 반띵됨
class 프로그래머스_전력망을둘로나누기_레벨2_한재경 {
    static int minDiff = Integer.MAX_VALUE;
    public void bfs(int n, int[][] wires) {
        Queue<Integer> q = new ArrayDeque<>(); //현재 송전탑 인덱스
        q.add(wires[0][0]);
        int[] visited = new int[n + 1]; //방문한 송전탑 (1부터 시작)
        visited[0] = 0;
        
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int[] wire : wires) {
                if (wire[0] == now && visited[wire[1]] == 0) {
                    q.add(wire[1]);
                    visited[wire[1]] = 1;
                } else if (wire[1] == now && visited[wire[0]] == 0) {
                    q.add(wire[0]);
                    visited[wire[0]] = 1;
                }
            }
        }
        int acnt = 0;
        int bcnt = -1; //0은 한 개 더 많음
        for (int v : visited) {
            if (v == 1) {
                acnt++;
            } else {
                bcnt++;
            }
        }
        minDiff = Math.min(minDiff, Math.abs(acnt - bcnt)); //두 송전탑 개수 차
    }
    public int solution(int n, int[][] wires) { //n:송전탑개수, wires: [a,b] 송전탑 전선으로 연결됨
        //전선 하나 끊어서 송전탑 개수가 최대한 비슷한 두 덩이로 분할
        //각 전선 끊는 경우
        for (int i = 0; i < wires.length; i++) { //끊을 전선 인덱스
            int[][] nw = new int[wires.length - 1][2]; //새로운 전선들
            for (int j = 0; j < wires.length; j++) { //해당 전선 제외하고 새로운 전선에 넣기
                if (j == i) {
                    continue;
                }
                else if (j > i) {
                    nw[j - 1] = wires[j];
                } else {
                    nw[j] = wires[j];
                }
            }
            bfs(n, nw);
        }
        
        return minDiff;
    }
}
