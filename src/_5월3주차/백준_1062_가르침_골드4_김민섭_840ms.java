import java.io.*;
import java.util.*;

public class 백준_1062_가르침_골드4_김민섭_840ms {
	
	// STATIC
	static long[] bin;
	static int maximum;
	static boolean[] visited;
	static int counter;
	static int N;
	static int K;
	static long[] word;
	static long temp;
	// [FUNC]
	private static void func(int index) {
		if (K == 0) {
			int count = 0;
			for (int n = 0; n < N; n++) {
				if ((temp & word[n]) == word[n]) {
					count++;
				}
			}
			maximum = Math.max(maximum, count);
			return;
		}
		for (int i = index; i < 26; i++) {
			temp = temp | bin[i];
			K--;
			func(i + 1);
			temp -= bin[i];
			K++;
		}
	} // [FUNC]
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		bin = new long[26];
		long b = 1;
		for (int i = 0; i < 26; i++) {
			bin[i] = b;
			b *= 2;
		}
		maximum = 0;
		visited = new boolean[26];
		counter = 0;
		temp = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) - 5;
		
		word = new long[N];
		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			for (char c : str.toCharArray()) {
				if (c == 'a' || c == 'c' || c == 'i' || c == 'n' || c == 't') {
					continue;
				}
				word[n] |= 1 << (int) (c - 97);
			}
//			System.out.println(word[n]);
		}
		Arrays.sort(word);
		
		if (0 <= K) {
			func(0);
		}
		
		System.out.println(maximum);
	} // [MAIN]
	
}
