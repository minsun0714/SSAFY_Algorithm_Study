import java.io.*;
import java.util.*;

public class Main_1030_프렉탈평면_G3_김민섭_104ms {
	
	// [GET COLOR AT]
	private static int getColorAt(int row, int col, int n, int k, int width) {
		int blackStart = (width / n) * ((n - k) / 2);
		int blackEnd = blackStart + (width / n) * k - 1;
		int nextWidth = width / n;
		
		if (blackStart <= row && row <= blackEnd && blackStart <= col && col <= blackEnd) {
			return 1;
		}
		
		if (width != 1) {
			return getColorAt(row % nextWidth, col % nextWidth, n, k, nextWidth);
		}
		
		return 0;
	}
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int rowStart = Integer.parseInt(st.nextToken());
		int rowEnd = Integer.parseInt(st.nextToken());
		int colStart = Integer.parseInt(st.nextToken());
		int colEnd = Integer.parseInt(st.nextToken());
		
		int width = (int) Math.pow(N, T);
		
		for (int row = rowStart; row <= rowEnd; row++) {
			for (int col = colStart; col <= colEnd; col++) {
				sb.append(getColorAt(row, col, N, K, width));
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}