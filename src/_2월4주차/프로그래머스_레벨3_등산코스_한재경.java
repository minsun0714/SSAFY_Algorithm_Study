import java.util.*;

class 프로그래머스_레벨3_등산코스_한재경 {
    static List<List<int[]>> graph;
    static Set<Integer> summitsSet, gatesSet;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        summitsSet = new HashSet<>();
        for (int s : summits) summitsSet.add(s);

        gatesSet = new HashSet<>();
        for (int g : gates) gatesSet.add(g);

        for (int[] p : paths) {
            graph.get(p[0]).add(new int[]{p[1], p[2]});
            graph.get(p[1]).add(new int[]{p[0], p[2]});
        }

        return dijkstra(n, gates, summits);
    }

    private int[] dijkstra(int n, int[] gates, int[] summits) {
        //pq: {idx, score}. 비용 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] intensities = new int[n + 1]; //각 노드 비용
        Arrays.fill(intensities, Integer.MAX_VALUE);

        // 모든 gate를 출발점으로 추가
        for (int gate : gates) {
            pq.offer(new int[]{gate, 0});
            intensities[gate] = 0;
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0]; //현재노드
            int intensity = cur[1]; //현재비용

            if (intensity > intensities[node]) continue; //현재 비용 > 기록된 비용 진행x
            //즉 다른 경로에서 현재 노드 최적으로 갱신되었을 때 진행x

            if (summitsSet.contains(node)) continue; // 산봉우리에 도착하면 더이상 진행 X

            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int cost = next[1];

                int newIntensity = Math.max(intensity, cost); //다음노드 비용

                if (intensities[nextNode] > newIntensity) { //기록된값보다 더 낮게 갈 수 있으면
                    intensities[nextNode] = newIntensity;
                    pq.offer(new int[]{nextNode, newIntensity});
                }
            }
        }

        Arrays.sort(summits);
        int summitIdx = summits[0], minIntensity = intensities[summitIdx];

        for (int s : summits) {
            if (intensities[s] < minIntensity) {
                summitIdx = s;
                minIntensity = intensities[s];
            }
        }

        return new int[]{summitIdx, minIntensity};
    }
}
