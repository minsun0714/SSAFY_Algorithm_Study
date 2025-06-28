import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_15971_두로봇_골드4_이민선_464ms {
    static int n;
    static int aCur, bCur;
    static class Node {
        int num;
        int cost;

        Node (int num, int cost){
            this.num = num;
            this.cost = cost;
        }

        public String toString (){
            return "(" + num + "," + cost + ")";
        }
    }
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        aCur = Integer.parseInt(st.nextToken());
        bCur = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i=0;i<=n;i++) graph.add(new ArrayList<>());


        for (int i=0;i<n - 1;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        bfs();
    }

    private static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        int[] visited = new int[n + 1];
        int[] memoMax = new int[n + 1];

        q.offer(new Node(aCur, 0));

        while (!q.isEmpty()){
            Node cur = q.poll();
            if (cur.num == bCur) break;

            for (Node next:graph.get(cur.num)){
                if (visited[next.num] == 0) {
                    visited[next.num] = visited[cur.num] + next.cost;
                    memoMax[next.num] = Math.max(memoMax[cur.num], next.cost);
                    if (cur.num != bCur) q.offer(next);
                }
            }
        }

        System.out.println(visited[bCur] - memoMax[bCur]);
    }
}
