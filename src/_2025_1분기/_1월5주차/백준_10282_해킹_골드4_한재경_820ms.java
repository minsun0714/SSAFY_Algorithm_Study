import java.io.*;
import java.util.*;

//다익스트라
public class 백준_10282_해킹_골드4_한재경_820ms {
    static List<List<Node>> links;
    
    static class Node implements Comparable<Node> {
        int idx;
        int cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    //dist리턴
    public static int[] dijkstra(int start, int n) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0)); //start노드에서 시작
        int[] dist = new int[n]; //최소 가중치 그래프
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        //해당 노드 갱신값 > 기존값이면 진행x
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            for (Node next : links.get(now.idx)) { //연결노드들 탐색
                //현재가중치+이동가중치 < next기존가중치
                if (dist[now.idx] + next.cost < dist[next.idx]) { 
                    pq.offer(new Node(next.idx, dist[now.idx] + next.cost));
                    dist[next.idx] = dist[now.idx] + next.cost;
                }
            }
        }
        return dist;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); //컴퓨터 개수
            int d = Integer.parseInt(st.nextToken()); //의존성 개수
            int c = Integer.parseInt(st.nextToken()) - 1; //해킹컴퓨터 인덱스
            
            //인접그래프
            links = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                links.add(new ArrayList<>());
            }
            
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int b = Integer.parseInt(st.nextToken()) - 1; //b로
                int a = Integer.parseInt(st.nextToken()) - 1; //a에서
                int s = Integer.parseInt(st.nextToken()); //s초 후
                links.get(a).add(new Node(b, s));
            }
            
            int[] dist = dijkstra(c, n);
        
            int cnt = 0;
            int time = 0;
            for (int i = 0; i < n; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    cnt++;
                    if (dist[i] > time) {
                        time = dist[i];
                    }
                }
            }
            System.out.println(cnt + " " + time);
        }
    }
}
