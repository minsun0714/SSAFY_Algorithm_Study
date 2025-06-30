import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 백준_11054_가장긴바이토닉부분수열_골드4_김민섭_416ms {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 길이
		int N = Integer.parseInt(br.readLine());
		
		// 수열
		int[] arr = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 좌측 탐색
		Map<Integer, Integer> leftMaxMap = new HashMap<>();
		leftMaxMap.put(0, 0);

		int[] left = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int curr = arr[i];
			for (int n = curr - 1; n >= 0; n--) {
				if (leftMaxMap.get(n) != null) {
					if (leftMaxMap.get(curr) != null) {
						left[i] = Math.max(leftMaxMap.get(curr), leftMaxMap.get(n) + 1);
						leftMaxMap.put(curr, left[i]);
					} else {
						left[i] = leftMaxMap.get(n) + 1;
						leftMaxMap.put(curr, left[i]);
					}
				}
			}
		}
		
		int answer = 1;
		
		// 우측 탐색
		Map<Integer, Integer> rightMaxMap = new HashMap<>();
		rightMaxMap.put(0, 0);
		
		int[] right = new int[N + 1];
		for (int i = N; i >= 1; i--) {
			int curr = arr[i];
			for (int n = curr - 1; n >= 0; n--) {
				if (rightMaxMap.get(n) != null) {
					if (rightMaxMap.get(curr) != null) {
						right[i] = Math.max(rightMaxMap.get(curr), rightMaxMap.get(n) + 1);
						rightMaxMap.put(curr, right[i]);
					} else {
						right[i] = rightMaxMap.get(n) + 1;
						rightMaxMap.put(curr, right[i]);
					}
					answer = Math.max(answer, left[i] + right[i]- 1);
				}
			}
		}
		
		System.out.println(answer);
	}
}