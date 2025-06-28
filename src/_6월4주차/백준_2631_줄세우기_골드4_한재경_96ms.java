import java.io.*;
import java.util.*;

//dp, 가장 긴 증가하는 부분수열
public class 백준_2631_줄세우기_골드4_한재경_96ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 증가수열 제외 전부 옮겨야 함!
        int[] dp = new int[n]; //i번째까지 증가수열 길이
        Arrays.fill(dp, 1);
        int ans = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(n - ans);
    }
}
