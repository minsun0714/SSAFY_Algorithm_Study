package _9월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_1504_특정한최단경로_골드4_이민선_684ms {
    static class Node {
        int v;
        int c;

        public Node(int v, int c){
            this.v = v;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", c=" + c +
                    '}';
        }
    }
    static int n;
    static int e;
    static List<ArrayList<Node>> graph;
    static int v1;
    static int v2;
    static int[] dist;
    static Long answer1 = 0L;
    static Long answer2 = 0L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i=0;i<n + 1;i++){
            graph.add(new ArrayList<>());
        }

        for (int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];

        answer1 += dijkstra(1, v1);
        answer1 += dijkstra(v1, v2);
        answer1 += dijkstra(v2, n);

        answer2 += dijkstra(1, v2);
        answer2 += dijkstra(v2, v1);
        answer2 += dijkstra(v1, n);

        if (answer1 >= Integer.MAX_VALUE && answer2 >= Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(answer1, answer2));
        }


    }

    public static int dijkstra(int start, int end){

        for (int i=1;i<=n;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b)-> Integer.compare(a.c, b.c));

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()){
            Node curNode = pq.poll();

            if (curNode.c > dist[curNode.v]) {
                continue;  // 이미 처리된 노드는 무시
            }

            for (int i=0;i<graph.get(curNode.v).size();i++){
                Node nextNode = graph.get(curNode.v).get(i);

                if (dist[nextNode.v] > dist[curNode.v] + nextNode.c){
                    dist[nextNode.v] = dist[curNode.v] + nextNode.c;
                    pq.offer(new Node(nextNode.v, dist[nextNode.v]));
                }
            }
        }

        return dist[end];
    }
}
