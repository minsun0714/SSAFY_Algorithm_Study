import java.io.*;
import java.util.*;

public class Softeer_통근버스출발순서검증하기_이민선_415ms {
    static int n;
    static int[] nums;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;

        for (int start = 0;start < n;start++){
            Stack<Integer> stack = new Stack<>();

            for (int i=start + 1;i<n;i++){
                stack.push(nums[i]);

                if (stack.peek() <= nums[start]){
                    stack.pop();
                    answer += stack.size();

                }
            }
        }
        System.out.println(answer);
    }
}
