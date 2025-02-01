import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 124ms, 다익스트라
public class 백준_1261_알고스팟_골드4_김동빈_124ms {
	
	static class Info implements Comparable<Info>{
		int x;
		int y;
		int brokenWallCount;
		public Info(int x, int y, int brokenWallCount) {
			this.x = x;
			this.y = y;
			this.brokenWallCount = brokenWallCount;
		}
		
		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.brokenWallCount, o.brokenWallCount);
		}
	}
	
	static int[][] board;
	private static int N;
	private static int M;
	static int INF = 100001;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); 
		N = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		
		for (int i = 0; i < N; ++i ) {
			
			String line = br.readLine();
			for (int j =0; j < M; ++j) {
				board[i][j] = line.charAt(j) - '0';
			}
			
		}
		// ~ 입력 완료
		
		int answer = StartBFS(0, 0);
		System.out.println(answer);
		
	}

	// 벽 부순 개수 리턴
	private static int StartBFS(int r, int c) {
		
		// 상하좌우
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int WAYS = 4;
		
		// init
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(r, c, 0));
		
		int[][] dist = new int[N][M];
		for (int i = 0; i < N; ++ i) {
			Arrays.fill(dist[i], INF);
		}
		dist[0][0] = 0;
		
		// BFS
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			int x = cur.x;
			int y = cur.y;
			int brokenWallCount = cur.brokenWallCount;
			
			// 목적지 도착
			if (x == (N - 1) && y == (M - 1)) {
				return brokenWallCount;
			}
			
			// 탐색
			for (int dir = 0; dir < WAYS; ++dir) {
				
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if (isOutOfRange(nx, ny)) continue; // 범위 밖
				
				if (isWall(nx, ny)) { // 벽
					if (dist[nx][ny] <= (brokenWallCount + 1)) continue;// 탐색 가치가 없음
					
					pq.offer(new Info(nx, ny, brokenWallCount + 1));
					dist[nx][ny] = brokenWallCount + 1;
					
				} else { // 벽 아님
					if ((dist[nx][ny] <= brokenWallCount)) continue; // 탐색 가치가 없음
					
					pq.offer(new Info(nx, ny, brokenWallCount));
					dist[nx][ny] = brokenWallCount;
				}
				
			}
		}
		
		return -1; // 목적지에 도착 한 적이 없이 끝난다.
	}
	
	static boolean isOutOfRange(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c>= M);
	}
	static boolean isWall(int r, int c) {
		return board[r][c] == 1;
	}
	
}
