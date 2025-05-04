package etc._4_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 위상정렬
public class Main_백준_2252_줄세우기_골드3_500ms {
	public static void main(String[] args) throws Exception {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 그래프
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[N + 1];

        // 간선 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
            indegree[to]++;
        }

        // 최초
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        StringBuilder answer = new StringBuilder();
        boolean[] visited = new boolean[N + 1];

        while (!q.isEmpty()) {
            int cur = q.poll();
            answer.append(cur).append(" ");
            visited[cur] = true;

            for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                indegree[next]--;
                if (indegree[next] == 0 && !visited[next]) {
                    q.add(next);
                }
            }
        }

        // 남은 노드들
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                answer.append(i).append(" ");
            }
        }

        // 결과 출력
        System.out.println(answer);
    }
}