package etc._1_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라, 704ms
public class 백준_10282_해킹_골드4_김동빈_710ms {
	
    static class Info implements Comparable<Info> {
        int computer;
        int dist;

        public Info(int computer, int time) {
            this.computer = computer;
            this.dist = time;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
	
    static int INF = 100000000;
    static ArrayList<Info>[] graph;
	static int n;
	static StringBuilder answer;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
        
        // main
        for (int t = 1; t <= T; t++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
            n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1;
            
            // 그래프 초기화
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            
            // 의존성 입력받기
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());
                
                graph[b].add(new Info(a, s));
            }
            
            // 다익스트라 실행
            dijkstra(c);
        }
        
        // 정답 출력
        System.out.println(answer);
    }

    // 다익스트라
    static void dijkstra(int start) {
    	
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(start, 0));

        while (!pq.isEmpty()) {
            Info info = pq.poll();
            int cur = info.computer;
            int d = info.dist;

            // 가치 없다.
            if (d > dist[cur]) continue;

            // 탐색
            for (Info link : graph[cur]) {
                int next = link.computer;
                int nd = link.dist;
                
                int totalDist = d + nd;
                if (totalDist >= dist[next]) continue; // 갈 가치 없다.
                
                // 간다.
                dist[next] = totalDist;
                pq.offer(new Info(next, totalDist));
            }
        }

        // dist 순회
        int infectedCount = 0;
        int infectedTime = 0;

        for (int i = 0; i < n; i++) {
        	// 감염 되었다.
            if (dist[i] != INF) {
                infectedCount++;
                if (dist[i] > infectedTime) {
                	infectedTime = dist[i];
                }
            }
        }
        
        // 정답 append
        answer.append(infectedCount).append(" ").append(infectedTime).append("\n");
        
        return;
    }
}
