import java.util.*;
import java.io.*;

//투포인터
public class 백준_2143_두배열의합_골드3_한재경_1054ms {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        long[] asum = new long[(n * (n-1)) / 2 + n];
        long[] nasum = new long[n]; //누적합
        int idx = 0;
        nasum[0] = a[0];

        for (int i = 1; i < n; i++) {
            nasum[i] += nasum[i - 1] + a[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == 0) {
                    asum[idx++] = nasum[j];
                } else {
                    asum[idx++] = nasum[j] - nasum[i-1];
                }
            }
        }
        long[] bsum = new long[(m * (m-1)) / 2 + m];
        long[] nbsum = new long[m]; //누적합
        idx = 0;
        nbsum[0] = b[0];
        for (int i = 1; i < m; i++) {
            nbsum[i] += nbsum[i - 1] + b[i];
        }
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                if (i == 0) {
                    bsum[idx++] = nbsum[j];
                } else {
                    bsum[idx++] = nbsum[j] - nbsum[i-1];
                }
            }
        }

        Arrays.sort(asum);
        Arrays.sort(bsum);

        int l = 0;
        int r = bsum.length-1;
        long cnt = 0;

        while (l < asum.length && r >= 0) {
            if (asum[l] + bsum[r] == t) {
                //asum, bsum 끝까지
                long an = asum[l], bn = bsum[r];
                int ac = 0, bc = 0;
                while (l < asum.length && an == asum[l]) {
                    l++;
                    ac++;
                }
                while (r >= 0 && bn == bsum[r]) {
                    r--;
                    bc++;
                }
                cnt += (long) ac * bc;
            } else if (asum[l] + bsum[r] < t) {
                l++;
            } else {
                r--;
            }
        }
        System.out.println(cnt);
    }
}
