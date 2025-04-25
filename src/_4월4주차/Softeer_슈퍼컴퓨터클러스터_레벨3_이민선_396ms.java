import java.io.*;
import java.util.*;

// 이분 탐색
public class Softeer_슈퍼컴퓨터클러스터_레벨3_이민선_396ms {
    static int n;
    static long b;
    static int[] nums;
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());

        nums= new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        binarySearch();
        System.out.println(answer);
    }

    private static void binarySearch(){
        long s = 0;
        long e = nums[nums.length - 1] + (long) Math.pow(b, 0.5);

        while (s <= e){
            long mid = (s + e) / 2;
            long cost = 0;
            for (int num: nums){
                if (num <= mid) cost += Math.pow(mid - num, 2);
            }

            if (cost > b){
                e = mid - 1;
            } else {
                s = mid + 1;
                answer = mid;
            }
        }
    }
}