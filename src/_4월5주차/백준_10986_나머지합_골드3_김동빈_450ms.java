package etc._4_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 누적합
public class Main_백준_10986_나머지합_ms {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long answer = 0;
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] aSum = new int[n + 1];
		int[] remainders = new int[m];
		remainders[0]++;
		
		for (int i = 1; i <= n; ++i) {
			int cur = Integer.parseInt(st.nextToken());
			aSum[i] = aSum[i - 1] + cur;
			aSum[i] %= m;
		}
		
		// 개수 세기
		for (int i = 1; i <= n; ++i) {
			int remainder = aSum[i];
			int preCount = remainders[remainder];
			
			answer += preCount;
			remainders[remainder]++;
		}
		
		System.out.println(answer);
	}
	
}
