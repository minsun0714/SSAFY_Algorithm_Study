package _2월1주차;

import java.io.*;
import java.util.*;

public class 백준_17951_흩날리는시험지속에서내평점이느껴진거야_골드3_이민선_200ms {
    static int n, k;
    static int[] scores;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        scores = new int[n];
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreTokens()){
            scores[idx++] = Integer.parseInt(st.nextToken());
        }
        binarySearch();
        System.out.println(answer);
    }

    public static void binarySearch(){
        int s = 1, e = 0;
        for (int score:scores){
            e += score;
        }

        while (s <= e){
            int mid = (s + e) / 2;

            int count = 0;
            int sum = 0;
            for (int score: scores){
                sum += score;
                if (sum >= mid){
                    sum = 0;
                    count++;
                }
            }

            if (count >= k){
                s = mid + 1;
                answer = mid;
            } else {
                e = mid - 1;
            }

        }
    }
}
