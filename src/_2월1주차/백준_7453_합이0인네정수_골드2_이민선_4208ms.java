package _2월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_7453_합이0인네정수_골드2_이민선_4208ms {
    static int n;
    static int[] A;
    static int[] B;
    static int[] C;
    static int[] D;
    static int[][] board;
    static long[] AB;
    static long[] CD;
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];

        board = new int[4][n];

        board[0] = A;
        board[1] = B;
        board[2] = C;
        board[3] = D;

        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<4;j++){
                board[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        AB = new long[n * n];
        CD = new long[n * n];

        int idx = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                AB[idx++] = A[i] + B[j];
            }
        }
        Arrays.sort(AB);
        idx = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                CD[idx++] = C[i] + D[j];
            }
        }
        Arrays.sort(CD);

        int s = 0;
        int e = n * n - 1;

        while (s < n * n && e >= 0){
            if (AB[s] + CD[e] > 0) e--;
            else if (AB[s] + CD[e] < 0) s++;
            else {
                long countS = 1;
                while (s < n * n - 1 && AB[s] == AB[s + 1]){
                    s++;
                    countS++;
                }
                long countE = 1;
                while (e > 0 && CD[e] == CD[e - 1]){
                    e--;
                    countE++;
                }
                answer += countS * countE;
                s++;
            }
        }
        System.out.println(answer);
    }
}