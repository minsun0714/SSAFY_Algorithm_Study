package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_16401_과자나눠주기_실버2_이민선_864ms {
    static int m, n;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nums = new int[n];

        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        binarySearch();
    }

    public static void binarySearch(){
        long s = 1;
        long e = nums[n - 1];
        long answer = 0;

        while (s <= e){
            long mid = (s + e) / 2;

            long count = 0;
            for (int i=0;i<n;i++){
                count += nums[i] / mid;
            }

            if (count >= m) {
                s = mid + 1;
                answer = mid;
            } else {
                e = mid - 1;
            }

        }
        System.out.println(answer);
    }
}
