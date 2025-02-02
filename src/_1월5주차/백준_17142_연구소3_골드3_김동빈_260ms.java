package etc._1_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 조합, bfs, 256ms
public class 백준_17142_연구소3_골드3_김동빈_260ms {
	
	public static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static final int VIRUS = 2;
	static final int WALL = 1;
	static final int ROOM = 0;
	static int N;
	static int M;
	static int[][] board;
	static Point[] viruses;
	static int virusCount;
	static int roomCount;
	static int answer;
	private static int INF;
	
	public static void main(String[] args) throws Exception {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		StringTokenizer NM = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(NM.nextToken()); 
		M = Integer.parseInt(NM.nextToken()); 
		
		board = new int[N][N];
		viruses = new Point[10];
		
		virusCount = 0;
		roomCount = 0;
		
		for (int i = 0; i < N; ++i) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; ++j) {
				int e = Integer.parseInt(line.nextToken()); 
				board[i][j] = e;
				
				// 바이러스 위치 기억
				if (e == VIRUS) {
					viruses[virusCount++] = new Point(i, j);
				} else if (e == ROOM) {
					roomCount++;
				}
			}
		}
		
		// ~ 입력 완료
		INF = N * N + 1;
		answer = INF;
		for (int i = 0; i < virusCount; ++i) {
			combViruses(i, new int[M], 0);
		}
		
		if (roomCount == 0) {
			System.out.println(0);
		} else if (answer >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	
	static int BFS(int[] initialViruses) {
		
		// 상하좌우
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int WAYS = 4;
		
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		// init
		for (int i = 0; i < M; ++i) {
			Point v = viruses[initialViruses[i]];
			int x = v.x;
			int y = v.y;
			visited[x][y] = true;
			q.offer(v);
		}
		
		int pollCount = M;
		int time = 0;
		int infectedRoomCount = 0;
		
		// bfs
		while(!q.isEmpty()) {
			int nextPollCount = 0;
			
			// every tick
			for (int i = 0; i < pollCount; ++i) {
				
				Point cur = q.poll();
				int x = cur.x;
				int y = cur.y;
				
				// 탐색
				for (int dir = 0; dir < WAYS; ++dir) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if (isOutOfRange(nx, ny)) continue; // 범위 밖
					if (visited[nx][ny]) continue; // 이미 방문 함
					if (isWall(nx, ny)) continue; // 벽
					if (isRoom(nx, ny)) ++infectedRoomCount; // 빈 방
					
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
					nextPollCount++;
				}
			}
			
			pollCount = nextPollCount;
			time++;
			
			if (infectedRoomCount >= roomCount) return time;
		}
		
		return infectedRoomCount >= roomCount ? time : INF;
	}
	
	static void combViruses(int cur, int[] selected, int depth) {
		// 골라
		selected[depth] = cur;
		
		// 기저
		if (depth == M - 1) {
			int localAnswer = BFS(selected);
			
			if (localAnswer < answer) {
				answer = localAnswer;
			}
			
			return;
		}
		
		// 조합
		for (int sel = cur + 1; sel < virusCount; ++sel) {
			combViruses(sel, selected, depth + 1); // 골라
		}
		
		return;
	}
	
	static boolean isOutOfRange(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
	
	static boolean isWall(int r, int c) {
		return board[r][c] == WALL;
	}
	
	static boolean isRoom(int r, int c) {
		return board[r][c] == ROOM;
	}

}
