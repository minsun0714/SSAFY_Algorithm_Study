import java.io.*;
import java.util.*;

//bfs, 548ms
public class Softeer_이미지프로세싱_레벨3_한재경 {
    static int[] dx = new int[] {0, 0, -1, 1};
    static int[] dy = new int[] {-1, 1, 0, 0};
    static int[][] grid;
    static int h;
    static int w;
    static int[][] visited;

    //(i, j, k) 픽셀(i, j)과 같은색, 덩어리 픽셀들 모두 afterColor로 바꿈
    public static void bfs(int sx, int sy, int color, int afterColor) {
        Queue<int[]> qu = new ArrayDeque<>();
        qu.add(new int[] {sx, sy});
        visited = new int[h][w];
        visited[sx][sy] = 1;
        grid[sx][sy] = afterColor;
        
        while(!qu.isEmpty()) {
            int[] nxny = qu.poll();
            int x = nxny[0];
            int y = nxny[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < h && nx >= 0 && ny < w && ny >= 0 &&
                    visited[nx][ny] != 1 && grid[nx][ny] == color) {                    
                    grid[nx][ny] = afterColor;
                    visited[nx][ny] = 1;
                    qu.add(new int[] {nx, ny});
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        grid = new int[h][w]; //각 픽셀 색상 저장
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int q = Integer.parseInt(br.readLine());
        for (int t = 0; t < q; t++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            int k = Integer.parseInt(st.nextToken());

            bfs(i, j, grid[i][j], k);
            
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
