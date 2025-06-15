import java.io.*;
import java.util.*;

// 크루스칼
class Node {
    float x;
    float y;

    Node(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

class Link implements Comparable<Link> {
    int from; //출발 노드 인덱스
    int to;
    float cost;

    Link(int from, int to, float cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int compareTo(Link o) {
        if (this.cost > o.cost) {
            return 1;
        } else if (this.cost == o.cost) {
            return 0;
        }
        return -1;
    }
}

public class 백준_4386_별자리만들기_골드3_한재경_128ms {
    static List<Link> links;
    static int[] parents;
    static int[] ranks;

    public static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // 랭크가 더 큰 간선으로 편입
        if (rootX == rootY) {
            return false;
        }

        if (ranks[rootX] > ranks[rootY]) {
            parents[rootY] = rootX;
        } else if (ranks[rootX] < ranks[rootY]) {
            parents[rootX] = rootY;
        } else {
            ranks[rootX]++;
            parents[rootY] = rootX;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n]; //전체 노드
        links = new ArrayList<>(); //간선
        parents = new int[n];
        ranks = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            nodes[i] = new Node(x, y);
        }

        // Link 구하기: 모든 경우 가중치
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //i->j
                float cost = (float) Math.sqrt(Math.pow(nodes[j].x - nodes[i].x, 2)
                        + Math.pow(nodes[j].y - nodes[i].y, 2));
                links.add(new Link(i, j, cost));
            }
        }
        Collections.sort(links); //정렬

        // 부모 세팅
        for (int i = 0; i < n; i++) {
            parents[i] = i;

        }

        // 유니온 파인드
        // 총 연결개수 == 노드개수 - 1이면 스탑
        int cnt = 0;
        float ans = 0;

        for (int i = 0; i < links.size(); i++) {
            if (union(links.get(i).from, links.get(i).to)) {
                cnt++;
                ans += links.get(i).cost;
            }
            if (cnt == n - 1) {
                break;
            }
        }
        System.out.printf("%.2f\n", ans);
    }
}
