package _3월4주차;

import java.io.*;
import java.util.StringTokenizer;

public class 백준_13397_구간나누기2_이민선_골드4_96ms {
    static int n, m;
    static int[] nums;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int s = 0;
        int e = 1;

        st = new StringTokenizer(br.readLine());

        nums = new int[n];

        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
            e = Math.max(e, nums[i]);
        }

        while (s <= e){
            int mid = (s + e) / 2;

            int count = 1;
            int min = nums[0];
            int max = nums[0];

            for (int i=0;i<n;i++){

                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
                if (max - min > mid) {
                    min = nums[i];
                    max = nums[i];
                    count++;
                }

            }

            if (count > m){
                s = mid + 1;
            } else {
                e = mid - 1;
                answer = mid;
            }
        }
        System.out.println(s);
    }
}