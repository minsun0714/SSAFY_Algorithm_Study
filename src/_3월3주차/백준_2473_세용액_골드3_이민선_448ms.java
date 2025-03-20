package _3월3주차;

import java.io.*;
import java.util.*;

public class 백준_2473_세용액_골드3_이민선_448ms {
    static int n;
    static long[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        nums = new long[n];

        int idx = 0;
        while (st.hasMoreTokens()){
            nums[idx++] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        long sum = Long.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<n - 2;i++){
            long a = nums[i];

            int s = i + 1, e = n - 1;
            while (s < e){
                if (Math.abs(a + nums[s] + nums[e]) < sum) {
                    sb = new StringBuilder();
                    sb.append(a).append(" ").append(nums[s]).append(" ").append(nums[e]);
                    sum = Math.abs(a + nums[s] + nums[e]);
                }

                if (a + nums[s] + nums[e] > 0) e--;
                else s++;
            }
        }
        System.out.println(sb);
    }
}
