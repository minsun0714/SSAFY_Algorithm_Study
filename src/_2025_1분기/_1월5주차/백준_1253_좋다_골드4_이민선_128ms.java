package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_1253_좋다_골드4_이민선_128ms {
    static int n;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreElements()){
            nums[idx++] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int answer = 0;
        for (int i=0;i<n;i++){
            boolean isGood = slidingWindow(i);
            if (isGood) answer++;
        }
        System.out.println(answer);
    }

    public static boolean slidingWindow(int targetIdx){
        int s = 0;
        int e = n - 1;
        int target = nums[targetIdx];
        while (s < e){
            if (s == targetIdx) s++;
            if (e == targetIdx) e--;

            if (s == e) break;

            int sum = nums[s] + nums[e];

            if (sum == target){
                return true;
            } else if (sum > target){
                e--;
            } else {
                s++;
            }

        }
        return false;
    }
}
