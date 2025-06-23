import java.io.*;

public class 백준_2631_줄세우기_골드4_이민선_64ms {
    static int n;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        for (int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n];

        int max = 0;

        for (int i=0;i<n;i++){
            dp[i] = 1;
            for (int j=0;j<i;j++){
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }

        System.out.println(n - max);
    }
}
