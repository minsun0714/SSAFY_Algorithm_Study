/*
정확성  테스트
테스트 1 〉	통과 (0.16ms, 71.5MB)
테스트 2 〉	통과 (0.70ms, 88.5MB)
테스트 3 〉	통과 (0.22ms, 71.6MB)
테스트 4 〉	통과 (0.54ms, 86.6MB)
테스트 5 〉	통과 (0.19ms, 75.5MB)
테스트 6 〉	통과 (0.88ms, 81MB)
테스트 7 〉	통과 (1.66ms, 87.6MB)
테스트 8 〉	통과 (0.72ms, 85.3MB)
테스트 9 〉	통과 (5.15ms, 72.9MB)
효율성  테스트
테스트 1 〉	통과 (25.21ms, 64.5MB)
테스트 2 〉	통과 (73.52ms, 69.1MB)
테스트 3 〉	통과 (97.46ms, 85.6MB)
테스트 4 〉	통과 (21.69ms, 65.2MB)
테스트 5 〉	통과 (107.09ms, 71.2MB)
테스트 6 〉	통과 (21.59ms, 66.2MB)
 */

import java.util.Stack;
//dfs
class Solution {

    static int[] sdr = {-1, 0, 1, 0}; //상 우 하 좌
    static int[] sdc = {0, 1, 0, -1}; //상 우 하 좌
    static boolean[][] visited;
    static boolean[] sum_visited;

    static int[][] land;
    static int[] sumlist;

    static int srow, scol, cnt;

    public int solution(int[][] inputland) {
        land = inputland;

        // static 변수 초기화
        srow = land.length;
        scol = land[0].length;

        sumlist = new int[scol];

        visited = new boolean[srow][scol]; //처음은 false
        sum_visited = new boolean[scol]; //처음은 false

        int max = 0;
        for (int c = 0; c < scol; c++) {
            for (int r = 0; r < srow; r++) {
                // 해당 칸이 1이고, 방문 전인 칸이라면
                if (land[r][c] == 1 && !visited[r][c]) {
                    cnt = 0; // 숫자 세기 시작
                    visit_check(r, c);

                    // 셌던 값을 해당 기억해둔 컬럼들에 추가
                    for (int sc = 0; sc < scol; sc++) {
                        if (sum_visited[sc]) { // 대상이 된다면
                            sum_visited[sc] = false; // 초기화를 한 뒤에
                            sumlist[sc] += cnt; // 셌던 값 추가
                        }
                    }
                }
            }

            if (sumlist[c] > max) {
                max = sumlist[c];
            }
        }
        return max;
    }

    public void visit_check(int r, int c) {
        // 명시적인 스택을 이용한 DFS 구현
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {r, c});
        visited[r][c] = true;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int cr = current[0];
            int cc = current[1];

            cnt++; // 석유 덩어리 크기 증가

            sum_visited[cc] = true; // 해당 열은 덩어리에 포함됨

            // 상, 우, 하, 좌 탐색
            for (int d = 0; d < sdr.length; d++) {
                int dr = cr + sdr[d];
                int dc = cc + sdc[d];

                if (n_check(dr, dc) && land[dr][dc] == 1 && !visited[dr][dc]) {
                    visited[dr][dc] = true;
                    stack.push(new int[] {dr, dc}); // 스택에 추가
                }
            }
        }
    }



    public boolean n_check(int dr, int dc) {
        return dr >= 0 && dc >= 0 && dr < srow && dc < scol;
    }
}




