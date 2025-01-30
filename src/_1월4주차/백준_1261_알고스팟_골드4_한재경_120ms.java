import java.io.*;
import java.util.*;

// 다익스트라
public class Main {
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};
    static int[][] grid;
    static int[][] dist;
    static int n;
    static int m;
    
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static void dijkstra(int sx, int sy) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(sx, sy, 0));
        while (!q.isEmpty()) {
            Node current = q.poll();
            int x = current.x;
            int y = current.y;
            int cost = current.cost;
            
            if (x == n - 1 && y == m - 1) {
                System.out.println(cost);
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m
                    && dist[nx][ny] > cost + grid[nx][ny]) {
                    int ncost = cost + grid[nx][ny];
                    q.offer(new Node(nx, ny, ncost));
                    dist[nx][ny] = ncost;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }
        dist = new int[n][m]; //최소가중치
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        dijkstra(0, 0);
    }
}
