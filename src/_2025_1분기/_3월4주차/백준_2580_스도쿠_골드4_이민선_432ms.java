package _3월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2580_스도쿠_골드4_이민선_432ms {
    static int[][] board = new int[9][9];
    static int n;
    static int[][] zeroIdxList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        for (int i=0;i<9;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<9;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) n++;
            }
        }

        zeroIdxList = new int[n][2];
        int idx = 0;
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if (board[i][j] == 0)zeroIdxList[idx++] = new int[]{i, j};
            }
        }

        backtracking(0);
    }

    private static void backtracking(int depth){
        if (depth == n){
            print();
            System.exit(0);
        }

        for (int i=1;i<=9;i++){
            int[] curIdx = zeroIdxList[depth];

            int x = curIdx[0], y = curIdx[1];

            if (isRepetitive(x, y, i)) continue;

            board[x][y] = i;
            backtracking(depth + 1);
            board[x][y] = 0;
        }
    }

    private static boolean isRepetitive(int x, int y, int val) {
        // 가로 check
        for (int i=0;i<9;i++){
            int cur = board[i][y];
            if (cur == 0) continue;
            if (cur == val) return true;
        }

        // 세로 check
        for (int i=0;i<9;i++){
            int cur = board[x][i];
            if (cur == 0) continue;
            if (cur == val) return true;
        }

        // 사각형 check
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i=startX;i<startX + 3;i++){
            for (int j=startY;j<startY + 3;j++){
                int cur = board[i][j];
                if (cur == 0) continue;
                if (cur == val) return true;
            }
        }
        return false;
    }

    private static void print(){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
