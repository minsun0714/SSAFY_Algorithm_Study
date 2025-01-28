import java.io.*;
import java.util.*;

// 구현, 420ms
public class Softeer_우물안개구리_레벨3_한재경_420ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //n명
        int m = Integer.parseInt(st.nextToken()); //m개 관계
        Map<Integer, Integer> w = new HashMap<>(); //사람idx:힘
        boolean[] isNotTop = new boolean[n]; //각 사람이 탑이 아닌지

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            w.put(i, Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int aidx = Integer.parseInt(st.nextToken()) - 1;
            int bidx = Integer.parseInt(st.nextToken()) - 1;
            if (w.get(aidx) > w.get(bidx)) {
                isNotTop[bidx] = true;
            } else if (w.get(bidx) > w.get(aidx)) {
                isNotTop[aidx] = true;
            } else {
                isNotTop[aidx] = true;
                isNotTop[bidx] = true;
            }
        }
        
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (isNotTop[i] == false) {
                result++;
            }
        }        
        System.out.println(result);
    }
}
