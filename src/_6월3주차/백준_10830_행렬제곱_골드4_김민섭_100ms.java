import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	
	// main
	public static void main(String[] args) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		long[][] A = new long[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Long.parseLong(st.nextToken());
			}
		}
		
		long[][] result = mat_mul(A, B);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]%1000).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	// mat_mul
	static long[][] mat_mul(long[][] matrix, long num) {
		
		if (num == 1) {
			return matrix;
		}
		
		if (num % 2 == 0) {
			long[][] new_mat = mat_mul(matrix, num/2);
			return mat_mul(new_mat, new_mat);
		}
		
		return mat_mul(mat_mul(matrix, num-1), matrix);
	}
	
	// mat_mul
	static long[][] mat_mul(long[][] matrix1, long[][] matrix2) {
		
		long[][] res = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					res[i][j] += (matrix1[i][k] * matrix2[k][j]) % 1000;
				}
				res[i][j] %= 1000;
			}
		}
		return res;
		
	}
	
}
