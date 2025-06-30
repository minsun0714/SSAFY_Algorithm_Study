package _2월4주차;

import java.io.*;
import java.util.*;

public class 백준_1644_소수의연속합_골드3_이민선_1812ms {
    static int n;
    static int answer;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i=2;i<=n;i++){
            if (isPrime(i)) {
                list.add(i);
            }
        }
        twoPointer();
        System.out.println(answer);
    }

    private static void twoPointer(){
        int s = 0, e = 0;
        int sum = 0;
        while (s < list.size()){
            if (sum == n) answer++;
            if (e == list.size() || sum > n) sum -= list.get(s++);
            else sum += list.get(e++);
        }
    }

    private static boolean isPrime(int num){
        if (num <= 1) return false;

        for (int i=2;i<=Math.sqrt(num);i++){
            if (num % i == 0) return false;
        }

        return true;
    }
}
