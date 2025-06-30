import java.util.*;
import java.io.*;

//크루스칼 + BFS
class Bridge implements Comparable<Bridge> {
    int from;
    int to;
    int cost;

    Bridge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int compareTo(Bridge o) {
        return this.cost - o.cost;
    }
}
public class 백준_17472_다리만들기2_골드1_한재경_104ms {
    static int n;
    static int m;
    static int[][] grid;
    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{1, -1, 0, 0};
    static PriorityQueue<Bridge> bridges = new PriorityQueue<>();
    static int[] parent;
    static int[] rank;
    static int minBridgeCost;

    //섬 num으로 색칠하기
    static void bfs(int sx, int sy, int num) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        grid[sx][sy] = num;
        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0];
            int y = xy[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && grid[nx][ny] == 1) {
                    grid[nx][ny] = num;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    //해당지점에서 특정 방향으로 뻗어나가서 다리 생성
    static void setBridge(int from, int sx, int sy) {
        for (int dir = 0; dir < 4; dir++) {
            int cnt = 0;
            int x = sx;
            int y = sy;
            while (true) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                //다른 섬 발견 시 (다리길이 2 이상)
                if (0 <= nx && nx < n && 0 <= ny && ny < m && grid[nx][ny] > 1 && cnt > 1) {
                    Bridge bridge = new Bridge(from, grid[nx][ny], cnt);
                    bridges.add(bridge);
                    break;
                }
                //전진
                else if (0 <= nx && nx < n && 0 <= ny && ny < m && grid[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    cnt++;
                }
                else {
                    break;
                }
            }
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        // 경로 압축
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return;
        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        minBridgeCost = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //섬 색칠하기
        int num = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    bfs(i, j, num++);
                }
            }
        }

        //모든 다리 만들어서 bridges (우선순위큐)에 추가
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] > 1) {
                    int from = grid[i][j]; //시작섬
                    setBridge(from, i, j); //다리 생성
                }
            }
        }

        //각 섬의 부모 자기자신으로 초기화 (각 섬번호 2부터 시작)
        parent = new int[num];
        rank = new int[num];
        for (int i = 2; i < num; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        //우선순위큐 bridges 다리들로 크루스칼하기
        int mstCnt = 0; //지금까지 mst에 포함시킨 다리 개수
        int costSum = 0; //해당 다리들의 비용합
        while (!bridges.isEmpty()) {
            Bridge b = bridges.poll();
            //다리 양쪽 섬의 부모 다르면 유니온
            int pa = find(b.from);
            int pb = find(b.to);
            if (pa != pb) {
                union(pa, pb);
                costSum += b.cost;
                mstCnt++;
            }
        }

        // 연결된 간선 수가 (섬 개수 − 1) 이면 성공
        int islandCnt = num - 2; //섬 번호는 2부터 시작
        if (mstCnt == islandCnt - 1) {
            System.out.println(costSum);
        } else {
            System.out.println(-1);
        }
    }
}
