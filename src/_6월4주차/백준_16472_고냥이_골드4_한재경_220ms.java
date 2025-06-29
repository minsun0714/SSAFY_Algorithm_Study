import java.io.*;
import java.util.*;

// 투포인터
public class 백준_16472_고냥이_골드4_한재경_220ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int len = s.length();

        Map<Character, Integer> map = new HashMap<>(); //각 문자 누적합
        int cnt = 1; //초기에 하나만 포함
        int ans = 1; //n 안넘는 최대 길이
        map.put(s.charAt(0), 1);

        int l = 0;
        int r = 0;

        while (l <= r) {
            if (cnt > n) {
                char lc = s.charAt(l);
                map.put(lc, map.get(lc) - 1); //l하나 빼기
                if (map.get(lc) == 0) {
                    map.remove(lc);
                    cnt--; //개수 하나 줄이기
                }
                l++;
            } else {
                ans = Math.max(ans, r - l + 1);
                r++;
                if (r == len) {
                    break;
                }
                char rc = s.charAt(r);
                if (!map.containsKey(rc)) { //없었으면
                    cnt++;
                }
                map.put(rc, map.getOrDefault(rc, 0) + 1);
            }
        }
        System.out.println(ans);
    }
}
