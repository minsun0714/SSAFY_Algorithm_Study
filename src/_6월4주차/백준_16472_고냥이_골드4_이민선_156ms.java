import java.io.*;
import java.util.*;

public class 백준_16472_고냥이_골드4_이민선_156ms {
    static int n;
    static String strInput;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        strInput = br.readLine();

        Map<Character, Integer> map = new HashMap<>();
        int answer = 0;

        int l = 0;

        for (int r=0;r<strInput.length();r++){
            char rc = strInput.charAt(r);
            while (!map.containsKey(rc) && map.size() == n){
                char lc = strInput.charAt(l);
                map.put(lc, map.get(lc) - 1);
                if (map.get(lc) == 0) map.remove(lc);
                l++;
            }

            int initialValue = map.getOrDefault(rc, 0);
            map.put(rc, initialValue + 1);
            answer = Math.max(answer, r - l + 1);
        }

        System.out.println(answer);
    }
}
