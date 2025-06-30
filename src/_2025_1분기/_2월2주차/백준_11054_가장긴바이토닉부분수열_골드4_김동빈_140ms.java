package etc._2_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// dp, 140ms
public class 백준_11054_가장긴바이토닉부분수열_골드4_김동빈_140ms {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] numbers = new int[n];
		for (int i = 0; i < n; ++i) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] increaseSequence = new int[n];
		int[] decreaseSequence = new int[n];
		
		Arrays.fill(increaseSequence, 1);
		Arrays.fill(decreaseSequence, 1);
		
		// 정방향 증가
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (numbers[j] < numbers[i]) {
					increaseSequence[i] = Math.max(increaseSequence[i], increaseSequence[j] + 1);
				}
			}
		}
		// 역방향 증가 (정방향 감소)
		for (int i = n - 1; i >= 0; --i) {
			for (int j = n - 1; j > i; --j) {
				if (numbers[j] < numbers[i]) {
					decreaseSequence[i] = Math.max(decreaseSequence[i], decreaseSequence[j] + 1);
				}
			}
		}
		
		// 정답 찾기
		int answer = 0;
		for (int i = 0; i < n; ++i) {
			
			int l = increaseSequence[i] + decreaseSequence[i]; 
			answer = Math.max(answer, l);
		}
		
		// 정답 출력 (중복되는 본인 제외)
		System.out.println(answer - 1);
		
	}
}
