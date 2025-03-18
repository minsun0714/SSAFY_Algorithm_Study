package _3월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상 정렬,dp
public class 백준_1005_ACMCraft_골드3_이민선_784ms {
    static int t;
    static int n, k;
    static int[] buildings;
    static int[] indegree;
    static List<List<Integer>> graph;
    static int w;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (t-- > 0){
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            buildings = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i=1;i<=n;i++){
                buildings[i] = Integer.parseInt(st.nextToken());
            }
            graph = new ArrayList<>();

            for (int i=0;i<=n;i++){
                graph.add(new ArrayList<>());
            }

            indegree = new int[n + 1];
            for (int i=0;i<k;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                indegree[b]++;
            }

            w = Integer.parseInt(br.readLine());

            topologySort();
        }
        System.out.println(sb);
    }

    private static void topologySort(){
        Queue<Integer> q = new LinkedList<>();
        int[] cost = new int[n + 1];

        for (int i=1;i<=n;i++){
            cost[i] = buildings[i];
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()){
            int cur = q.poll();

            for (int i=0;i<graph.get(cur).size();i++){
                int next = graph.get(cur).get(i);
                cost[next] = Math.max(cost[next], cost[cur] + buildings[next]);
                indegree[next]--;
                if (indegree[next] == 0){
                    q.offer(next);
                }
            }
        }
        sb.append(cost[w]).append("\n");
    }
}
