package _4월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_11779_최소비용구하기2_골드3_이민선_416ms {
    static int n, m;
    static List<List<Node>> graph = new ArrayList<>();
    static class Node implements Comparable<Node>{
        int node;
        int cost;

        Node (int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Node anotherNode){
            return Integer.compare(this.cost, anotherNode.cost);
        }

        public String toString(){
            return "(" + node + "," + cost + ")";
        }
    }
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Node node = new Node(e, c);
            graph.get(s).add(node);
        }

        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        dijkstra(s, e);
    }

    private static void dijkstra(int s, int e){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(s, 0));

        dist[s] = 0;
        int[] memo = new int[n + 1];

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if (dist[cur.node] < cur.cost) continue;

            for (Node next:graph.get(cur.node)){
                if (dist[cur.node] + next.cost < dist[next.node]){
                    dist[next.node] = dist[cur.node] + next.cost;
                    memo[next.node] = cur.node;
                    pq.offer(next);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dist[e]).append("\n");
        List<Integer> trace = new ArrayList<>();
        int count = 1;
        while (e != s){
            count++;
            trace.add(e);
            e = memo[e];
        }
        trace.add(e);
        sb.append(count).append("\n");
        for (int i=trace.size() - 1;i >= 0;i--){
            sb.append(trace.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}
