import java.io.*;
import java.util.*;

//dp (dfs로 할 필요X - 방문한 장소 재방문x, [i-1], [j-1]지점과 비교해서 dp 써내려나가면 됨)
public class 소프티어_나무수확_레벨3_한재경_415ms {
    static int[] dx = new int[]{0, 1}; //우 하
    static int[] dy = new int[]{1, 0};
    static int n;
    static int[][] grid;
    static int[][][] dp;
    static int ans;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        dp = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0][0] = grid[0][0];
        dp[0][0][1] = grid[0][0] * 2;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                if (i != 0) { //1행이상
                    dp[i][j][1] = dp[i-1][j][1] + grid[i][j]; //이전 스프링 그대로 가져가기
                    if (dp[i-1][j][0] + grid[i][j] > dp[i][j][0]) { // 스프링설치x
                        dp[i][j][0] = dp[i-1][j][0] + grid[i][j];
                    }
                    if (dp[i-1][j][0] + grid[i][j] * 2 > dp[i][j][1]) { //(i,j)에 스프링 설치
                        dp[i][j][1] = dp[i-1][j][0] + grid[i][j] * 2;
                    }
                }
                if (j != 0) { //1열이상
                    dp[i][j][1] = Math.max(dp[i][j-1][1] + grid[i][j], dp[i][j][1]); //이전 스프링 그대로 가져가기
                    if (dp[i][j-1][0] + grid[i][j] > dp[i][j][0]) { // 스프링설치x
                        dp[i][j][0] = dp[i][j-1][0] + grid[i][j];
                    }
                    if (dp[i][j-1][0] + grid[i][j] * 2 > dp[i][j][1]) { //(i,j)에 스프링 설치
                        dp[i][j][1] = dp[i][j-1][0] + grid[i][j] * 2;
                    }
                }
            }
        }
        
        System.out.println(dp[n-1][n-1][1]);
    }
}
