// simple
// [ 82 ms | 11 mb ]
import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        int[] sum = new int[n]; 

        int[][] origin = new int[n][m];
        for (int row = 0; row < n; row++) {
            tokens = new StringTokenizer(input.readLine());
            for (int col = 0; col < m; col++) {
                origin[row][col] = Integer.parseInt(tokens.nextToken());
                sum[row] += origin[row][col];
            }
        }

        for (int i = 0; i < 2; i++) {
            tokens = new StringTokenizer(input.readLine());
            int up = Integer.parseInt(tokens.nextToken()) -1;   
            int down = Integer.parseInt(tokens.nextToken()) -1;
            for (int row = up; row <= down; row++) {
                if(sum[row]>=1){
                    sum[row]--;
                }
            }
        }
      
        int answer = 0;
        for(int value : sum){
            answer += value;
        }
      
        System.out.println(answer);
    }
}
