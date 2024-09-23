import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1647_도시분할계획_골드4_정석진_1932ms {
    public static class Element {
        int start;
        int end;
        int cost;

        public Element(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static List<int[]>[] adjList;  // 인접 리스트: [노드 번호][{연결 노드, 가중치}]
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];  // 인덱스 기반 인접 리스트
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 간선 정보 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[start].add(new int[]{end, cost});
            adjList[end].add(new int[]{start, cost});
        }

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Element> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        // 임의의 시작 노드를 1로 설정
        visited[1] = true;
        for (int[] edge : adjList[1]) {
            pq.add(new Element(1, edge[0], edge[1]));
        }

        int totalCost = 0;
        int maxCost = 0;

        while (cnt != N - 1) {
            Element e = pq.poll();
            if (visited[e.end]) continue;

            visited[e.end] = true;
            totalCost += e.cost;
            maxCost = Math.max(maxCost, e.cost);
            cnt++;

            // 해당 노드의 인접 노드들을 탐색
            for (int[] edge : adjList[e.end]) {
                if (!visited[edge[0]]) {
                    pq.add(new Element(e.end, edge[0], edge[1]));
                }
            }
        }

        totalCost -= maxCost;  // 가장 큰 비용의 간선을 제외하여 두 도시로 분리
        System.out.println(totalCost);
    }
}

