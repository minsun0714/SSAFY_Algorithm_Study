package _3월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_14567_선수과목_골드5_이민선_504ms {
    static int n, m;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] indegree;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        indegree = new int[n + 1];

        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            indegree[b]++;
        }

        result = new int[n + 1];

        topologySort();
        for (int i=1;i<=n;i++){
            sb.append(result[i]).append(" ");
        }
        System.out.print(sb);
    }

    private static void topologySort(){
        Queue<int[]> q = new LinkedList<>();
        for (int i=1;i<=n;i++){
            if (indegree[i] == 0) {
                q.offer(new int[]{1, i});
                result[i] = 1;
            }
        }

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int term = cur[0], course = cur[1];

            for (int i=0;i<graph.get(course).size();i++){
                int next = graph.get(course).get(i);
                indegree[next]--;
                if (indegree[next] == 0){
                    q.offer(new int[]{term + 1, graph.get(course).get(i)});
                    result[next] = term + 1;
                }
            }
        }
    }
}
