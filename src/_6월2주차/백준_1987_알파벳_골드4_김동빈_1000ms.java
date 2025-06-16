package etc._6_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS
public class 백준_1987_알파벳_골드4_김동빈_1000ms {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char letter = line.charAt(j);
                board[i][j] = letter;
            }
        }
        
        answer = 0;
        visited = new boolean[26];
        
        DFS(0, 0, 1);
		
        System.out.println(answer);
	}
	
	static int R;
	static int C;
	static char[][] board;
	static int answer;
	static boolean visited[];
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static void DFS(int x, int y, int len) {
		
		char curChar = board[x][y]; 
		visited[curChar - 'A'] = true;
		
		if (len > answer) {
			answer = len;
		}
		
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			char nextChar = board[nx][ny]; 
			if (visited[nextChar - 'A']) continue;
			
			DFS(nx, ny, len + 1);
		}
		
		visited[curChar - 'A'] = false;
	}
}
