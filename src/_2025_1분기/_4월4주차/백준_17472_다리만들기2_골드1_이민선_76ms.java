

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// dfs, mst
public class 백준_17472_다리만들기2_골드1_이민선_76ms {
    static int n, m;
    static int[][] board;
    static class Node implements Comparable<Node>{
        int self;
        int destination;
        int distance;

        Node (int self, int destination, int distance){
            this.self = self;
            this.destination = destination;
            this.distance = distance;
        }

        public String toString(){
            return "( " + self + " , " + destination + " , " + distance + " )";
        }

        public int compareTo(Node o) {
            return Integer.compare(distance, o.distance);
        }
    }
    static List<Node> graph = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 1;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (board[i][j] == 1) dfs(i, j, ++count);
            }
        }

        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (board[i][j] > 1) {
                    for (int dir=0;dir<4;dir++) {
                        getLinearDistance(i, j, dir, board[i][j], 0);
                    }
                }
            }
        }

        parent = new int[count];

        // mst
        Collections.sort(graph);

        parent = new int[count + 1];
        for (int i=1;i<=count;i++){
            parent[i] = i;
        }
        int mst = 0;
        for (Node node: graph){
            if (find(node.self) != find(node.destination)){
                union(node.self, node.destination);
                mst += node.distance;
            }
        }

        for (int i=3;i<=count;i++){
            if (find(i) != find(2)){
                System.out.println(-1);
                System.exit(0);
            }
        }
        System.out.println(mst);
    }

    private static int find(int node){
        if (node != parent[node]){
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    private static void dfs(int x, int y, int count){
        board[x][y] = count;
        for (int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            if (board[nx][ny] != 1) continue;

            dfs(nx, ny, count);
        }
    }

    private static int getLinearDistance(int x, int y, int dir, int self, int distance){
        int nx = x;
        int ny = y;
        while (true){
            nx += dx[dir];
            ny += dy[dir];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) return 0;
            if (board[nx][ny] == self) return 0;

            distance++;

            if (board[nx][ny] > 1){
                if (distance > 2) {
                    for (Node node: graph){
                        if (node.self == self && node.destination == board[nx][ny] && node.distance <= distance - 1)
                            return 0;
                    }
                    graph.add(new Node(self, board[nx][ny], distance - 1));
                }
                return distance;
            }

        }
    }
}
