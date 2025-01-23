import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 백준_14888_연산자끼워넣기_실버1_정석진_100ms {
	public static int resultMax;
	public static int resultMin;

	public static int[] num;
	public static int N;

	public static void backtrack(int cur, int cnt, int operpl, int opermin, int opermul, int operdiv) {
		if (cnt == N - 1) {
			resultMax = resultMax > cur ? resultMax : cur;
			resultMin = resultMin < cur ? resultMin : cur;
			return;
		}
		// 백트래킹
		if (operpl > 0) {
			backtrack(cur + num[cnt + 1], cnt + 1, operpl - 1, opermin, opermul, operdiv);
		}
		if (opermin > 0) {
			backtrack(cur - num[cnt + 1], cnt + 1, operpl, opermin - 1, opermul, operdiv);
		}
		if (opermul > 0) {
			backtrack(cur * num[cnt + 1], cnt + 1, operpl, opermin, opermul - 1, operdiv);
		}
		if (operdiv > 0) {
			backtrack(cur / num[cnt + 1], cnt + 1, operpl, opermin, opermul, operdiv - 1);
		}

	}

	public static void main(String[] args) throws IOException {
		// 입력을 받기 위한 BufferedReader 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		resultMax = Integer.MIN_VALUE;
		resultMin = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		int[] oper = new int[4]; // 0+ 1- 2* 3/
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}

		backtrack(num[0], 0, oper[0], oper[1], oper[2], oper[3]);
		sb.append(resultMax).append("\n").append(resultMin);
		System.out.println(sb.toString());
	}
}

