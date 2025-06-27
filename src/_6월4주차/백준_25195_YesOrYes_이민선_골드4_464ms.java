import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_25195_YesOrYes_이민선_골드4_464ms {
    static int n, m;
    static class Node {
        int u;
        boolean exist;
        List<Integer> children = new ArrayList<>();

        Node (int u){
            this.u = u;
        }

        public String toString(){
            return "(" + u + "," + exist + "," + children + ")";
        }
    }
    static Node[] graph;
    static int s;
    static boolean isPossible = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new Node[n + 1];

        for (int i=1;i<=n;i++){
            graph[i] = new Node(i);
        }

        for (int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].children.add(v);
        }

        s = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreElements()){
            int s = Integer.parseInt(st.nextToken());

            if (s == 1) {
                System.out.println("Yes");
                System.exit(0);
            }

            graph[s].exist = true;
        }

        dfs();
        System.out.println(isPossible ? "Yes" : "yes");
    }

    private static void dfs(){
        Stack<Integer> stack = new Stack<>();

        stack.push(1);

        while (!stack.isEmpty()){
            int cur = stack.pop();

            for (int next:graph[cur].children){
                if (graph[next].exist) continue;
                stack.push(next);
            }

            if (graph[cur].children.isEmpty()){
                isPossible = false;
                return;
            }
        }
    }
}
