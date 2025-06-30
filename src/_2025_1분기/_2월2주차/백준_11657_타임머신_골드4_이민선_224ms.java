package _2월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_11657_타임머신_골드4_이민선_224ms {
    static int n, m;
    static int[][] edges;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new int[m][3];
        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new int[]{a, b, c};
        }

        if (hasNegativeCycle()){
            System.out.println(-1);
        } else {
            for (int i=2;i<=n;i++){
                System.out.println(dist[i] == Integer.MAX_VALUE ? -1 : dist[i]);
            }
        }
    }

    public static boolean hasNegativeCycle(){
        dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                int[] edge = edges[j];

                int a = edge[0], b = edge[1], c = edge[2];
                if (dist[a] != Integer.MAX_VALUE && dist[a] + c < dist[b]){
                    dist[b] = dist[a] + c;
                    if (i == n - 1) return true;
                }
            }
        }

        return false;
    }
}