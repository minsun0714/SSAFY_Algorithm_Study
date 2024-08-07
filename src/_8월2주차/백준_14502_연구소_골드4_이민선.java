package _8월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 384ms
// 백트래킹
public class 백준_14502_연구소_골드4_이민선 {
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        StringTokenizer st = new StringTokenizer(input);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];

        for (int i=0;i<n;i++) {
            String row = br.readLine();
            st = new StringTokenizer(row);
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(board, n, m, 0, 0, 0);
        System.out.println(answer);
    }

    public static void backTracking(int[][] board, int n, int m, int depth, int startX, int startY){
        if (depth == 3){
            int[][] copiedBoard = spreadVirus(board,n, m);
            int result = countSafeArea(copiedBoard, n, m);

            answer = Math.max(answer, result);
            return;
        }

        for (int i=startX;i<n;i++){
            for (int j=(i == startX ? startY : 0);j<m;j++){
                if (board[i][j] == 0){
                    board[i][j] = 1;
                    backTracking(board, n, m, depth + 1, i, j + 1);
                    board[i][j] = 0;
                }
            }
        }
    }

    public static int[][] spreadVirus(int[][] board, int n, int m){
        int[][] copied_board = new int[n][];

        for (int i=0;i<n;i++){
            int[] copiedRow = board[i].clone();
            copied_board[i] = copiedRow;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Stack<int[]> stack = new Stack<>();

        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (copied_board[i][j] == 2){
                    stack.push(new int[]{i, j});
                }
            }
        }

        while (!stack.empty()){
            int[] cur = stack.pop();

            int x = cur[0];
            int y = cur[1];

            for (int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];


                if (nx < 0 || ny < 0|| nx >= n || ny >= m) continue;

                if (copied_board[nx][ny] > 0) continue;

                copied_board[nx][ny] = 2;

                stack.push(new int[]{nx, ny});
            }

        }
        return copied_board;
    }

    public static int countSafeArea(int[][] copiedBoard, int n, int m){
        int result = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (copiedBoard[i][j] == 0)
                    result++;
            }
        }
        return result;
    }
}
