package _4월4주차;

import java.util.*;

// 그래프
class Solution {

    static HashMap<Integer, Integer> indegree;
    static HashMap<Integer, Integer> outdegree;
    static HashMap<Integer, ArrayList<Integer>> graph;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        indegree = new HashMap<>();
        outdegree = new HashMap<>();
        graph = new HashMap<>();
        HashSet<Integer> Vset = new HashSet<>();

        // 답
        int generatedV = -1;
        int dounutCount = 0;
        int barCount = 0;
        int eightCount = 0;

        for (int[] edge : edges) {

            // a -> b로 감
            int a = edge[0];
            int b = edge[1];
            Vset.add(a);
            Vset.add(b);
            // 그래프 형성
            graph.putIfAbsent(a, new ArrayList<>());
            graph.get(a).add(b);

            indegree.putIfAbsent(a, 0);
            outdegree.putIfAbsent(a, 0);
            outdegree.put(a, outdegree.get(a) + 1);

            outdegree.putIfAbsent(b, 0);
            indegree.putIfAbsent(b, 0);
            indegree.put(b, indegree.get(b) + 1);
        }

        // 최초 생긴 점 찾기
        for (int point : Vset) {
            if (indegree.get(point) == 0 && outdegree.get(point) >= 2) {
                generatedV = point;
                break;
            }
        }

        List<Integer> linkedPoints = graph.get(generatedV);
        for (int link : linkedPoints) {
            indegree.put(link, indegree.get(link) - 1);
        } // 인디그리 하나씩 줄여주기

        Vset.remove(generatedV); // 임의의 점 날려버리기
        HashMap<Integer, Boolean> visited = new HashMap<>();

        // 막대그래프
        for (int point : Vset) {
            if (indegree.get(point) == 0) { // 막대
                barCount++;
                bfs(point, visited);
                visited.put(point, true);
            }
        }

        // 8 그래프
        for (int point : Vset) {
            if (indegree.get(point) == 2 && outdegree.get(point) == 2) { // 8
                eightCount++;
                visited.put(point, true);
            }
        }

        // 도넛 그래프
        for (int point : Vset) {
            if (!visited.containsKey(point) && indegree.get(point) == 1 && outdegree.get(point) == 1) { //
                visited.put(point, true);
                boolean result = bfs(point, visited);

                if (result) {
                    dounutCount++;
                }
            }
        }

        // 정답
        answer[0] = generatedV;
        answer[1] = dounutCount;
        answer[2] = barCount;
        answer[3] = eightCount;

        return answer;
    }

    private boolean bfs(int point, HashMap<Integer, Boolean> globalVisited) {

        HashMap<Integer, Boolean> visited = new HashMap<>();

        boolean result = true;

        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(point);
        visited.put(point, true);
        while(!q.isEmpty()) {

            int cur = q.poll();

            for (int next : graph.getOrDefault(cur, new ArrayList<Integer>())) {

                if (indegree.get(next) == 2 && outdegree.get(next) == 2) { // 8 그래프
                    result = false;
                } else {
                    globalVisited.put(cur, true);
                }

                if(visited.containsKey(next)) continue;
                q.offer(next);
                visited.put(next, true);
            }
        }

        return result;
    }
}
