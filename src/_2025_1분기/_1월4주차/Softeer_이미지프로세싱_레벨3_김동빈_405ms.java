package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 405ms, BFS
public class Softeer_이미지프로세싱_레벨3_김동빈_405ms {
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int H;
	static int W;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		board = new int[H][W];
		for (int i = 0; i < H; ++i) {	
			StringTokenizer row = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < W; ++j) {
				board[i][j] = Integer.parseInt(row.nextToken());
			}
		}
		// ~ 입력 완료
		
		// query
		int queryCount = Integer.parseInt(br.readLine());
		for (int q = 0; q < queryCount; ++q) {
			st = new StringTokenizer(br.readLine());					
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int color = Integer.parseInt(st.nextToken());
			
			StartBFS(r, c, color); // 쿼리 수행
		}
		
		printBoard(); // 결과 출력
	}
	
	static void StartBFS(int r, int c, int targetColor) {
		
		// 상하좌우
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int WAYS = 4;
		
		boolean[][] visited = new boolean[H][W];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(r, c));
		visited[r][c] = true;
		
		int originColor = board[r][c]; // 최초의 색깔 
		while (!q.isEmpty()) {
			
			Point cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			board[x][y] = targetColor; // 색깔 변경
			
			// 탐색
			for (int dir = 0; dir < WAYS; ++dir) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if (isOutOfRange(nx, ny)) continue; // 범위 밖
				if (visited[nx][ny]) continue; // 이미 왔던곳 
				
				int nColor = board[nx][ny]; // 탐색 대상의 픽셀 색깔
				
				if (nColor != originColor) continue; // 연결 되지 않은 픽셀
				
				visited[nx][ny] = true; // 방문처리
				q.offer(new Point(nx, ny)); // q에 넣기
			}
		}
		
		return;
	}
	
	
	static boolean isOutOfRange(int r, int c) {
		return (r < 0 || r >= H || c < 0 || c >= W);
	}
	
	
	// 보드 출력
	static void printBoard() {
		
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < H; ++i) {
			for (int j = 0; j < W; ++j){
				
				result.append(board[i][j]).append(" ");
			}
			result.append("\n");
		}
		
		System.out.println(result);
	}
	
}
