import java.io.*;
import java.util.*;

//벨만포드, 264ms, 최솟값 갱신 시 음수 int 범위 초과할 수 있으므로 dist[] long!
class 백준_11657_타임머신_실버4_한재경_264ms {
    int start;
    int end;
    int cost;
    public Edge() {}

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //노드수
        int m = Integer.parseInt(st.nextToken()); //간선수
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }

        //아래 최솟값 갱신 시 음수 int 범위 초과할 수 있으므로 long!
        long[] dist = new long[n]; //최소비용 갱신
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i < n - 1; i++) { //최악의 경우 모든 노드 방문
            for (Edge e : edges) {
                if (dist[e.start] != Integer.MAX_VALUE
                        && dist[e.start] + e.cost < dist[e.end]) {
                    dist[e.end] = dist[e.start] + e.cost;
                }
            }
        }
        boolean isPossible = true;

        for (Edge e : edges) {
            if (dist[e.start] != Integer.MAX_VALUE
                    && dist[e.start] + e.cost < dist[e.end]) {
                //갱신되는 값 -> 사이클
                isPossible = false;
                break;
            }
        }
        if (isPossible) {
            for (int i = 1; i < n; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                } else{
                    System.out.println(dist[i]);
                }
            }
        } else {
            System.out.println(-1);
        }
    }
}
