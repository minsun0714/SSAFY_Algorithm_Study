import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_1504_특정한최단경로_골드4_정석진_600ms {
    public static int N;

    public static class Node {
        int name;
        List<Edge> edges = new ArrayList<>();

        public Node(int name) {
            this.name = name;
        }
    }

    public static class Edge {
        Node target;
        int weight;

        public Edge(Node target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            nodes[start].edges.add(new Edge(nodes[end], value));
            nodes[end].edges.add(new Edge(nodes[start], value));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 각 경로에서 다익스트라를 세 번 호출
        long route1 = 0;
        route1 += dijkstra(nodes, 1, v1); // 1 -> v1
        route1 += dijkstra(nodes, v1, v2); // v1 -> v2
        route1 += dijkstra(nodes, v2, N); // v2 -> N

        long route2 = 0;
        route2 += dijkstra(nodes, 1, v2); // 1 -> v2
        route2 += dijkstra(nodes, v2, v1); // v2 -> v1
        route2 += dijkstra(nodes, v1, N); // v1 -> N

        long result = Math.min(route1, route2);

        // 경로가 존재하지 않는 경우 처리
        if (route1 >= Integer.MAX_VALUE && route2 >= Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    public static int dijkstra(Node[] nodes, int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N + 1];
        dist[start] = 0;
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0];
            int curDist = current[1];

            if (visited[curNode]) continue;
            visited[curNode] = true;

            if (curNode == end) {
                return curDist;
            }

            for (Edge edge : nodes[curNode].edges) {
                if (!visited[edge.target.name] && curDist + edge.weight < dist[edge.target.name]) {
                    dist[edge.target.name] = curDist + edge.weight;
                    pq.add(new int[]{edge.target.name, dist[edge.target.name]});
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}