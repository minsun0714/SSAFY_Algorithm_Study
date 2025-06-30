package etc._2_1;

import java.io.*;
import java.util.*;

// dfs, 93ms
public class Softeer_함께하는효도_레벨3_김동빈_93ms {

    static class Point {
    	int p;
        int x;
        int y;

        public Point(int p, int x, int y){
        	this.p = p;
            this.x = x;
            this.y = y;
        }

		@Override
		public String toString() {
			return "Point [p=" + p + ", x=" + x + ", y=" + y + "]";
		}
    }
    
    static final int[] PRIME_VALUE = {3, 5, 7};
    static final int[] PRIME_TIME = {11, 13, 17, 19};
    
    static final int NOT_VISITED = 2;
    static final int FULL_TIME = 3;
    
    static int[][] forest;
    static Point[] friends;
    static int n;
    static int m;
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        forest = new int[n][n];
        friends = new Point[m];
        
        for (int i = 0; i < n; ++i){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j){
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < m; ++i){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            
            Point friend = new Point(PRIME_VALUE[i], x, y);
            friends[i] = friend;
        }
        
        // ~ 입력 완료
        visited = new int[n][n];
        for (int[] row : visited) {
        	for (int i = 0; i < n; ++i) {
        		row[i] = NOT_VISITED;
        	}
    	}
        // 사람, 칸수, 수확량, 시간
        harvest(0, 0, 0, friends[0].x, friends[0].y);

        System.out.println(answer);   
    }
    
    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int WAYS = 4;
    static int answer = 0;
    static int[][] visited;
    
    static void harvest(int cur, int time, int sum, int x, int y){
    	
    	int p = friends[cur].p;
    	int apple = (visited[x][y] == NOT_VISITED) ? forest[x][y] : 0; // 현재칸 수확량 계산
    	int PRE_STATUS = visited[x][y]; 
        visited[x][y] = p * PRIME_TIME[time]; // 방문(수확) 처리
        
        int curSum = sum + apple; // 현재까지 총 수확량
        
        // m번째 사람이 FULL_TIME (3초)만큼 수확
        if (cur == m - 1 && time == FULL_TIME) {
            if (answer < curSum) {
                answer = curSum;
                
            }
            // 방문 해제
            visited[x][y] = PRE_STATUS;
            return;
        }
        
        // m번째 사람이 3초만큼 수확해서 다음 사람 차례임
        if (time == FULL_TIME) {

            harvest(cur + 1, 0, curSum, friends[cur + 1].x, friends[cur + 1].y);

            // 방문 해제
            visited[x][y] = PRE_STATUS;
            return;
        }

        // 그 외에는 탐색해야 함
        for (int dir = 0; dir < WAYS; ++dir){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (isOutOfRange(nx, ny)) continue; // 범위 밖
            if (visited[nx][ny] % p == 0) continue; // 되돌아 가는 이동
            if (visited[nx][ny] % PRIME_TIME[time + 1] == 0) continue; // 충돌하는 이동

            harvest(cur, time + 1, curSum, nx, ny); // 수확하자!~     
        }

        // 방문 해제
        visited[x][y] = PRE_STATUS;
        return;        
    }

    static boolean isOutOfRange(int r, int c){
        return (r < 0 || r >= n || c < 0 || c >= n);
    }
}