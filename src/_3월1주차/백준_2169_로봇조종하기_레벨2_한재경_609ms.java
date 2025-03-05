import java.util.*;
import java.io.*;

//dp
public class 백준_2169_로봇조종하기_레벨2_한재경_609ms {
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n][m]; //각 셀에 가치
        int[][][] dp = new int[n][m][2]; // 오/왼값 구분
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // dp 배열 초기화: 최악의 경우보다 작은 값으로 채워줌.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0] = dp[i][j][1] = -101 * n * m;
            }
        }

        dp[0][0][0] = grid[0][0];
        for (int i = 1; i < m; i++) { //첫 행 채우기
            dp[0][i][0] = dp[0][i-1][0] + grid[0][i];
            dp[0][i][1] = dp[0][i][0];
        }
        for (int i = 1; i < n ; i++) {
            //위,왼에서 온 값: dp 0
            for (int j = 0; j < m; j++) {
                //위에서 온 값은 픽스: 윗셀 왼값 vs 오값 중 최대
                dp[i][j][0] = Math.max(dp[i-1][j][0] + grid[i][j], dp[i-1][j][1] + grid[i][j]);
                if (j != 0) { //첫 열 아니면 왼값 vs 현재값
                    dp[i][j][0] = Math.max(dp[i][j - 1][0] + grid[i][j], dp[i][j][0]);
                }
            }
            //위,오에서 온 값: dp 1
            for (int j = m-1; j >= 0; j--) {
                //위에서 온 값은 픽스: 윗셀 왼값 vs 오값 중 최대
                dp[i][j][1] = Math.max(dp[i-1][j][0] + grid[i][j], dp[i-1][j][1] + grid[i][j]);
                if (j != m - 1) { //마지막 열 아니면 오값 vs 현재값
                    dp[i][j][1] = Math.max(dp[i][j + 1][1] + grid[i][j], dp[i][j][1]);
                }
            }
        }

        System.out.println(Math.max(dp[n-1][m-1][0], dp[n-1][m-1][1]));
    }
}
