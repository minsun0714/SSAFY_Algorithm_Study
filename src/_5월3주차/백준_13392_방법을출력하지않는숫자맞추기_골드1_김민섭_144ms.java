import java.io.*;
import java.util.*;

public class 백준_13392_방법을출력하지않는숫자맞추기_골드1_김민섭_144ms {
	
	// STATIC
	static int N;
	static String num;
	static String password;
	static int[][] dp;
	
	// [GET VALUE]
	private static int getValue(String string, int index, int rotate) {
		return ((string.charAt(index) - '0') + rotate) % 10;
	} // [GET VALUE]

	// [COUNTER]
	private static int counter(int index) {
		int left = getValue(num, index, 0) <= getValue(password, index, 0) ?
				getValue(password, index, 0) - getValue(num, index, 0) :
				getValue(password, index, 0) - getValue(num, index, 0) + 10;
		int right = (10 - left) % 10;
		dp[index][left] = counter(index + 1, left) + left;
		dp[index][0] = counter(index + 1, 0) + right;
		return Math.min(dp[index][left], dp[index][0]);
	}
	
	private static int counter(int index, int rotate) {
		
		if (index == N - 1) {
			return dp[index][rotate] = getValue(num, index, rotate) <= getValue(password, index, 0) ?
				getValue(password, index, 0) - getValue(num, index, rotate) :
				getValue(num, index, rotate) - getValue(password, index, 0);
		}
		
		if (dp[index][rotate] != -1) {
			return dp[index][rotate];
		}
		
		
		int left = getValue(num, index, rotate) <= getValue(password, index, 0) ?
				getValue(password, index, 0) - getValue(num, index, rotate) :
					getValue(password, index, 0) - getValue(num, index, rotate) + 10;
		int right = (10 - left) % 10;
		
		return dp[index][rotate] = Math.min(left + counter(index + 1, (rotate + left) % 10), right + counter(index + 1, rotate));
	} // [COUNTER]
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		num = br.readLine();
		password = br.readLine();
		dp = new int[N][10];
		
		for (int n = 0; n < N; n++) {
			Arrays.fill(dp[n], -1);
		}
		
		System.out.println(counter(0));
	} // [MAIN]
	
}