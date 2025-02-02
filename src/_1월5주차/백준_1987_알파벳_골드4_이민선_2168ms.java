package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 백준_1987_알파벳_골드4_이민선_2168ms {
    static int r, c;
    static char[][] board;
    static int answer = 1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for (int i=0;i<r;i++){
            String input = br.readLine();
            for (int j=0;j<c;j++){
                board[i][j] = input.charAt(j);
            }
        }
        visited = new int[r][c];
        Set<Character> set = new HashSet<>();
        set.add(board[0][0]);
        dfs(0, 0, 1, set);
        System.out.println(answer);
    }
    public static void dfs(int x, int y, int depth, Set<Character> set){

        answer = Math.max(answer, depth);

        for (int i=0;i<4;i++){
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
            if (visited[nx][ny] == 1) continue;
            if (set.contains(board[nx][ny])) continue;
            visited[nx][ny] = 1;
            set.add(board[nx][ny]);
            dfs(nx, ny, depth+ 1, set);
            set.remove(board[nx][ny]);
            visited[nx][ny] = 0;
        }
    }
}
