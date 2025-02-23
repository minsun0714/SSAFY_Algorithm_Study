package _2월4주차;

import java.io.*;
import java.util.*;

public class Softeer_출퇴근길_레벨3_이민선_862ms {
    static int n, m;
    static int s, t;
    static List<List<Integer>> edges = new ArrayList<>();
    static List<List<Integer>> reversedEdges = new ArrayList<>();
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i=0;i<=n;i++){
            edges.add(new ArrayList<>());
            reversedEdges.add(new ArrayList<>());
        }

        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());

            edges.get(x).add(y);
            reversedEdges.get(y).add(x);
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        // 출근길
        Set<Integer> set1 = new HashSet<>();
        visited = new int[n + 1];
        Arrays.fill(visited, -1);
        dfs(s, t, edges, set1);

        Set<Integer> set2 = new HashSet<>();
        visited = new int[n + 1];
        Arrays.fill(visited, -1);
        dfs(t, -1, reversedEdges, set2);

        // 퇴근길
        Set<Integer> set3 = new HashSet<>();
        visited = new int[n + 1];
        Arrays.fill(visited, -1);
        dfs(t, s, edges,set3);

        Set<Integer> set4 = new HashSet<>();
        visited = new int[n + 1];
        Arrays.fill(visited, -1);
        dfs(t, -1, reversedEdges, set4);

        set1.retainAll(set2);
        set3.retainAll(set4);
        set1.retainAll(set3);

        set1.remove(s);
        set1.remove(t);
        System.out.println(set1.size());
    }

    public static void dfs(int cur, int destination, List<List<Integer>> edges, Set<Integer> set){
        if (cur == destination) return;
        if (visited[cur] != -1) return;

        visited[cur] = 0;
        for (int i=0;i<edges.get(cur).size();i++){
            int next = edges.get(cur).get(i);
            dfs(next, destination, edges, set);
            set.add(cur);
        }
    }
}
