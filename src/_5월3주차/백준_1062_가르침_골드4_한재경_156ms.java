import java.io.*;
import java.util.*;

// 비트마스킹 + dfs
public class 백준_1462_가르침_골드4_한재경_156ms {
    static int n, k, ans = 0;
    static int[] wordMasks;
    static int bitmask = (1 << 'a'-'a')|(1 << 'n'-'a')|(1 << 't'-'a')|(1 << 'i'-'a')
            |(1 << 'c'-'a');

    static void dfs(int start, int count, int mask) {
        //글자수 다 채우면
        if (count >= k - 5) {
            int nowCount = 0;
            // 몇 개 단어 커버 가능한지
            for (int word : wordMasks) {
                if ((word & mask) == word) {
                    nowCount++;
                }
            }
            ans = Math.max(ans, nowCount);
            return;
        }
        // a부터 z까지 26개
        for (int i = start; i < 26; i++) {
            int now = 1 << i;
            //이전에 포함했으면
            if ((mask & now) > 0) {
                continue;
            }
            dfs(i + 1, count+1, mask | now);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 단어 개수
        k = Integer.parseInt(st.nextToken()); // 가르칠 글자 수
        wordMasks = new int[n]; //주어진 각 단어 비트마스크

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= 1 << c - 'a';
            }
            wordMasks[i] = mask;
        }
        //k가 5개 미만이면 0
        if (k < 5) {
            System.out.println(0);
        } else {
            dfs(0, 0, bitmask);
            System.out.println(ans);
        }
    }
}
