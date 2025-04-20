package etc._4_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 재귀
public class Main_백준_1030_프렉탈평면 {
	private static int[][] initBoard;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
		
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        
        int s = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R1 = Integer.parseInt(st.nextToken());
        int R2 = Integer.parseInt(st.nextToken());
        int C1 = Integer.parseInt(st.nextToken());
        int C2 = Integer.parseInt(st.nextToken());
		
		initBoard = new int[N][N];
		
		for (int i = (N - K) / 2; i < (N - K) / 2 + K; ++i) {
			for (int j = (N - K) / 2; j < (N - K) / 2 + K; ++j) {
				initBoard[i][j] = 1;
			}
		}
		
		for (int i = R1; i <= R2; ++i) {
			for (int j = C1; j <= C2; ++j) {
				
				int v = find(i, j, N, K, s);
				answer.append(v);
			}
			answer.append("\n");
		}
		
		System.out.println(answer);
	}
	
	public static int find(int x, int y, int N, int K, int s) {
        if (s == 0) return 0;
        if (s == 1) return initBoard[x][y];
        
        int size = (int) Math.pow(N, s - 1); // 현재 단계의 한 블록 크기
        int row = x / size;
        int col = y / size;

        int start = (N - K) / 2;
        int end = start + K;

        if (start <= row && row < end && start <= col && col < end) return 1;

        return find(x % size, y % size, N, K, s - 1);
    }
}