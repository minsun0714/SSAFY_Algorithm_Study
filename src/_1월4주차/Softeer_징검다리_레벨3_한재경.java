import java.io.*;
import java.util.*;

//가장 긴 증가하는 부분수열, dp, 125ms
public class Softeer_징검다리_레벨3_한재경 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] locks = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            locks[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n];
        
        //locks[i-1]까지 중 j < i인 dp[j] 중 max + 1 또는 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1; //초기화
            for (int j = 0; j < i; j++) {
                if (locks[j] < locks[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        System.out.println(result);
    }
}
