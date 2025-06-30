import java.util.Arrays;
import java.util.PriorityQueue;

// 연속 BFS 
public class LeetCode_2503_MaximumNumberOfPointsFromGridQueries_김동빈_112ms {
	
	static class Cell implements Comparable<Cell> {
		int x;
		int y;
		int point;
		public Cell(int x, int y, int point) {
			this.x = x;
			this.y = y;
			this.point = point;
		}
		@Override
		public int compareTo(Cell o) {
			return Integer.compare(this.point, o.point);
		}
	}
	
	static class Info implements Comparable<Info>{
		int query;
		int index;
		public Info(int query, int index) {
			this.query = query;
			this.index = index;
		}
		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.query, o.query);
		}
	}

	private int m;
	private int n;
	private PriorityQueue<Cell> q;
	private boolean[][] visited;
	private int aSum = 0;
	
    public int[] maxPoints(int[][] grid, int[] queries) {
    	
    	m = grid.length;
    	n = grid[0].length;
    	
    	// 쿼리를 작은 것부터 처리
    	PriorityQueue<Info> pq = new PriorityQueue<>();
    	int index = 0;
    	for (int q: queries) {
    		Info info = new Info(q, index++);
    		pq.offer(info);
    	}
    	
    	// 정답 배열
    	int[] answer = new int[queries.length];
    	
    	// 탐색 준비
    	q = new PriorityQueue<>();
    	visited = new boolean[m][n];
    	q.offer(new Cell(0, 0, grid[0][0]));
    	visited[0][0] = true;
    	
    	// 탐색 시작
    	while(!pq.isEmpty()) {
    		
    		// 현재 상태
    		Info cur = pq.poll();
    		int curQ = cur.query;
    		int curIndex = cur.index;
    		
    		// 탐색
			bfs(curQ, visited, grid);
			answer[curIndex] = aSum;
    	}
    	
    	return answer;
    }

	private void bfs(int query, boolean[][] visited, int[][] grid) {
		// 상하좌우
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		while (!q.isEmpty()) {
			
			Cell cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int point = cur.point;
			
			if (point < query) { // 갈 수 있다.
				++aSum;
				
				for (int d = 0; d < 4; ++d) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue; // 범위 밖
					if (visited[nx][ny]) continue; // 가 봄
					
					q.offer(new Cell(nx, ny, grid[nx][ny]));
					visited[nx][ny] = true;
				}
				
			} else { // 갈 수 없다.
				q.offer(cur);
				break;
			}
		}
				
		return;
	}
}