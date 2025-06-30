package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1937_욕심쟁이판다_골드3_김동빈_480ms {
	
	static class Point {
		
		int x;
		int y;
		int d;
		
		public Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	static int n;
	static int[][] board;
	static int[][] dist;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		board = new int[n][n];
		dist = new int[n][n];
		for (int i = 0; i < n; ++i) {
			Arrays.fill(dist[i], 1);
		}
		
		
		for (int i = 0; i < n; ++i) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; ++j) {
				board[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		
		// ~ 입력완료
		
		// 모든 점에서 탐색
		int localOpt = 1;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (dist[i][j] > 1) continue; // 탐색 해본 점
				
				int feasibleAnswer = DFS(i, j);
				if (localOpt < feasibleAnswer) {
					localOpt = feasibleAnswer;
				}
			}
		}
		
		int globalOpt = localOpt;
		System.out.println(globalOpt);
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int WAYS = 4;
	
	// 가는게 아니고 오는 것임
	static int DFS(int x, int y) {
		// 기저
		if (dist[x][y] > 1) {
			return dist[x][y];
		}
		
		// 4방향 dp
		int pathLen = 1;
		for (int dir = 0; dir < WAYS; ++dir) {
			
			int px = x + dx[dir];
			int py = y + dy[dir];
			
			if (isOutOfRange(px, py)) continue; // 범위 밖
			if (board[px][py] >= board[x][y]) continue; // 대나무가 같거나 많음
			if (dist[px][py] + 1 < dist[x][y]) continue; // 지금의 탐색이 더 크네? 
			
			int prePathLen = DFS(px, py) + 1;
			if (pathLen < prePathLen) {
				pathLen = prePathLen;
			}
		}
		
		return dist[x][y] = pathLen;
	}
	
	private static boolean isOutOfRange(int nx, int ny) {
		return (nx < 0 || nx >= n || ny < 0 || ny >= n);
	}
}
