import java.io.*;
import java.util.*;

public class Softeer_나무공격_레벨2_한재경 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n][m];
        int[] rows = new int[n]; //각 행별 병사수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                grid[i][j] = x;
                if (x == 1) {
                    rows[i]++;
                }
            }
        }
        
        int result = 0;
        
        for (int t = 0; t < 2; t++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            for (int i = a; i <= b; i++) {
                if (rows[i] > 0) {
                    rows[i]--;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            result += rows[i];
        }
        System.out.println(result);
    }
}
