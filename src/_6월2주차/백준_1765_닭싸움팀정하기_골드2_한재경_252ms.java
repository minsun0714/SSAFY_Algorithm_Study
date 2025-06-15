import java.util.*;
import java.io.*;

//유니온파인드
public class 백준_1765_닭싸움팀정하기_골드2_한재경_252ms {
    static int[] parents;
    static int[] rank;
    static List<List<Integer>> enemies;

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[px] > rank[py]) {
            parents[py] = px;
        } else if (rank[py] > rank[px]) {
            parents[px] = py;
        } else {
            parents[py] = px;
            rank[px]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int a = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        parents = new int[n];
        rank = new int[n];
        enemies = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            parents[i] = i;
            enemies.add(new ArrayList<>());
        }

        //친구는 다 유니온, 원수는 서로 추가. 원수 초기세팅 어떻게?
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            if (a.equals("F")) { //친구
                union(b, c);
            } else { //원수
                enemies.get(b).add(c);
                enemies.get(c).add(b);
            }
        }

        //원수"집합"의 원수가 친구가 아니라 특정 원수의 원수가 친구!
        //즉 "원수 친구"의 원수랑은 친구가 아님
        for (int i = 0; i < n; i++) {
            for (int e : enemies.get(i)) { //원수
                for (int ee : enemies.get(e)) { //원수의 원수
                    union(find(i), ee);
                }
            }
        }
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < n; i++) {
            ans.add(find(i));
        }
        System.out.println(ans.size());
    }
}
