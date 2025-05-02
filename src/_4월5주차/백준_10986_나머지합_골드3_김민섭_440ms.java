import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_10986_나머지합_골드3_김민섭_440ms {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] sum = new int[N+1];
		int[] counter = new int[M];
		
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			sum[i] = (sum[i-1] + Integer.parseInt(st.nextToken()))%M;
			counter[sum[i]]++;
		}
		
		long result = counter[0];
		for (int i = 0; i < counter.length; i++) {
			result += func(counter[i]);
		}
		
//		System.out.println(Arrays.toString(sum));
//		System.out.println(Arrays.toString(counter));
		System.out.println(result);
		
	}
	
	static long func(int n) {
		if (n < 1) {
			return 0;
		}
		return n-1 + func(n-1);
	}
	
}