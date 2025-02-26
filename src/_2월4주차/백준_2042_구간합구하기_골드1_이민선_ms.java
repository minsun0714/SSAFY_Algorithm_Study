package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2042_구간합구하기_골드1_이민선_ms {
    static int n, m, k;
    static long[] nums;
    static long[] sums;
    static long
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nums = new long[n + 1];
        sums = new long[n + 1];
        for (int i=1;i<=n;i++){
            nums[i] = Integer.parseInt(br.readLine());
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i=0;i<m + k;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1){

            }
        }
    }
}
