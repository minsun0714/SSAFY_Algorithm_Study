// 그리디
package _2월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2828_사과담기게임_실버5_이민선_68ms {
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, m;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int j = Integer.parseInt(br.readLine());

        int min = 1;
        int max = m;
        for (int i=0;i<j;i++){
            int appleDrop = Integer.parseInt(br.readLine());
            if (appleDrop < min){
                answer += min - appleDrop;
                min = appleDrop;
                max = appleDrop + m - 1;
            } else if (appleDrop > max){
                answer += appleDrop - max;
                max = appleDrop;
                min = appleDrop - m + 1;
            }
        }
        System.out.println(answer);
    }
}
