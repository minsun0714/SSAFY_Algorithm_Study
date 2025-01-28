package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_12015_가장긴증가하는부분수열2_골드2_이민선_436ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int[] LIS = new int[n + 1];
        int idx = 0;
        for (int num: nums){
            // 마지막 원소보다 크면 LIS에 추가
            if (LIS[idx] < num){
                LIS[++idx] = num;
            }
            // 마지막 원소보다 작으면 LIS에서 "num과 가장 가깝지만 num보다는 큰 값"을 찾아서 대치
            else if (LIS[idx] > num){
                int nearestLarger = binarySearch(LIS, idx, num);
                LIS[nearestLarger] = num;
            }
        }
        System.out.println(idx);
    }
    public static int binarySearch(int[] LIS, int idx, int target){
        int s = 0;
        int e = idx;
        int nearestLarger = idx;
        while (s <= e){
            int mid = (s + e) / 2;

            if (LIS[mid] == target){
                return mid;
            } else if (LIS[mid] > target){
                e = mid - 1;
                nearestLarger = mid;
            } else {
                s = mid + 1;
            }
        }
        return nearestLarger;
    }
}
