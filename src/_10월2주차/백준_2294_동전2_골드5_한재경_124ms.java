import java.io.*;
import java.util.*;

//dp
public class 백준_2294_동전2_골드5_한재경_124ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //동전 종류
        int k = Integer.parseInt(st.nextToken()); //원하는 동전합
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        // dp[i] = 금액 i를 만들기 위한 최소 동전 개수
        int[] dp = new int[k + 1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;

        //점화식: 마지막에 특정 coin 추가
        //동전 순서는 결과에 영향x
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) { //마지막에 해당 coin 추가해 i원 만들기
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        
        if (dp[k] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}
