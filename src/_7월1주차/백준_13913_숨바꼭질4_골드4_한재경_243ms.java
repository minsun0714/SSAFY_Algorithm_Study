import java.io.*;
import java.util.*;

//bfs. parent[] 에 직전 idx 메모! -> 최종에 역순회로 경로 찾기
public class 백준_13913_숨바꼭질4_골드4_한재경_243ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
//        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //수빈
        int k = Integer.parseInt(st.nextToken()); //동생
        if (k <= n) {
            System.out.println(n - k);
            for (int i = n; i >= k; i--) {
                sb.append(i + " ");
            }
            System.out.println(sb);
        } else {
            int MAX_NUM = k * 2 + 1;
            int[] dp = new int[MAX_NUM];
            Arrays.fill(dp, MAX_NUM);
            int[] parent = new int[MAX_NUM]; //i에 오기 직전 인덱스 메모: 경로추적용
            Arrays.fill(parent, -1);

            Queue<Integer> pq = new ArrayDeque<>(); //idx
            pq.add(n);
            dp[n] = 0;

            while (!pq.isEmpty()) {
                int now = pq.poll();
                if (now == k) {
                    System.out.println(dp[now]);
                    Stack<Integer> ans = new Stack<>();
                    while (true) {
                        if (now == -1) {
                            break;
                        }
                        ans.add(now);
                        now = parent[now];
                    }
                    while (!ans.isEmpty()) {
                        sb.append(ans.pop() + " ");
                    }
                    System.out.println(sb);
                    break;
                }
                if (now * 2 < MAX_NUM && dp[now] + 1 < dp[now * 2]) {
                    dp[now * 2] = dp[now] + 1;
                    pq.add(now * 2);
                    parent[now * 2] = now;
                }
                if (now + 1 < MAX_NUM && dp[now] + 1 < dp[now + 1]) {
                    dp[now + 1] = dp[now] + 1;
                    pq.add(now + 1);
                    parent[now + 1] = now;
                }
                if (now - 1 >= 0 && dp[now] + 1 < dp[now - 1]) {
                    dp[now - 1] = dp[now] + 1;
                    pq.add(now - 1);
                    parent[now - 1] = now;
                }
            }
        }
    }
}
