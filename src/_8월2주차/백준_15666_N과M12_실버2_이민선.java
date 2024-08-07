package _8월2주차;


import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

// 백트래킹, 1156 ms
public class 백준_15666_N과M12_실버2_이민선 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] nums = new int[n];

        for (int i=0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        bruteForce(nums, n, m, new Stack<>(), 0);
    }

    public static void bruteForce(int[] nums, int n, int m, Stack<Integer> selected, int depth){
        if (depth == m) {
            for (int num : selected){
                System.out.printf("%d ", num);
            }
            System.out.println();
            return;
        }

        for (int i=0;i<n;i++){
            if (i > 0 && (nums[i] == nums[i - 1])) continue;
            if (!selected.empty() && nums[i] < selected.peek()) continue;
            selected.push(nums[i]);
            bruteForce(nums, n, m, selected, depth + 1);
            selected.pop();
        }
    }
}
