import java.io.*;
import java.util.*;

// bfs, 우선순위 큐, 조합
public class 백준_17142_연구소3_골드3_한재경_328ms {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int n, m;
    //grid: 0빈칸 1벽 2바이러스 / newGrid: -1빈칸 -2벽 -3바이러스
    static int[][] grid, newGrid;
    static List<Node> vi;
    static int minTime = Integer.MAX_VALUE;

    public static class Node implements Comparable<Node> {
        int x, y, time;
        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void combinations(int s, List<Node> now) {
        if (now.size() == m) {
            bfs(now);
            return;
        }
        for (int i = s; i < vi.size(); i++) {
            now.add(vi.get(i));
            combinations(i + 1, now);
            now.remove(now.size() - 1);
        }
    }

    public static void bfs(List<Node> act) {
        int[][] tempGrid = new int[n][n];
        for (int i = 0; i < n; i++) {
            tempGrid[i] = Arrays.copyOf(newGrid[i], n); //매번 카피 필요
        }

        Queue<Node> pq = new PriorityQueue<>();
        for (Node node : act) { //초기화
            node.time = 0;
            pq.add(node);
            tempGrid[node.x][node.y] = 0; //0으로 시작
        }

        int maxTime = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int x = now.x, y = now.y, time = now.time;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && 0 <= ny && nx < n && ny < n) {
                    if (tempGrid[nx][ny] == -1) { // 빈 칸인 경우만 전파 시간 기록
                        tempGrid[nx][ny] = time + 1;
                        pq.add(new Node(nx, ny, time + 1));
                        maxTime = Math.max(maxTime, time + 1);
                    } else if (tempGrid[nx][ny] == -3) { // 비활성 바이러스면 활성화만 하고 maxTime 갱신 X
                        tempGrid[nx][ny] = time + 1;
                        pq.add(new Node(nx, ny, time + 1));
                    }
                }
            }
        }

        if (isAllInfected(tempGrid)) { //모두 감염된 경우에만
            minTime = Math.min(minTime, maxTime);
        }
    }

    public static boolean isAllInfected(int[][] tempGrid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tempGrid[i][j] == -1) { // 아직 감염되지 않은 빈 칸이 있다면 실패
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        newGrid = new int[n][n];
        vi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int cell = Integer.parseInt(st.nextToken());
                grid[i][j] = cell;

                if (cell == 0) {
                    newGrid[i][j] = -1;
                } else if (cell == 2) {
                    newGrid[i][j] = -3;
                    vi.add(new Node(i, j, 0));
                } else {
                    newGrid[i][j] = -2;
                }
            }
        }

        combinations(0, new ArrayList<>());
        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }
}
