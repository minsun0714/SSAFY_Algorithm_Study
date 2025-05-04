// 누적합
// [ 532 ms | 222 mb ]
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        Map<Integer, Long> remainMap = new HashMap<>(); 
        tokens = new StringTokenizer(input.readLine());
        int prefixSum = 0; 
        for (int i = 0; i < n; i++) {
            int remain = Integer.parseInt(tokens.nextToken());
            prefixSum += remain;
            prefixSum %= m;
            remainMap.putIfAbsent(prefixSum, 0L);
            long value = remainMap.get(prefixSum);
            remainMap.put(prefixSum, value+1);
        }

        long count = 0;
        for(int key : remainMap.keySet()){
            long value = remainMap.get(key);
            value -= 1;
            count += value*(value+1)/2;
        }
        if(remainMap.containsKey(0))
            count += remainMap.get(0);
        System.out.println(count);
    }
}
