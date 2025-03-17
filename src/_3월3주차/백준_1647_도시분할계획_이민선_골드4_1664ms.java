package _3월3주차;

import java.io.*;
import java.util.*;

public class 백준_1647_도시분할계획_이민선_골드4_1664ms {
    static int n, m;
    static List<int[]> graph = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new int[]{a, b, c});
        }

        parent = new int[n + 1];

        for (int i=1;i<=n;i++) parent[i] = i;

        graph.sort(Comparator.comparingInt(a -> a[2]));

        int maxCost = 0;
        long answer = 0;
        for (int i=0;i<m;i++){
            int a = graph.get(i)[0], b = graph.get(i)[1], c = graph.get(i)[2];

            if (find(a) != find(b)){
                union(a, b);

                maxCost = c;
                answer += c;
            }
        }
        System.out.println(answer - maxCost);
    }

    private static int find(int x){
        if (x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
