package _4월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_20366_같이눈사람만들래_골드3_이민선_312ms {
    static int n;
    static int[] nums;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreTokens()){
            nums[idx++] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        for (int i=0;i<=n - 4;i++){
            for (int j=n - 1;j >= i + 3;j--){
                slidingWindow(i, j);
            }
        }
        System.out.println(answer);
    }

    private static void slidingWindow(int i, int j) {
        int s = i + 1;
        int e = j - 1;

        while (s < e){
            int a = nums[i] + nums[j];
            int b = nums[s] + nums[e];


            if (a > b) s++;
            else if (a < b) e--;
            else {
                answer = 0;
                return;
            }
            answer = Math.min(answer, Math.abs(a - b));
        }
    }
}

