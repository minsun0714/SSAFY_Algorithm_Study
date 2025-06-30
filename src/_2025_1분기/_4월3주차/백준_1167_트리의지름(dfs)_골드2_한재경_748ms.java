import java.util.*;
import java.io.*;

class Node {
    int to; //도착 노드
    int cost; //비용

    Node (int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

// dfs
public class 백준_1167_트리의지름(dfs)_골드2_한재경_748ms {
    static int v;
    static List<Node>[] nodes;

    //start로부터 가장 먼 노드, 거리 반환
    public static int[] getFar(int start) {
        Stack<Integer> stack = new Stack<>(); //스택

        //start부터 시작하는 dist들 메모
        int[] dist = new int[v + 1];
        stack.add(start);

        while (!stack.isEmpty()) {
            int from = stack.pop();

            for (Node node : nodes[from]) { //연결 노드들
                if (dist[node.to] == 0 && node.to != start) { //시작점 아니고 미방문이면
                    stack.add(node.to);
                    dist[node.to] = dist[from] + node.cost; //from까지 거리 + cost
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
