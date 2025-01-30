// sliding window
package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2531_회전초밥_실버1_이민선_212ms {
    static int n;
    static int d;
    static int k;
    static int c;
    static int[] plates;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        plates = new int[n];
        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            plates[i] = Integer.parseInt(st.nextToken());
        }

        slidingWindow();
    }
    public static void slidingWindow(){
        int answer = 0;
        int s = 0;
        int e = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(c, 1);

        while (s < n){
            if (e < k){
                map.put(plates[e % n], map.get(plates[e % n]) == null ? 1 : map.get(plates[e % n]) + 1);
                e++;
                continue;
            }
            map.put(plates[e % n], map.get(plates[e % n]) == null ? 1 : map.get(plates[e % n]) + 1);
            if (map.get(plates[s % n]) == 1){
                map.remove(plates[s % n]);
            } else {
                map.put(plates[s % n], map.get(plates[s % n]) - 1);
            }
            answer = Math.max(answer, map.keySet().size());

            s++;
            e++;
        }
        System.out.println(answer);
    }
}
