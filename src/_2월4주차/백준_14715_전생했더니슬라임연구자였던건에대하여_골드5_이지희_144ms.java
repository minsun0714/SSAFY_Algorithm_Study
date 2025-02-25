package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_14715_전생했더니슬라임연구자였던건에대하여_골드5_이지희_144ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] notPrime = new boolean[N+1]; // true : not prime , false : prime

        int powerSum = 0;

        int temp = N;
        for(int p=2; p<=N; p++) {
            if(temp == 1) break; // 소인수 분해 끝
            if(notPrime[p]) continue; // p가 소수가 아니면 건너 띔

            // p가 소수라면 에라토스테네스의 체에 따라 배수를 지움
            int k = N/p ;
            for(int i=1; i<=k; i++) {
                notPrime[p*i] = true;
            }

            int power = 0; // 지수
            while(true) {
                if(temp % p != 0) break; // 안나눠떨어지면 끝

                power ++;
                temp /= p;
            }

            powerSum += power; // 지수의 함
        }

        int cnt = 0;
        while(powerSum > 1) {
            if(powerSum % 2 != 0) powerSum ++;  // 짝수로 맞춰버려

            cnt ++;
            powerSum /= 2;
        }

        System.out.println(cnt);
        br.close();
    }

}
