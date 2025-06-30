package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_1477_휴게소세우기_골드4_이민선_64ms {
    static int n;
    static int m;
    static int l;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        nums = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        nums[n] = l;
        Arrays.sort(nums);

        binarySearch();
    }

    private static void binarySearch() {
        int answer = 0;
        int s = 1;
        int e = l - 2;

        while (s <= e){
            int mid = (s + e) / 2;

            int count = 0;

            int prev = 0;

            for (int cur: nums){
                count += (cur - prev - 1) / mid;
                prev = cur;
            }

            // m개보다 더 많이 지었다는 뜻은 mid가 최댓값이 아니라는 뜻 -> 답이 될 수 없음
            if (count > m){
                s = mid + 1;
                // m개보다 더 적게 지었거나 m개와 같다는 뜻은 mid가 최댓값일 수도 있다는 뜻 -> 답이 될 수도 있음.
            } else {
                e = mid - 1;
                answer = mid;

            }
        }
        System.out.println(answer);
    }
}
