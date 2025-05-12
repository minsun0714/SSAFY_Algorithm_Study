import java.io.*;
import java.util.*;

public class Softeer_마이크로서버_레벨3_김민섭_1264ms {

    // [MAIN]
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        // TEST CASE
        for (int t = 0; t < T; t++) {
            int answer = 0;
            List<Integer> list = new ArrayList<>();
            
            int N = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            
            for (int n = 0; n < N; n++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp <= 600) {
                    list.add(temp);
                } else {
                    answer++;
                }
            }
            
            Collections.sort(list);

            // CALCULATE
            while (!list.isEmpty()) {
                if (list.size() == 1) {
                    answer++;
                    break;
                }
                
                int end = list.get(list.size() - 1);
                

                if (900 < list.get(0) + end) {
                    answer++;
                    list.remove(list.size() - 1);
                    continue;
                }

                if (end == 300 && 3 <= list.size()) {
                    list.remove(0);
                    list.remove(0);
                    list.remove(0);
                    answer++;
                    continue;
                }
                
                int left = 0;
                int right = list.size() - 2;
                int mid = (left + right) / 2;

                while (left <= right) {
                    mid = (left + right) / 2;
                    if (list.get(mid) + end <= 900) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                
                list.remove(list.size() - 1);
                list.remove(right);
                answer++;
            }
            
            // APPEND
            sb.append(answer).append("\n");
        } // TEST CASE

        // PRINT
        System.out.println(sb);
    } // [MAIN]
}
