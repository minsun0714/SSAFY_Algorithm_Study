import java.io.*;
import java.util.*;

public class Softeer_연탄의크기_레벨2_한재경 {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int maxr = 0;
        int m = 0;
        for (int r = 2; r <= 100; r++) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] % r == 0) {
                    cnt++;
                }
            }
            if (cnt > m) {
                maxr = r;
                m = cnt;
            }
            if (cnt == n) {
                break;
            }
        }
        System.out.println(m);
    }
}
