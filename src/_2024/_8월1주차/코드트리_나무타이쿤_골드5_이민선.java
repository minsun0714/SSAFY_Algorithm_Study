package _2024._8월1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 구현, 104ms
public class 코드트리_나무타이쿤_골드5_이민선 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        StringTokenizer st = new StringTokenizer(input);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][n];

        for (int i=0;i<n;i++){
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int j=0;j<n;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ArrayList<int[]> nutritionInfo = new ArrayList<>();

        nutritionInfo.add(new int[] {n - 1, 0});
        nutritionInfo.add(new int[] {n - 1, 1});
        nutritionInfo.add(new int[] {n - 2, 0});
        nutritionInfo.add(new int[] {n - 2, 1});


        int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
        int[] dy = {0, 1, 1, 0, -1, -1, -1, 0, 1};

        while (m > 0) {
            m--;

            input = br.readLine();
            st = new StringTokenizer(input);
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            for (int j = 0; j < nutritionInfo.size(); j++) {
                int[] nutrition = nutritionInfo.get(j);
                int x = nutrition[0];
                int y = nutrition[1];

                int nx = (x + n + dx[d] * p) % n;
                int ny = (y + n + dy[d] * p) % n;

                nutritionInfo.set(j, new int[]{nx, ny});
            }

            for (int i = 0; i < nutritionInfo.size(); i++) {
                int[] nutrition = nutritionInfo.get(i);
                int x = nutrition[0];
                int y = nutrition[1];
                board[x][y]++;
            }

            int[] diagonalX = new int[]{-1, -1, 1, 1};
            int[] diagonalY = new int[]{-1, 1, -1, 1};

            for (int i = 0; i < nutritionInfo.size(); i++) {
                int[] nutrition = nutritionInfo.get(i);
                int x = nutrition[0];
                int y = nutrition[1];


                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + diagonalX[dir];
                    int ny = y + diagonalY[dir];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                    if (board[nx][ny] == 0) continue;

                    board[x][y]++;
                }
            }
            int[][] temp = new int[n][n];
            for (int i = 0; i < nutritionInfo.size(); i++) {
                int[] nutrition = nutritionInfo.get(i);
                temp[nutrition[0]][nutrition[1]] = board[nutrition[0]][nutrition[1]];
            }
            nutritionInfo.clear();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] >= 2 && temp[i][j] == 0) {
                        board[i][j] -= 2;
                        nutritionInfo.add(new int[]{i, j});
                    }

                    if (temp[i][j] > 0) {
                        board[i][j] = temp[i][j];
                    }
                }
            }


        } // end of while
        int answer = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                answer += board[i][j];
            }
        }
        System.out.println(answer);
    } // end of main
}


