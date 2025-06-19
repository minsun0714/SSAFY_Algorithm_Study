import java.util.*;
import java.io.*;

public class 백준_10830_행렬제곱_골드4_한재경_120ms {
    static int n;

    static int[][] matrixPow(int[][] arr, long b) { // b제곱
        if (b == 1) {
            //1000으로 나눈 나머지
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] %= 1000;
                }
            }
            return arr;
        }

        // 제곱 반띵
        // a^b = a^((b/2)*(b/2)) = halfMul
        int[][] half = matrixPow(arr, b / 2);

        // 반띵제곱을 행렬곱
        int[][] halfMul = matrixMul(half, half);

        //짝수제곱의 경우
        if (b % 2 == 0) {
            return halfMul;
        }

        //홀수제곱의 경우: 자기자신 한 번 더 행렬곱
        return matrixMul(halfMul, arr);
    }

    // 행렬곱
    static int[][] matrixMul(int[][] a, int[][] b) {
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // long 계산값 누적해서 1000으로 나눠야 함!
                long now = 0;
                for (int k = 0; k < n; k++) {
                    now += (a[i][k] * b[k][j]);
                }
                sum[i][j] += now % 1000;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
//        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] =  Integer.parseInt(st.nextToken());
            }
        }

        int[][] ans = matrixPow(arr, b);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(ans[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
