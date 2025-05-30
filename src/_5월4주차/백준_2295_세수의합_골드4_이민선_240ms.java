import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_2295_세수의합_골드4_이민선_240ms {
    static int n;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        for (int k=n - 1;k >= 0;k--){
            for (int x = 0;x < k;x++){
                int y = x;
                int z = k;

                while (y <= z){
                    if (nums[x] + nums[y] + nums[z] == nums[k]) {
                        System.out.println(nums[k]);
                        System.exit(0);
                    }
                    else if (nums[x] + nums[y] + nums[z] < nums[k]) y++;
                    else z--;
                }
            }
        }
    }
}
