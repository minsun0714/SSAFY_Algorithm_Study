import java.io.*;
import java.util.*;

// bfs, a->b(b도달하면 종료: b방문 후 다른지점 방문 방지) + b->a(역방향arr넣기, 방문 가능한 모든 지점) = a->b갈때 마주치는 모든 정점
// 역방향은 해당 해당 정점과의 연결가능성만 확인. 따라서 end조건x
// end조건 넣으면 안되는 이유: a->b갈 때 무조건 a경유해야하는 노드 존재 가능. b->a올 때도 해당 노드 거칠 때
public class Softeer_출퇴근길_레벨3_한재경_692ms {
    static int s; //시작점
    static int t; //도착점
    static int n;

    //end가면 stop. 모든 노드 visited 메모.
    static boolean[] bfsEx(int st, int end, List<List<Integer>> link) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(st);
        visited[st] = true;
        while(!q.isEmpty()) {
            int now = q.poll();
            for (int nxt : link.get(now)) {
                if (!visited[nxt] && nxt != end) {
                    q.add(nxt);
                    visited[nxt] = true;
                }
            }
        }
        return visited;
    }

    // 출발지에서 가는 모든 지점
    static boolean[] bfs(int st, List<List<Integer>> link) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(st);
        visited[st] = true;
        while(!q.isEmpty()) {
            int now = q.poll();
            for (int nxt : link.get(now)) {
                if (!visited[nxt]) {
                    q.add(nxt);
                    visited[nxt] = true;
                }
            }
        }
        return visited;
    }

    //출근길에 s여러번 방문가능. 퇴근길에 t 여러번 방문 가능.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //n개 노드
        int m = Integer.parseInt(st.nextToken()); //n개 간선
        List<List<Integer>> link = new ArrayList<>(); //i->j
        List<List<Integer>> rev = new ArrayList<>(); //i->j 가는 역방향 리스트
        
        for (int i = 0; i < n; i++) {
            link.add(new ArrayList<>());
            rev.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            link.get(a).add(b);
            rev.get(b).add(a);
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()) - 1; //시작점
        t = Integer.parseInt(st.nextToken()) - 1; //도착점

        //s->t
        boolean[] visit = bfsEx(s, t, link);
        boolean[] visit2 = bfs(t, rev);

        //t->s
        boolean[] visit3 = bfsEx(t, s, link);
        boolean[] visit4 = bfs(s, rev);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (visit[i] && visit2[i] && visit3[i] && visit4[i]) {
                ans++;
            }
        }
        
        System.out.println(ans);
    }
}
