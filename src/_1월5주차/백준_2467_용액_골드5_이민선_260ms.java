package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2467_용액_골드5_이민선_260ms {
    static int n;
    static int[] nums;
    static long answer1;
    static long answer2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        nums = new int[n];
        while (st.hasMoreTokens()){
            nums[idx++] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        slidingWindow();
        System.out.println(answer1 + " " + answer2);
    }
    public static void slidingWindow(){
        long target = 2_000_000_000;
        int s = 0;
        int e = n - 1;


        while (s < e){
            int sum = nums[s] + nums[e];

            if (Math.abs(sum) <= target){
                target = Math.abs(sum);
                answer1 = nums[s];
                answer2 = nums[e];

            }

            if (Math.abs(nums[s]) > Math.abs(nums[e])){
                s += 1;
            } else {
                e -= 1;
            }
        }
    }
}