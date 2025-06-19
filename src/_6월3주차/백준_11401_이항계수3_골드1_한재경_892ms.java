import java.util.*;
import java.io.*;

// "나눗셈의 모듈로 연산" 유형: 역원 구해야 함!
// 모듈로 연산에서는 나눗셈 연산 불가(소숫점 없음)이기 때문!
// k의 역원 = k^(mod-2)
public class 백준_11401_이항계수3_골드1_한재경_892ms {
    static int MOD = 1000000007;
    //n / k
    //n/k * n-1/k-1 * n-k+1/1 ... k번
    static int div(long n, long k) {
        if (k == 0) {
            return 1; //nC0 = 1
        }

        long sum = 1;

        while (k > 0) {
            //원래는 sum = (sum * n / k) % mod
            //나눗셈 모듈로이므로 역원 구해서 각각 계산!
            //(sum * n % mod) * (sum * k역원 % mod)

            // 1) 분자에 n 곱하고 mod
            sum = (sum * n) % MOD;
            // 2) 분모 k의 역원 곱하고 mod
            long newK = modPow(k, MOD - 2); //k의 역원 = k^(mod-2) !!!
            sum = (sum * newK) % MOD;

            n--;
            k--;
        }
        return (int) sum;
    }

    // a^p % MOD
    static long modPow(long a, long p) {
        long result = 1;
        while (p > 0) {
            // 지수가 홀수면 한 번 더 곱해 주고
            if (p % 2 == 1) {
                result = (result * a) % MOD;
            }
            // a를 제곱하고
            a = (a * a) % MOD;
            // 지수를 절반으로 줄인다
            p /= 2;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
//        Integer n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        k = Math.min(k, n-k);

        //nCk
        //(n*n-1 ..(n-k + 1)) / k!
        //6C4 = 6*5*4*3/4*3*2 = 6*5/2*1
        System.out.println(div(n, k));
    }
}
