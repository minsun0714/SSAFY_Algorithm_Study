package _4월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_20366_같이눈사람만들래_골드3_이민선_840ms {
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

        for (int i=n - 1;i>=3;i--){
            for (int j=i;j>=2;j--){
                if (i == j) continue;
                slidingWindow(j, i);
            }
        }
        System.out.println(answer);
    }

    private static void slidingWindow(int i, int j) {
        int s = 0;
        int e = 1;

        while (s < i && e < j){
            if (s == e) {
                e++;
                continue;
            }
            if (s == j){
                s++;
                continue;
            }
            if (e == i){
                e++;
                continue;
            }
            int a = nums[s] + nums[j];
            int b = nums[e] + nums[i];

            answer = Math.min(answer, (Math.abs(a - b)));
            if (a > b) e++;
            else if (a < b) s++;
            else {
                answer = 0;
                return;
            }
        }
    }
}
