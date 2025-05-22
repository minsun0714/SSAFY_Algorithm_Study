import java.io.*;
import java.util.*;

public class Main_2295_세수의합_골드4_288ms {
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] array = new int[N];
		
		for (int n = 0; n < N; n++) {
			array[n] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(array);
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum = array[i] + array[j];
				if (sum <= 200_000_000) {
					map.put(sum, 1);
				}
			}
		}
		
		l : for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				if (map.get(array[i] - array[j]) != null) {
					System.out.println(array[i]);
					break l;
				}
			}
		}
	} // [MAIN]
	
}