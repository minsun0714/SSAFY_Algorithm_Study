import java.io.*;
import java.util.*;

// Union-Find : disjoint set
// 적 관련으로 더 줄일 수 있을거라 생각하는 데...... 계속 실패해서 마감
public class 백준_1765_닭싸움팀정하기_골드2_이지희_152ms {
    static int[] parent;
    static List<Integer>[] enemies;

    // union
    static void friend(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return ;

        if(x < y) parent[y] = x;
        else parent[x] = y;
    }

    static int find(int x) {
        if(x == parent[x])
            return x;

        return parent[x] = find(parent[x]);
    }

    // transitivity union
    // person : 나, target : 적
    static void enemy(int person, int target) {
        if(enemies[target].isEmpty())
            return;
        // 적의 적은 나의 친구
        // 첫번째 적으로만 하는 이유 : 어차피 적들끼리 이미 같은 팀이기 때문
        int e = enemies[target].get(0);
        friend(person, e);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        enemies = (List<Integer>[]) new ArrayList[N + 1];

        for(int i=1; i<=N; i++) {
            parent[i]=i;
            enemies[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(br.readLine());

        for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(command.equals("F")) {
                friend(x, y);

            } else {
                enemy(x, y);
                enemy(y, x);
                enemies[x].add(y);
                enemies[y].add(x);
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i=1; i<=N; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
        br.close();
    }

}