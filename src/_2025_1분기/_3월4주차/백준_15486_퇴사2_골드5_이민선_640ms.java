package _3월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_15486_퇴사2_골드5_이민선_640ms {
    static int n;
    static int[] tList;
    static int[] pList;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tList = new int[n];
        pList = new int[n];

        int[] dp = new int[n + 1];

        int idx = 0;

        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());

            tList[idx] = Integer.parseInt(st.nextToken());
            pList[idx] = Integer.parseInt(st.nextToken());

            idx++;
        }

        for (int i=n - 1;i>=0;i--){
            dp[i] = dp[i + 1];
            if (i + tList[i] > n) continue;
            dp[i] = Math.max(dp[i + tList[i]] + pList[i], dp[i]);
        }

        System.out.println(dp[0]);

    }
}
