package _3월3주차;

import java.io.*;
import java.util.*;

public class 백준_2623_음악프로그램_골드3_이민선_84ms {
    static int n, m;
    static List<List<Integer>> graph;
    static int[] indegree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        indegree = new int[n + 1];

        for (int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int prev = 0;
            for (int j=0;j<k;j++){
                int num = Integer.parseInt(st.nextToken());

                if (j != 0){
                    indegree[num]++;
                    graph.get(prev).add(num);
                }
                prev = num;
            }
        }

        topologySort();
        boolean isPossible = true;
        for (int i=1;i<=n;i++){
            if (indegree[i] != 0) isPossible = false;
        }
        if (isPossible) System.out.println(sb);
        else System.out.println(0);
    }

    private static void topologySort(){
        Queue<Integer> q = new LinkedList<>();
        for (int i=1;i<=n;i++){
            if (indegree[i] == 0){
                q.offer(i);
            }
        }

        while (!q.isEmpty()){
            int cur = q.poll();

            sb.append(cur).append("\n");

            for (int i=0;i<graph.get(cur).size();i++){
                int next = graph.get(cur).get(i);
                indegree[next]--;
                if (indegree[next] == 0){
                    q.offer(next);
                }
            }
        }
    }
}
