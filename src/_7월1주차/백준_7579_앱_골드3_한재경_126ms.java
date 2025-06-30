import java.io.*;
import java.util.*;

//dp
public class 백준_7579_앱_골드3_한재경_126ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
//        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //앱개수
        int m = Integer.parseInt(st.nextToken()); //m메모리 확보
        int[] act = new int[n]; //활성화 앱 메모리
        int[] inAct = new int[n];// 비활성화 비용

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            act[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inAct[i] = Integer.parseInt(st.nextToken());
        }

        int s = Arrays.stream(inAct).sum(); //총 활성화 메모리

        //합 m되는 act 메모리, 최소 inAct 비용 구하기
        //메모리: 무게, 비용: 가치
        int[] dp = new int[s + 1]; //비용 i일때 최소메모리
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int mem = act[i];
            int cost = inAct[i];
            for (int j = s; j - cost >= 0; j--) {
                //해당 앱 비용 포함/안포함 경우 메모리 갱신
                if (dp[j - cost] != -1) {
                    dp[j] = Math.max(dp[j - cost] + mem, dp[j]);
                }
            }
        }
        for (int i = 0; i <= s; i++) {
            if (dp[i] >= m) {
                System.out.println(i);
                break;
            }
        }

    }
}
