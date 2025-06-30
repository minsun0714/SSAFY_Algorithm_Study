package _3월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_1949_우수마을_골드2_이민선_188ms {
    static int n;
    static int[] vals;
    static List<List<Integer>> tree = new ArrayList<>();
    static boolean[] visited;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        vals = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int idx = 1;
        while (st.hasMoreTokens()){
            vals[idx++] = Integer.parseInt(st.nextToken());
        }

        for (int i=0;i<=n;i++) tree.add(new ArrayList<>());

        for (int i=0;i<n-1;i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        visited = new boolean[n + 1];
        dp = new int[n + 1][2];
        for (int i=1;i<=n;i++){
            dp[i][1] = vals[i];
        }
        visited[1] = true;
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int cur){
        for (int next:tree.get(cur)){
            if (!visited[next]){
                visited[next] = true;
                dfs(next);

                dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
                dp[cur][1] += dp[next][0];
            }
        }
    }
}
