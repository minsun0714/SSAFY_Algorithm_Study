import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 클래스
public class Softeer_나무공격_레벨2_김민섭_93ms {
	
	// 메인
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// [입력]
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// [입력]
		int[] arr = new int[N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int m = 0; m < M; m++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					arr[n]++;
				}
			}
		}
		
		// 공격 1
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken())-1;
		int end = Integer.parseInt(st.nextToken())-1;
		for (int n = start; n <= end; n++) {
			arr[n] = Math.max(0, arr[n]-1);
		}
		
		// 공격 2
		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken())-1;
		end = Integer.parseInt(st.nextToken())-1;
		for (int n = start; n <= end; n++) {
			arr[n] = Math.max(0, arr[n]-1);
		}
		
		// 출력
		int result = 0;
		for (int n = 0; n < N; n++) {
			result += arr[n];
		}
		System.out.println(result);
		
	} // 메인
	
} // 클래스