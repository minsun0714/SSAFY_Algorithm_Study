package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_10282_해킹_골드4_이민선_860ms {
    static class Node {
        int a;
        int s;
        Node(int a, int s) {
            this.a = a;
            this.s = s;
        }

        public String toString (){
            return "(" + a + "," + s + ")";
        }
    }
    static int n;
    static int c;
    static List<ArrayList<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for (int t=0;t<tc;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i=0;i<n + 1;i++){
                graph.add(new ArrayList<>());
            }
            for (int i=0;i<d;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Node(a, s));
            }
            dijkstra();
        }
    }

    public static void dijkstra(){
        int[] dist = new int[n + 1];
        for (int i=0;i<=n;i++){
            dist[i] = 100_000_000;
        }
        dist[c] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> a.s - b.s);

        pq.add(new Node(c, 0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            for (int i=0;i<graph.get(cur.a).size();i++){
                Node next = graph.get(cur.a).get(i);
                if (dist[cur.a] + next.s < dist[next.a]){
                    dist[next.a] = dist[cur.a] + next.s;
                    pq.add(next);
                }
            }
        }

        int count = 0;
        int max = 0;
        for (int i=0;i<=n;i++){
            if (dist[i] < 100_000_000){
                count++;
                max = Math.max(max, dist[i]);
            }
        }
        System.out.println(count + " " + max);
    }
}
