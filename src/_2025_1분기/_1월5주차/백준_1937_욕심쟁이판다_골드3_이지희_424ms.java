package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1937_욕심쟁이판다_골드3_이지희_424ms {
    static int[][] map, lis;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static int N, lisMax;

    static void dfs(int r, int c) {
        int max = 0;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
                continue;
            }

            if(map[nr][nc] > map[r][c]) {
                if(lis[nr][nc] == 0) {
                    dfs(nr, nc);
                }
                max = Math.max(max, lis[nr][nc]);
            }
        }

        lis[r][c] = max + 1;
        lisMax = Math.max(lisMax, lis[r][c]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lisMax = 0;

        map = new int[N][N];
        lis = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(lis[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        System.out.println(lisMax);
        br.close();
    }
}
