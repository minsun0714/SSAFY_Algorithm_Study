package _8월2째주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//116ms
public class 백준_15666_N과M12_실버2_한재경_백트래킹 {
    public static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //n개 수 중
        int m = Integer.parseInt(st.nextToken()); //m개 고르기
        Integer[] origin = new Integer[n]; //원본수열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
        }

        // 중복 없애기
        Set<Integer> set = new HashSet<>(Arrays.asList(origin));
        Integer[] nums = set.toArray(new Integer[0]);

        // nums에서 m개 고른 오름차순 수열 (중복가능) 개수
        Arrays.sort(nums);
        
        // now에 현재까지의 수열 저장, idx에 인덱스(이상만 탐색) 저장해서 dfs 돌리기
        // idx, idx+1 두가지 dfs 호출
        List<Integer> now = new ArrayList<>();
        dfs(nums, now, 0, m);
        System.out.println(result);
    }

    public static void dfs(Integer[] nums, List<Integer> now, int idx, int m) {
        if (now.size() >= m) {
            for (int i = 0; i < m; i++) {
                result.append(now.get(i)).append(" ");
            }
            result.append("\n");
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            now.add(nums[i]);
            dfs(nums, now, i, m);
            now.remove(now.size() - 1);
        }
    }
}
