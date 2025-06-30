import java.util.*;
import java.io.*;

//위상정렬
public class 백준_2252_줄세우기_골드3_한재경_500ms {
    static int n; //노드개수
    static int m; //간선개수
    static List<List<Integer>> links;
    static int[] in; //진입차수
    
    static List<Integer> sort() {
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>(); //정렬 결과
        
        //진입차수 0인 노드 큐에 넣기
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);
            for (int l : links.get(now)) {
                if (--in[l] == 0) { //진입차수 0인애들 큐에 넣기
                    q.add(l);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //노드개수
        m = Integer.parseInt(st.nextToken()); //간선개수
        links = new ArrayList<>();
        in = new int[n];

        for (int i = 0; i < n; i++) {
            links.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            links.get(a-1).add(b-1);
            in[b-1]++;
        }
        List<Integer> result = sort();
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append((i+1) + " ");
        }
        System.out.println(sb);
    }
}
