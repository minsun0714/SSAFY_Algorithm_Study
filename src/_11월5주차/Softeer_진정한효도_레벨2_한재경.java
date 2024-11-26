import java.io.*;
import java.util.*;

public class Softeer_진정한효도_레벨2_한재경 {
    static int minCost = 100;
    static int[][] grid;
    static final int n = 3;
    
    static void calc(int x, int y, int std) {
        int xcnt = 0;
        int ycnt = 0;
        for (int i = 0; i < n; i++) {
            xcnt += Math.abs(grid[x][i] - std);
            ycnt += Math.abs(grid[i][y] - std);
        }
        int mincnt = Math.min(xcnt, ycnt);
        minCost = Math.min(minCost, mincnt);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //각 땅 기준점
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int std = grid[i][j];
                calc(i, j, std);
            }
        }
        System.out.println(minCost);
    }
}
