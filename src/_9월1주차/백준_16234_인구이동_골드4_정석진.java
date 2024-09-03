import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_16234_인구이동_골드4_정석진 {
    public static class Node {
        int r;
        int c;
        int popul;

        public Node(int r, int c, int popul) {
            this.r = r;
            this.c = c;
            this.popul = popul;
        }
    }

    static int N, L, R;
    static Node[][] map;
    static boolean[][] visited;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static boolean isBound(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static LinkedList<Node> bfs(int r, int c) {
        Queue<Node> queue = new LinkedList<>();
        LinkedList<Node> union = new LinkedList<>();
        queue.add(new Node(r, c, map[r][c].popul));
        union.add(map[r][c]);
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newR = current.r + dir[i][0];
                int newC = current.c + dir[i][1];

                if (isBound(newR, newC) && !visited[newR][newC]) {
                    int diff = Math.abs(current.popul - map[newR][newC].popul);
                    if (L <= diff && diff <= R) {
                        queue.add(new Node(newR, newC, map[newR][newC].popul));
                        union.add(map[newR][newC]);
                        visited[newR][newC] = true;
                    }
                }
            }
        }

        return union;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new Node[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = new Node(r, c, Integer.parseInt(st.nextToken()));
            }
        }

        int totalDays = 0;

        while (true) {
            visited = new boolean[N][N];
            boolean isMoved = false;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c]) {
                        LinkedList<Node> union = bfs(r, c);
                        if (union.size() > 1) {
                            isMoved = true;
                            int sum = 0;
                            for (Node node : union) {
                                sum += node.popul;
                            }
                            int newPopulation = sum / union.size();
                            for (Node node : union) {
                                map[node.r][node.c].popul = newPopulation;
                            }
                        }
                    }
                }
            }

            if (!isMoved) {
                break;
            }
            totalDays++;
        }

        System.out.println(totalDays);
    }
}
