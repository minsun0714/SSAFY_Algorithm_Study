import java.io.*;
import java.util.*;

public class Softeer_슈퍼컴퓨터클러스터_레벨3_김민섭_277ms {
    
    // STATIC
    static final long BIGINT = 2_000_000_000;
    
    // [MAIN]
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        st = new StringTokenizer(br.readLine(), " ");
        
        long[] computers = new long[N];
        for (int n = 0; n < N; n++) {
            computers[n] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(computers);
        
        long maximum = computers[0];

        long left = 0;
        long right = BIGINT;
        long mid = (left + right) / 2;
        
        ml : while (left <= right) {
            int index = 0;
            long budget = 0;

            while (index < N && computers[index] < mid) {
                budget += (mid - computers[index]) * (mid - computers[index]);
                index++;
                
                if (B < budget) {
                    break;
                }
            }

            if (B < budget) {
                right = mid - 1;
                mid = (left + right) / 2;
                continue;
            }
            
            maximum = Math.max(maximum, mid);
            left = mid + 1;
            mid = (left + right) / 2;
        }

        System.out.println(maximum);
    } // [MAIN]
    
}
