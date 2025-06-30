import java.util.*;

//그래프 && BFS
class 프로그래머스_도넛과막대그래프_레벨2_한재경 {
    static List<List<Integer>> es; //간선리스트: 양방향(막대그래프 처리 위해)
    static boolean[] visited;
    static int n;
    static int[] out;
    
    //해당 지점에서 시작하는 덩어리 노드, 간선개수
    public int[] getCnt(int node) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(node);
        visited[node] = true;
        int nCnt = 1;
        int eCnt = 0;
        
        while (!q.isEmpty()) {
            int now = q.poll();
            eCnt += out[now]; //해당노드 진출차수만큼 카운팅
            for (int nxt : es.get(now)) { //연결 노드. 무조건 각 노드당 1번
                if (!visited[nxt]) {
                    nCnt++;
                    q.add(nxt);
                    visited[nxt] = true;
                }
            }
        }
        return new int[]{nCnt, eCnt};
    }
    
    public int[] solution(int[][] edges) {
        int[] answer = {};
        //총 노드 개수 구하기
        n = 0;
        for (int[] edge : edges) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }
        
        es = new ArrayList<>(); //간선리스트
        for (int i = 0; i < n; i++) { //초기화
            es.add(new ArrayList<>());
        }
        
        //시작점 구하기 및 간선 추가
        int[] in = new int[n]; //각 노드 진입차수
        out = new int[n]; //각 노드 진출차수
        for (int[] edge : edges) {
            in[edge[1] - 1]++;
            out[edge[0] - 1]++;
            
            //막대그래프 처리를 위한 양방향 처리!
            es.get(edge[0] - 1).add(edge[1] - 1);
            es.get(edge[1] - 1).add(edge[0] - 1);
        }
        
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (in[i] == 0 && out[i] > 1) start = i;
        }
        
        //시작점 제외 각 노드에서 시작하는 bfs해서 노드개수, 간선개수 구하기
        visited = new boolean[n];
        visited[start] = true;
        
        // 각 노드별 노드, 간선 수
        List<int[]> nes = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            // 간선 정보 없는 노드는 버리기!
            if (!visited[i] && (in[i] > 0 || out[i] > 0)) {
                nes.add(getCnt(i));
            }
        }
        
        // 각 그래프 분류
        int donut = 0, bar = 0, eight = 0;
        for (int[] ne : nes) {
            int node = ne[0];
            int edge = ne[1];
            int diff = edge - node;
            if (diff == 0) donut++;
            else if (diff == -1) bar++;
            else if (diff ==  1) eight++;
        }
        
        return new int[]{start+1, donut, bar, eight};
    }
}
