package _3월1주차;

import java.io.*;
import java.util.*;

public class 백준_11779_최소비용구하기2_골드3_이민선_428ms {
    static int n, m;
    static class Node implements Comparable<Node> {
        int v;
        int c;

        Node(int v, int c){
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node anotherNode){
            return Integer.compare(this.c, anotherNode.c);
        }

        @Override
        public String toString(){
            return "(" + v + "," + c + ")";
        }
    }
    static List<List<Node>> edges = new ArrayList<>();
    static int s, e;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i=0;i<=n;i++){
            edges.add(new ArrayList<>());
        }

        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.get(a).add(new Node(b, c));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        dijkstra();
    }

    public static void dijkstra(){
        StringBuilder sb = new StringBuilder();
        long[] dist = new long[n + 1];
        int[] trace = new int[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[s] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            // 시간초과 줄이기.
            if (dist[cur.v] < cur.c) continue;

            for (int i=0;i<edges.get(cur.v).size();i++){
                Node next = edges.get(cur.v).get(i);

                if (dist[cur.v] + next.c < dist[next.v]){
                    dist[next.v] = dist[cur.v] + next.c;
                    trace[next.v] = cur.v;
                    pq.offer(next);
                }
            }
        }
        System.out.println(dist[e]);
        int count = 0;
        List<Integer> path = new ArrayList<>();
        while (e != s){
            path.add(e);
            e = trace[e];
            count++;
        }
        path.add(s);
        count++;
        System.out.println(count);
        Collections.reverse(path);
        for (int i=0;i<path.size();i++){
            sb.append(path.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}
