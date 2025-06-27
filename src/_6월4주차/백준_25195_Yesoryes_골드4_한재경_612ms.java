import java.io.*;
import java.util.*;

//dfs
public class 백준_25195_Yesoryes_골드4_한재경_612ms {
    static boolean[] visited;
    static List<List<Integer>> links;
    static Set<Integer> sArr;
    static String ans;

    static void dfs(int x) {
        //연결된 링크 없으면 성공
        boolean flag = true;

        for (int nxt : links.get(x)) {
            //방문 전이면서 팬클럽 없는 경우
            if (!visited[nxt] && !sArr.contains(nxt)) {
                dfs(nxt); //진행 가능
            }
            flag = false;
        }
        if (flag) {
            ans = "yes";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
//        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //정점수
        int m = Integer.parseInt(st.nextToken()); //간선수
        links = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            links.get(a).add(b);
        }

        int s = Integer.parseInt(br.readLine()); //출발점 개수
        sArr = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) {
            sArr.add(Integer.parseInt(st.nextToken()) - 1);
        }

        //s지점 안거치고 끝까지 갈 수 있는 경우 존재 유무
        visited = new boolean[n];
        visited[0] = true; //시작점 초기화
        ans = "Yes";
        if (sArr.contains(0)) {
            System.out.println("Yes");
        } else {
            dfs(0);
            System.out.println(ans);
        }
    }
}
