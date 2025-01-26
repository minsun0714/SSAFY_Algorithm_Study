// tree dp

package _1월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_25515_트리노드합의최댓값_골드4_이민선_416ms {
    static int n;
    static List<List<Integer>> graph;
    static int[] score;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        for (int i=0;i<n-1;i++){
            st =  new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(p).add(c);
        }
        score = new int[n];
        st =  new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++){
            score[i] = Integer.parseInt(st.nextToken());
        }
        dp = new long[n];
        dfs(0);
        System.out.println(dp[0]);
    }

    private static void dfs(int root) {
        dp[root] = score[root];
        for (int i=0;i<graph.get(root).size();i++){
            int next = graph.get(root).get(i);
                dfs(next);
                dp[root] = Math.max(dp[root] + dp[next], dp[root]);
        }
    }
}
