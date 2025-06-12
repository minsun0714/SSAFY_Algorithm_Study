import java.io.*;
import java.util.*;

// 이분탐색
public class 백준_2295_세수의합_골드4_한재경_632ms {
    static List<Long> ab;

    // ab[x] = diff 되는 값 있는지
    static boolean bs(long diff) {
        int l = 0;
        int r = ab.size() - 1; // - 1하는 거 잊지 말기!!! 실수금지

        while (l <= r) {
            int mid = (l + r) / 2;
            if (ab.get(mid) == diff) {
                return true;
            } else if (ab.get(mid) > diff) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);

        // a + b = c - b 인 경우에 최대 c 찾기
        ab = new ArrayList<>(); // a + b 모음

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab.add(arr[i] + arr[j]);
            }
        }

        Collections.sort(ab);
        long ans = 0;

        for (int i = n - 1; i >= 0; i--) { //c. c가 최대일 때 바로 break 하려고
            for (int j = 0; j < i; j++) { //d.  c - d > 0 여야 하므로
                long diff = arr[i] - arr[j]; //c - d

                // ab[x] = diff 되는 c 있으면
                if (bs(diff)){
                    ans = arr[i];
                    break;
                }
            }
            if (ans > 0) {
                break;
            }
        }
        System.out.println(ans);
    }
}
