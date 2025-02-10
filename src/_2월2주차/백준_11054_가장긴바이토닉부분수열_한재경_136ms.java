package org.example;

import java.io.*;
import java.util.*;

// LIS, DP (O(n^2) 가능, 각 인덱스별 LIS 구해야 하므로 DP가 더 적합)
public class 백준_11054_가장긴바이토닉부분수열_한재경_136ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] upDp = new int[n];
        Arrays.fill(upDp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 좌측에 arr[i]보다 작은애들 중 dp 갱신
                if (arr[j] < arr[i]) {
                    upDp[i] = Math.max(upDp[i], upDp[j] + 1);
                }
            }
        }

        int[] downDp = new int[n];
        Arrays.fill(downDp, 1);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                // 우측에 arr[i]보다 작은애들 중 dp 갱신
                if (arr[j] < arr[i]) {
                    downDp[i] = Math.max(downDp[i], downDp[j] + 1);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(upDp[i] + downDp[i], ans);
        }
        System.out.println(ans - 1);
    }
}
