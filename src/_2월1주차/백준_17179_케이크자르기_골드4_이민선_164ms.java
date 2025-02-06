package _2월1주차;

import java.io.*;
import java.util.*;

public class 백준_17179_케이크자르기_골드4_이민선_164ms {
    static int n, m, l;
    static int[] pieces;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        pieces = new int[m + 2];
        pieces[0] = 0;
        pieces[m + 1] = l;
        for (int i=1;i<=m;i++){
            int piece = Integer.parseInt(br.readLine());
            pieces[i] = piece;
        }

        for (int i=0;i<n;i++){
            int q = Integer.parseInt(br.readLine());
            binarySearch(q);
        }
    }

    public static void binarySearch(int target){
        int s = 1;
        int e = l;

        while (s <= e){
            int mid = (s + e) / 2;

            int count = 0;
            int start = 0;
            for (int i=1;i<m + 2;i++){
                if (pieces[i] - pieces[start] >= mid) {
                    count++;
                    start = i;
                }
            }
            // 질문!!!!!
            // count == target일 때는 왜 if문에 들어갈까?
            // count가 마지막 원소까지 세어졌다면 문제가 없음.
            // 마지막 원소를 세지 못하고 for문이 끝난 경우, 사실상 count + 1 조각이 됨.
            // 이 때도 answer를 갱신해줘야 하지 않나?
            // 마지막 원소를 센 경우
            // count < target일 때 mid를 감소시켜야 하므로 ok
            // count == target일 때 answer를 갱신해줘야 하지 않나?
            // 마지막 원소를 못 센 경우
            // count < target일 때 조각 개수가 target과 같아질 가능성이 있음.
            // count == target일 때 조각 개수가 target보다 많아짐. 그럼 answer를 갱신해줘야 하지 않나?
            if (count <= target){
                e = mid - 1;
            } else {
                answer = mid;
                s = mid + 1;
            }
        }
        System.out.println(answer);
        answer = 0;
    }
}
