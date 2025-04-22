import java.util.*;
import java.io.*;

// bfs (입력받을 때 while (st.countTokens() > 1)로 하면 시간초과 남)
class 백준_1167_트리의지름_한재경_765ms {
    int to; //도착 노드
    int cost; //비용

    Node (int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static int v;
    static List<Node>[] nodes;

    //start로부터 가장 먼 노드, 거리 반환
    public static int[] getFar(int start) {
        Queue<Integer> q = new ArrayDeque<>();

        //start부터 시작하는 dist들 메모
        int[] dist = new int[v + 1];
        boolean[] visited = new boolean[v + 1];
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int from = q.poll();

            for (Node node : nodes[from]) { //연결 노드들
                if (!visited[node.to]) { //미방문이면
                    q.add(node.to);
                    dist[node.to] = dist[from] + node.cost; //from까지 거리 + cost
                    visited[node.to] = true;
                }
            }
        }
        
        //가장 먼 노드, 거리 구하기
        int max = 0;
        int idx = 0;

        for (int i = 0; i <= v; i++) {
            if (dist[i] > max) {
                max = dist[i];
                idx = i;
            }
        }

        return new int[]{idx, max};
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine()); //노드 개수
        nodes = new ArrayList[v + 1]; //각 노드를 from으로 하는 연결 리스트

        for (int i = 1; i <= v; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                nodes[from].add(new Node(to, cost));
            }
        }


        //노드 1로부터 가장 먼 노드, 거리 구하기
        int[] first = getFar(1);

        //위에서 구한 노드부터 가장 먼 노드, 거리 구하기
        int[] second = getFar(first[0]);

        System.out.println(second[1]);
    }
}
