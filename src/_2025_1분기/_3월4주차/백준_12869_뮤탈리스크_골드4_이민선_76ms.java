package _3월4주차;

import java.io.*;
import java.util.*;

public class 백준_12869_뮤탈리스크_골드4_이민선_76ms {
    static int n;
    static int[] SCVs = new int[3];
    static int[][] powers = new int[][]{{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};
    static int[][][] dp = new int[61][61][61];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (n-- > 0){
            SCVs[idx++] = Integer.parseInt(st.nextToken());
        }

        for (int i=0;i<=60;i++) {
            for (int j=0;j<=60;j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        int answer = dfs(SCVs[0], SCVs[1], SCVs[2]);
        System.out.println(answer);
    }

    private static int dfs(int x, int y, int z){
        if (x <= 0 && y <= 0 && z <= 0) return 0;
        if (dp[x][y][z] != -1) return dp[x][y][z];

        dp[x][y][z] = Integer.MAX_VALUE;

        for (int[] power: powers){
            int nx = Math.max(x - power[0], 0);
            int ny = Math.max(y - power[1], 0);
            int nz = Math.max(z - power[2], 0);

            dp[x][y][z] = Math.min(dp[x][y][z], dfs(nx, ny, nz) + 1);
        }

        return dp[x][y][z];
    }
}
