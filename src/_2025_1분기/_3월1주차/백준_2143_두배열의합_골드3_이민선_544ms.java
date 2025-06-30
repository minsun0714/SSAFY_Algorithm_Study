package _3월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2143_두배열의합_골드3_이민선_544ms {
    static int t;
    static int n, m;
    static int[] A;
    static int[] B;
    static long[] subA;
    static long[] subB;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[n];
        int idx = 0;
        while (st.hasMoreTokens()){
            A[idx++] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        B = new int[m];
        st = new StringTokenizer(br.readLine());
        idx = 0;
        while (st.hasMoreTokens()){
            B[idx++] = Integer.parseInt(st.nextToken());
        }

        subA = new long[n * (n + 1) / 2];
        subB = new long[m * (m + 1) / 2];

        idx = 0;
        for (int s=0;s<n;s++){
            long sum = 0;
            for (int e=s;e<n;e++){
                sum += A[e];
                subA[idx++] = sum;
            }
        }

        idx = 0;
        for (int s = 0;s<m;s++){
            long sum = 0;
            for (int e=s;e<m;e++){
                sum += B[e];
                subB[idx++] = sum;
            }
        }

        Arrays.sort(subA);
        Arrays.sort(subB);
        long answer = 0;
        int s = 0;
        int e = subB.length - 1;
        while (s < subA.length && e >= 0){
            long sa = subA[s], sb = subB[e];
            long sum = sa + sb;
            if (sum == t){
                long i = 0, j = 0;
                while (s < subA.length && subA[s] == sa){
                    i++;
                    s++;
                }
                while (e >= 0 && subB[e] == sb){
                    j++;
                    e--;
                }
                answer += i * j;
            }

            else if (sum < t) s++;
            else e--;
        }
        System.out.println(answer);

    }
}
