import java.io.*;
import java.util.*;

public class 백준_1987_알파벳_골드4_이민선_ms {
    static int r, c;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];

        for (int i=0;i<r;i++){
            String row = br.readLine();
            for (int j=0;j<c;j++){
                board[i][j] = row.charAt(j);
            }
        }

        Set<Character> set = new HashSet<>();
        set.add(board[0][0]);
        dfs(0, 0, set);
        System.out.println(answer);
    }

    private static void dfs(int x, int y, Set<Character> set){
        answer = Math.max(answer, set.size());

        for (int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
            if (set.contains(board[nx][ny])) continue;

            set.add(board[nx][ny]);
            dfs(nx, ny, set);
            set.remove(board[nx][ny]);
        }
    }
}
