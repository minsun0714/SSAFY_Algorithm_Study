import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 다른 사람 풀이 참고
 * 
 * Set을 사용하지 않고 중복 수열을 제외하기
 * 
 * 숫자 배열 nums, 수열 배열 answer이 있을 때
 * 중복된 경우를 answer을 보고 찾으려 한게 문제였다.
 * 정렬해놨으니 nums에서 이전 값과 다를 때만 수열에 넣으면 되는 문제였는데!
 */

//백트래킹, 144ms
public class 백준_15666_N과M12_실버2_조준희 {
	static int[] answer;
	static int N;
	static int M;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		answer = new int[M];
		nums = new int[N];
		
		for(int i = 0; i<N; i++) {
			nums[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		
		dfs(0, 0);
		System.out.print(sb);
	}
	
	public static void dfs(int start, int cnt) {
		if(cnt>=M) {
			for(int num : answer) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = start; i<N; i++) {
			//참고 부분
			if(i==0 ||nums[i-1]!=nums[i]) {
				answer[cnt]=nums[i];
				dfs(i, cnt+1);
			}
		}
	}
	
}
