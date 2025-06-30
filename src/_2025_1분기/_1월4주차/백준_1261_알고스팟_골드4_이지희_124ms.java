package _1월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 다익스트라 - N, M 순서 조심..
public class 백준_1261_알고스팟_골드4_이지희_124ms {
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);

        int[][] map = new int[N][M];
        int[][] cost = new int[N][M];

        for(int n=0; n<N; n++) {
            String line = br.readLine();
            for(int m=0; m<M; m++) {
                map[n][m] = line.charAt(m) - '0';
                cost[n][m] = Integer.MAX_VALUE;
            }
        }


        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.add(new int[] {0, 0, 0});
        cost[0][0] = 0;

        while(!pq.isEmpty()) {
            int[] now = pq.poll();

            int r = now[0];
            int c = now[1];
            int d = now[2];

            if(r == N - 1 && c == M - 1) {
                System.out.println(d);
                break;
            }

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                int newCost = d + map[nr][nc];
                if(newCost < cost[nr][nc]) {
                    cost[nr][nc] = newCost;
                    pq.add(new int[] {nr, nc, newCost});
                }
            }
        }

        br.close();
    }

}
