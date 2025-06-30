import java.util.*;
import java.io.*;

// 유니온 파인드
public class 백준_4195_친구네트워크_골드2_한재경_728ms {
    static int[] cnt;
    static int[] parent;
    static int[] rank;

    static int find(int node) {
        //경로압축
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    static int union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return cnt[pa];
        }
        if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
            cnt[pa] += cnt[pb];
            return cnt[pa];
        } else if (rank[pb] > rank[pa]) {
            parent[pa] = pb;
            cnt[pb] += cnt[pa];
            return cnt[pb];
        } else {
            parent[pb] = pa;
            cnt[pa] += cnt[pb];
            rank[pa]++;
            return cnt[pa];
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            int f = Integer.parseInt(br.readLine()); //관계수
            cnt = new int[2 * f];
            parent = new int[2 * f];
            rank = new int[2 * f];

            for (int i = 0; i < 2 * f; i++) {
                parent[i] = i;
                rank[i] = 1;
                cnt[i] = 1;
            }

            //친구 구분은 맵으로
            Map<String, Integer> map = new HashMap<>();
            int idx = 0;
            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken(); //친구1
                String b = st.nextToken(); //친구2
                map.putIfAbsent(a, idx++);
                map.putIfAbsent(b, idx++);

                union(map.get(a), map.get(b));
                sb.append(cnt[find(map.get(a))] + "\n");
            }
        }
        System.out.println(sb);
    }
}
