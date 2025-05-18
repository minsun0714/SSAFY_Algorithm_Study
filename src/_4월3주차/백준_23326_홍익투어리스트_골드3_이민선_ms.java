import java.io.*;
import java.util.*;

public class 백준_23326_홍익투어리스트_골드3_이민선_ms {
    static int n, q;
    static TreeSet<Integer> tree = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++){
            int a = Integer.parseInt(st.nextToken());
            if (a == 1) tree.add(i);
        }

        StringBuilder sb = new StringBuilder();

        int cur = 0;
        for (int i=0;i<q;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 3) {
                if (tree.isEmpty()) sb.append(-1);
                else if (tree.ceiling(cur) == null) sb.append(n - cur + tree.first());
                else sb.append(tree.ceiling(cur) - cur);
                sb.append("\n");
                continue;
            }

            int b = Integer.parseInt(st.nextToken());
            if (a == 1){
                if (tree.contains(b - 1)) tree.remove(b - 1);
                else tree.add(b - 1);
            } else {
                cur = (cur + b) % n;
            }
        }
        System.out.println(sb);
    }
}
