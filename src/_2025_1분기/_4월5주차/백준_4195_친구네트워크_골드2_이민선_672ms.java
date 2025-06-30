import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 백준_4195_친구네트워크_골드2_이민선_672ms {
    static int n;
    static Map<String, String> parent;
    static Map<String, Integer> count;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        for (int i=0;i<n;i++){
            int f = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            count = new HashMap<>();

            for (int j=0;j<f;j++){
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                parent.putIfAbsent(a, a);
                parent.putIfAbsent(b, b);

                count.putIfAbsent(a, 1);
                count.putIfAbsent(b, 1);

                union(a, b);

                sb.append(count.get(find(a))).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static String find(String x){
        if (!x.equals(parent.get(x))){
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    private static void union(String a, String b){
        a = find(a);
        b = find(b);

        if (!a.equals(b)){
            parent.put(b, a);
            count.put(a, count.get(a) + count.get(b));
        }
    }
}
