import java.io.*;
import java.util.*;

//bfs
public class 백준_15971_두사람_골드4_한재경_532ms {
    static class Node {
        int idx;
        int cost;
        int maxCost; //해당 노드까지 가장 긴 간선

        Node(int idx, int cost, int maxCost) {
            this.idx = idx;
            this.cost = cost;
            this.maxCost = maxCost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
//        int t = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //방개수
        int fir = Integer.parseInt(st.nextToken()) - 1; //로봇 위치
        int sec = Integer.parseInt(st.nextToken()) - 1;
        List<List<int[]>> links = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            links.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            links.get(a).add(new int[]{b, c});
            links.get(b).add(new int[]{a, c});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[fir] = 0;

        //간선 하나 차이나면 그 간선 빼고 계산: 경로에서 가장 긴 간선비용 제거
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(fir, 0, 0));
        int ansCost = 0; //결과 간선의 총 가중치
        int ansMaxCost = 0; //결과 간선에 포함된 최대가중치값

        //어차피 연결되는 경로 하나 뿐이므로 pq 필요없음
        while (!q.isEmpty()) {
            Node now = q.poll();
            int idx = now.idx;
            int cost = now.cost;
            int maxCost = now.maxCost;

            if (idx == sec) {
                ansCost = cost;
                ansMaxCost = maxCost;
                break;
            }

            // 연결 노드들 탐색
            for (int[] nxt : links.get(idx)) {
                int nxtIdx = nxt[0];
                int nxtCost = nxt[1];
                if (dist[nxtIdx] > cost + nxtCost) {
                    dist[nxtIdx] = cost + nxtCost;
                    q.add(new Node(nxtIdx, dist[nxtIdx],
                            Math.max(nxtCost, now.maxCost)));
                }
            }
        }
        System.out.println(ansCost - ansMaxCost);
    }
}
