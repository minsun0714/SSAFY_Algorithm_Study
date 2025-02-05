import java.io.*;
import java.util.*;

public class 백준_2638_치즈_골드3_한재경_432ms {
    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{1, -1, 0, 0};
    static int[][] grid;
    static boolean[][] visited;
    static Map<String, Integer> cmap; //겉부분 치즈좌표 : 카운트
    static int n, m;

    // 겉부분 치즈 좌표 카운팅
    public static boolean bfs(int sx, int sy) {
        Queue<int[]> q = new ArrayDeque<>();
        Queue<int[]> popq = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0];
            int y = xy[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
            
                if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny]) {
                    if (grid[nx][ny] == 1) { // 치즈면 카운팅
                        cmap.putIfAbsent(nx + " " + ny, 0);
                        cmap.put(nx + " " + ny, cmap.get(nx + " " + ny) + 1);
                        if (cmap.get(nx + " " + ny) == 2) { //2번째 접촉 시
                            popq.add(new int[]{nx, ny}); //없앨 큐에 넣고
                            visited[nx][ny] = true; //방문처리 (중복탐색 방지)
                        }
                    } //빈 공간이면
                    else if (grid[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        if (popq.isEmpty()) {
            return true;
        }
        // 한 사이클 끝나면 pop큐(접촉 2번 이상)인 애들 전부 없애기
        while (!popq.isEmpty()){
            int[] popxy = popq.poll();
            int popx = popxy[0];
            int popy = popxy[1];
            grid[popx][popy] = 0;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        while (true) {
            visited = new boolean[n][m];
            cmap = new HashMap<>(); // 치즈좌표:카운트
            if (bfs(0, 0)) {
                break;
            }
            cnt++;

        }
        System.out.println(cnt);
    }
}
