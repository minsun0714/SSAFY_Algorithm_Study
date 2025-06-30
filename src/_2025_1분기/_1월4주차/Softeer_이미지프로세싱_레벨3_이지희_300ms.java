package _1월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs
public class Softeer_이미지프로세싱_레벨3_이지희_300ms {

    static int H, W;
    static int[][] map;
    static int[] dr = {0, 0, -1, 1}, dc = {-1, 1, 0, 0};

    static void coloring(int i, int j, int c) {
        int color = map[i][j]; // 해당 좌표색
        map[i][j] = c;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int d=0; d<4; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];

                if(nr < 0 || nr >= H || nc < 0 || nc >= W) {
                    continue;
                }

                // 같은 색일 때만
                if(map[nr][nc] == color) {
                    map[nr][nc] = c; // 색변경
                    queue.add(new int[] {nr, nc});
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for(int h=0; h<H; h++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int w=0; w<W; w++) {
                map[h][w] = Integer.parseInt(st.nextToken());
            }
        }

        int orderCnt = Integer.parseInt(br.readLine());
        for(int o=0; o<orderCnt; o++) {
            st = new StringTokenizer(br.readLine(), " ");
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            if(map[i][j] != c) {
                coloring(i, j , c);
            }
        }


        for(int h=0; h<H; h++) {
            for(int w=0; w<W; w++) {
                sb.append(map[h][w]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
        br.close();

    }
}
