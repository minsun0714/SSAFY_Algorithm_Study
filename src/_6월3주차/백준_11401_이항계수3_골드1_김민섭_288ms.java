import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long div = 1000000007;
	
	// main
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		// 페르마의 소정리 (p는 prime && n % p != 0) 일 때
		// n^p mod p == n mod p ->
		// n^p-1 mod p == 1 mod p ->
		// n^p-2 mod p == n^-1 mod p ->
		// n^-1 mod p == n^p-2 modp
		
		// 구해야 하는 것 == ( N! / ( (N-K)! * K! ) ) % div
		// == ((N! % div) * ( ( ( (N-K)! * K! ) ^ -1 ) % div ) ) % div
		// == ( (N! % div) * ( ( ( (N-K)! * K! ) ^ (div-2) ) % div ) ) % div
		
		System.out.println( ( (fact(N)%div) * mod_in(((fact(N-K)*fact(K)))%div,div-2) ) % div );
		
	}
	
	// fact
	static long fact(long n) {
		if (n <= 1) {
			return 1;
		}
		return (fact(n-1) * n) % div;
	}
	
	// mod_in
	static long mod_in(long a, long p) {
		long result = 1;
		
		while (0 < p) {
			if(p % 2 == 0) {
				a = a * a;
				a = a % div;
				p = p / 2;
			} else {
				result = (result * a) % div;
				p--;
			}
		}
		
		return result;
	}
	
	
}
