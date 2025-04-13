package _4월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


// 백트래킹 + bfs
public class 백준_1941_소문난칠공주_골드3_이민선_444ms {
    static char[][] board = new char[5][5];
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i=0;i<5;i++){
            String row = br.readLine();
            for (int j=0;j<5;j++){
                board[i][j] = row.charAt(j);
            }
        }

        combination(0, new int[7][2], 0);
        System.out.println(answer);
    }

    private static void combination(int depth, int[][] selected, int start){
        if (depth >= 4 && countY(selected, depth) >= 4) return;
        if (depth == 7) {
            if (countGroups(selected) == 1) answer++;
            return;
        }

        for (int i=start;i<25;i++){
            int rowIdx = i / 5;
            int colIdx = i % 5;

            selected[depth] = new int[]{rowIdx, colIdx};
            combination(depth + 1, selected, i + 1);
            selected[depth] = new int[]{0, 0};
        }
    }

    private static int countGroups(int[][] selected){
        int[][] countBoard = new int[5][5];
        for (int[] s:selected){
            countBoard[s[0]][s[1]] = 1;
        }

        int count = 0;

        for (int i=0;i<5;i++) {
            for (int j=0;j<5;j++){
                if (countBoard[i][j] == 1){
                    bfs(i, j, countBoard);
                    count++;
                }
            }
        }

        return count;
    }

    private static void bfs(int x, int y, int[][] countBoard){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()){
            int[] cur = q.poll();

            for (int i=0;i<4;i++){
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

                if (countBoard[nx][ny] == 0) continue;

                countBoard[nx][ny] = 0;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    private static int countY(int[][] selected, int depth){
        int countY = 0;

        for (int i=0;i<depth;i++){
            int x = selected[i][0], y = selected[i][1];
            if (board[x][y] == 'Y') countY++;
        }

        return countY;
    }
}
