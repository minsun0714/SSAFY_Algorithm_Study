package _9월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상정렬

public class 백준_17220_마약수사대_골드4_이민선_68ms {
    static int n;
    static int m;
    static List<ArrayList<Integer>> graph;
    static int[] indegree;
    static Set<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<ArrayList<Integer>>();
        indegree = new int[n];
        for (int i=0;i<n;i++){
            graph.add(new ArrayList<Integer>());
        }
        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());

            char a = st.nextToken().charAt(0);
            char b = st.nextToken().charAt(0);

            graph.get(a - 65).add(b - 65);
            indegree[b - 65]++;
        }

        set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        while(st.hasMoreTokens()){
            set.add(st.nextToken().charAt(0) - 65);
        }
        int answer = topologySort();
        System.out.println(answer);
    }

    public static int topologySort(){
        Queue<Integer> q = new LinkedList<Integer>();
        int answer = 0;

        for (int i=0;i<n;i++){
            if (indegree[i] == 0 && !set.contains(i)){
                q.offer(i);
            }
        }

        while (!q.isEmpty()){
            int cur = q.poll();

            for (int i=0;i<graph.get(cur).size();i++){
                if (set.contains(graph.get(cur).get(i))){
                    continue;
                }
                indegree[graph.get(cur).get(i)]--;


                answer++;
                q.offer(graph.get(cur).get(i));
            }
        }
        return answer;
    }
}
