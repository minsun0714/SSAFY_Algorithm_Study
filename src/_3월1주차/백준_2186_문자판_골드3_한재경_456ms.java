import java.util.*;
import java.io.*;

//dfs, dp (욕심쟁이 판다 유형)
public class 백준_2186_문자판_골드3_한재경_456ms {
    static int[] dx = new int[]{0, 0 ,-1, 1};
    static int[] dy = new int[]{1, -1, 0, 0};
    static int ans, n, m, k;
    static String target;
    static String[] s;
    static int[][][] dp; //dp[x][y][z] : z번째 depth에서의 경우의 수 카운팅

    // 끝에서부터 메모.
    static int dfs(int depth, int x, int y) { //depth==k-1부터 0까지
        if (depth == target.length()) { //끝까지 오면 1
            return 1;
        }
        if (dp[x][y][depth] != -1) { //이미 메모된 값이면
            return dp[x][y][depth];
        }
        dp[x][y][depth] = 0; //메모 안된값이면 초기화
        for (int i = 0; i < 4; i++) { //i방향
            for (int j = 1; j <= k; j++) { //j칸
                int nx = x + (dx[i] * j);
                int ny = y + (dy[i] * j);
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (s[nx].charAt(ny) == target.charAt(depth)) {
                        dp[x][y][depth] += dfs(depth + 1, nx, ny);
                    }
                }
            }
        }
        return dp[x][y][depth];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); //상하좌우 이동가능칸

        s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = br.readLine();
        }
        target = br.readLine();
        dp = new int[n][m][target.length()];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s[i].charAt(j) == target.charAt(0)) {
                    ans += dfs(1, i, j);
                }
            }
        }
        System.out.println(ans);
    }
}
