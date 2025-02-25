package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 백준_1644_소수의연속합_골드3_이지희_192ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] notPrime = new boolean[N+1];
        List<Integer> primes = new ArrayList<>();

        for(int p=2; p<=N; p++) {
            if(notPrime[p]) continue;

            primes.add(p);

            int k = N/p ;
            for(int i=1; i<=k; i++) {
                notPrime[p*i] = true;
            }
        }

        int result = 0;


        for(int i = primes.size() - 1 ; i >= 0 ; i--) {
            int sum = 0;
            int temp = i;

            while(temp >= 0) {
                sum += primes.get(temp);

                if(sum == N) {
                    result ++;
                    break;
                }

                if(sum > N) break;

                temp --; // 다음꺼 더하러
            }
        }

        System.out.println(result);
        br.close();
    }

}
