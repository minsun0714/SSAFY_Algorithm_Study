import java.io.*;
import java.util.*;

// greedy, two pointer
public class Softeer_마이크로서버_레벨3_이민선_279ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] nums = new int[n];
            int idx = 0;
            int count300 = 0;
            while (st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());
                nums[idx++] = num;
                if (num == 300) count300++;
            }

            Arrays.sort(nums);

            int s = count300;
            int e = n - 1;

            long answer = 0;
            while (s <= e){
                // 300을 초과하는 서비스들끼리 최대한 쌍을 지음
                if (s != e && nums[s] + nums[e] <= 900) s++;
                    // 300 초과하는 서비스와 쌍을 못지으면 그때서야 300이랑 쌍을 지어야 함
                else if (nums[e] <= 600 && count300 > 0) count300--;
                // 300이랑도 쌍을 못지으면 서버 단독 할당
                e--;
                answer++;
            }
            System.out.println((count300 + 2) / 3 + answer);
        }
    }
}

