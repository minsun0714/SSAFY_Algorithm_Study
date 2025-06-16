import java.io.*;

// dfs, 백트래킹
public class 백준_1987_알파벳_골드4_이지희_860ms {
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static int R, C, maxSize;
    static char[][] map;
    static boolean[] visited = new boolean[26];

    static void dfs(int r, int c, int depth) {
        maxSize = Math.max(maxSize, depth);

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                continue;

            int idx = map[nr][nc] - 'A';
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(nr, nc, depth + 1);
                visited[idx] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        map = new char[R][C];

        for (int r=0; r<R; r++)
            map[r] = br.readLine().toCharArray();

        visited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(maxSize);
    }
}