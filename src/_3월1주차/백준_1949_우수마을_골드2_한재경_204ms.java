import java.util.*;
import java.io.*;

//dp + dfs + tree
public class 백준_1949_우수마을_골드2_한재경_204ms {
    static List<List<Integer>> links;
    static int[][] dp;
    static int[] towns;

    static void dfs(int now, int parent) { //양방향 관계이므로 parent제외
        for (int v : links.get(now)) {
            if (v == parent) {
                continue;
            }
            dfs(v, now);
            //"종단노드"부터 now번째까지 선정완료 후 최대sum
            //이때 여러 자식노드의 sum값 모을 필요 있어서 +=으로 계산!
            //now선택x
            dp[now][0] += Math.max(dp[v][0], dp[v][1]);
            //now선택o
            dp[now][1] += dp[v][0];
        }
        dp[now][1] += towns[now];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //마을 개수
        towns = new int[n]; //각 마을 주민수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            towns[i] = Integer.parseInt(st.nextToken());
        }
        links = new ArrayList<>(); //i번째
        for (int i = 0; i < n; i++) {
            links.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; //번호 -> idx변환
            int b = Integer.parseInt(st.nextToken()) - 1;
            links.get(a).add(b);
            links.get(b).add(a);
        }
        //"종단노드"부터 i번째까지 선정완료 후 최대sum
        dp = new int[n][2]; //0:i번째가 우수마을x, 1:i번째가 우수마을o

        dfs(0, -1);
        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }
}
