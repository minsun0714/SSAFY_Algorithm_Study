import java.io.*;
import java.util.*;

// 경로 역추적
public class Softeer_출퇴근길_레벨3_김민섭_911ms {
    static List<List<Integer>> link;
    static List<List<Integer>> linkRev;
    
    static boolean[] visited;
    
    static Set<Integer> startSet;
    static Set<Integer> startSetRev;
    static Set<Integer> endSet;
    static Set<Integer> endSetRev;
    
    // [Main]
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        link = new ArrayList<>();
        linkRev = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            link.add(new ArrayList<>());
            linkRev.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            link.get(start).add(end);
            linkRev.get(end).add(start);
        }

        st = new StringTokenizer(br.readLine(), " ");
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        startSet = new HashSet<>();
        visited = new boolean[N + 1];
        visited[S] = true;
        pathFinder(S, T, false, startSet);

        startSetRev = new HashSet<>();
        visited = new boolean[N + 1];
        visited[S] = true;
        pathFinder(S, 0, true, startSetRev);

        startSet.retainAll(startSetRev);

        endSet = new HashSet<>();
        visited = new boolean[N + 1];
        visited[T] = true;
        pathFinder(T, S, false, endSet);

        endSetRev = new HashSet<>();
        visited = new boolean[N + 1];
        visited[T] = true;
        pathFinder(T, 0, true, endSetRev);

        endSet.retainAll(endSetRev);

        startSet.retainAll(endSet);
        int answer = startSet.size() - 2;
        System.out.println(answer);
    } // [/Main]

    // [Path Finder]
    private static void pathFinder(int start, int end, boolean isRev, Set<Integer> set) {
        set.add(start);
        
        if (start == end) {
            return;
        }

        for (int next : !isRev ? link.get(start) : linkRev.get(start)) {
            if (!visited[next]) {
                visited[next] = true;
                pathFinder(next, end, isRev, set);
            }
        }
    } // [/Path Finder]
}
