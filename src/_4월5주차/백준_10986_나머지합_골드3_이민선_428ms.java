import java.io.*;
import java.util.*;

// 누적합, 수학
public class 백준_10986_나머지합_골드3_이민선_428ms {
    static int n;
    static int m;
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        long[] accSum = new long[n];
        int[] remainderCount = new int[m];

        st = new StringTokenizer(br.readLine());

        for (int i=0;i<n;i++){
            int num = Integer.parseInt(st.nextToken());
            accSum[i] = num;
            if (i > 0) accSum[i] += accSum[i - 1];
            int remainder = (int) (accSum[i] % m);
            remainderCount[remainder]++;
        }

        answer += remainderCount[0];

        for (int remainder: remainderCount){
            answer += selectTwoCount(remainder);
        }
        System.out.println(answer);
    }

    private static long selectTwoCount(int value){
        return (long) value * (value - 1) / 2;
    }
}
