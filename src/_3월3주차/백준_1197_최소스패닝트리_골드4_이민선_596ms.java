package _3월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_1197_최소스패닝트리_골드4_이민선_596ms {
    static int v, e;
    static List<int[]> graph = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.add(new int[]{v, e, c});
        }

        graph.sort(Comparator.comparingInt(a -> a[2]));

        parent = new int[v + 1];

        for (int i=0;i<=v;i++) parent[i] = i;

        int answer = 0;
        for (int i=0;i<e;i++){
            int a = graph.get(i)[0], b = graph.get(i)[1], c = graph.get(i)[2];

            if (find(a) != find(b)){
                union(a, b);
                answer += c;
            }
        }

        System.out.println(answer);
    }

    private static int find(int x){
        if (parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if (a < b){
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}
