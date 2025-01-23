package _2024._8월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 80ms
// 구현
public class 백준_14503_로봇청소기_골드5_이민선 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        StringTokenizer st = new StringTokenizer(input);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        input = br.readLine();
        st = new StringTokenizer(input);

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());



        int[][] board = new int[n][m];

        for (int i=0;i<n;i++){
            input = br.readLine();
            st = new StringTokenizer(input);

            for (int j=0;j<m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        int[] dr = new int[] {-1, 0, 1, 0};
        int[] dc = new int[] {0, 1, 0, -1};

        while (true){
            if (board[r][c] == 0){
                board[r][c] = 2;
                answer++;
            }

            boolean notCleaned = false;
            for (int i=0;i<4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (board[nr][nc] == 0){
                    notCleaned = true;
                    break;
                }
            }

            if (notCleaned){
                d = (d + 3) % 4;

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (board[nr][nc] == 0){
                    r = nr;
                    c = nc;
                }
            } else {
                int nr = r + dr[(d + 2) % 4];
                int nc = c + dc[(d + 2) % 4];

                if (board[nr][nc] != 1) {
                    r = nr;
                    c = nc;
                } else {
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}
