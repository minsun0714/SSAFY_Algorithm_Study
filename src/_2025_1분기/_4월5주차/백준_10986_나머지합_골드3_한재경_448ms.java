import java.io.*;
import java.util.*;

// 구현
public class 백준_10986_나머지합_골드3_한재경_448ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //수 개수
        int m = Integer.parseInt(st.nextToken()); //%m

        long ans = 0;

        int[] cnt = new int[m]; //이전에 해당 수로 나눠떨어진 구간 개수
        cnt[0]++; //0일 때 나눠떨어진 구간 1
        long sum = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            sum = (sum + a) % m; // 안전하게 나머지만 저장
            int mod = (int) sum;
            ans += cnt[mod];
            cnt[mod]++;
        }

        System.out.println(ans);
    }
}
