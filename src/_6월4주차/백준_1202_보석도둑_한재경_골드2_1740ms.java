import java.io.*;
import java.util.*;

//우선순위큐
public class 백준_1202_보석도둑_한재경_골드2_1740ms {
    static class Node implements Comparable<Node> {
        int weight;
        int cost;

        Node(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        // 무게작은순 정렬 (작은 가방부터 넣기 시작하기 때문)
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
//        int t = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //보석개수
        int k = Integer.parseInt(st.nextToken()); //가방개수
        Node[] nodes = new Node[n];
        int[] bags = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); //무게
            int c = Integer.parseInt(st.nextToken()); //가격
            nodes[i] = new Node(w, c);
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken()); //가방 최대무게
        }

        //작은 가방부터 시작 (큰 가방부터 하면 가벼운데 큰 가치인 애 먼저 채택될 수도)
        Arrays.sort(bags);
        Arrays.sort(nodes);

        //현재 가방에 담을 수 있는 애 담기: 작은 가방부터 시작하므로 이후 뺄 필요 없음
        //보석의 "가치"만 담음!! 가장 높은 가치로 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int nodeIdx = 0;
        long sum = 0;
        for (int i = 0; i < k; i++) { //i번째가방

            // 현재 가방에 넣을 수 있는 크기면
            while (nodeIdx < nodes.length && nodes[nodeIdx].weight <= bags[i]) {
                pq.add(nodes[nodeIdx].cost);
                nodeIdx++;
            }

            // pq에서 가장 큰 애 빼내기. 없으면 0
            sum += pq.isEmpty() ? 0 : pq.poll();
        }
        System.out.println(sum);
    }
}
