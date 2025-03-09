package etc._3_1;

import java.io.*;
import java.util.*;

// dp, 430ms
public class 백준_2186_문자판_골드3_김동빈_430ms {
	
	static int[][][] dp;
	static int len;
	private static char tail;
	private static char[][] board;
	private static int K;
	private static String word;
	private static int M;
	private static int N;
	
	public static void main(String[] args) throws Exception {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
		
        word = br.readLine();
        len = word.length();
        char head = word.charAt(0);
        tail = word.charAt(len - 1);
        
        dp = new int[N][M][len + 1];
        for (int i = 0; i < N; ++i) {
        	for (int j = 0; j < M; ++j) {
            	Arrays.fill(dp[i][j], -1);
            }
        }
        
        int answer = 0;
        for (int i = 0; i < N; ++i) {
        	for (int j = 0; j < M; ++j) {
        		char cur = board[i][j];
        		if (cur == head) {
        			answer += dfs(1, i, j);
        		}
        	}
        }
        
        System.out.println(answer);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int dfs(int depth, int x, int y) {
		
		// 메모
		if (dp[x][y][depth] != -1) {
			return dp[x][y][depth];
		}
		
		char curChar = board[x][y];
		
		// 기저
		if (depth == len) {
			if (curChar == tail) {
				return dp[x][y][depth] = 1;
			}
			
			return 0;
		}
		
		dp[x][y][depth] = 0;
		
		// 탐색
		for (int d = 0; d < 4; ++d) {
			for (int k = 1; k <= K; ++k) {
				int nx = x + dx[d] * k;
				int ny = y + dy[d] * k;
				
				if (isOutOfRange(nx, ny)) continue;
				if (isNotFitChar(depth + 1, nx, ny)) continue;
				
				dp[x][y][depth] += dfs(depth + 1, nx, ny);
			}
		}
		
		return dp[x][y][depth];
	}

	private static boolean isNotFitChar(int i, int nx, int ny) {
		return (word.charAt(i - 1) != board[nx][ny]);
	}

	private static boolean isOutOfRange(int nx, int ny) {
		return (nx < 0 || nx >= N || ny < 0 || ny >= M);
	}
}
