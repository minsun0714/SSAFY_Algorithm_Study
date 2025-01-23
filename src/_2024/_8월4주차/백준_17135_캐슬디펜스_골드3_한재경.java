package _2024._8월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//조합 + bfs, 264ms
public class 백준_17135_캐슬디펜스_골드3_한재경 {
    static int[] dx = {0, -1, 0}; //좌상우
    static int[] dy = {-1, 0, 1};
    //궁수들 동시에 공격
    //거리 d이하 적 중 가장 가까운 적, 같으면 왼쪽 적부터
    //같은 적 여러명이 공격 가능
    //bfs (상좌우 순서로) cnt 올려서 d안에 적 발견하면 return 적위치
    //궁수 한명씩 넣고 어느 위치 적 쏘는지 리턴
    static int[] attackWho(int[][] grid, int n, int m, int archer, int d){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{n - 1, archer, 1}); //x,y,cnt
        int[][] visited = new int[n][m];
        visited[n-1][archer] = 1;
        while (!q.isEmpty()) {
            int[] qpoll = q.poll();
            int x = qpoll[0];
            int y = qpoll[1];
            int cnt = qpoll[2];
            if (cnt > d) {
                continue;
            }
            if (grid[x][y] == 1) {
                return new int[]{x, y};
            }
            for (int i = 0; i < 3; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && visited[nx][ny] != 1) {
                    visited[nx][ny] = 1;
                    q.add(new int[]{nx, ny, cnt + 1});
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        //공격받거나 성까지 이동하면 게임에서 제외됨. 모두제외되면 게임끝
        //궁수 공격 턴, 적 이동 턴(i+1)
        //궁수3명 성 칸에 배치해 제거할 수 있는 최대 적의 수!
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //행
        int m = Integer.parseInt(st.nextToken()); //열
        int d = Integer.parseInt(st.nextToken()); //공격 범위
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //궁수 배치 경우 all
        //n행에 3명 배치. m열 중 중복없이 3개 조합
        List<int[]> allArchers = new ArrayList<>(); //3명 궁수위치(열) [[0,1,2], [1,2,3]..]
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                for (int k = j + 1; k < m; k++) {
                    allArchers.add(new int[]{i, j, k});
                }
            }
        }

        int maxcnt = 0;
        //모든 궁수에 대해 for문
        for (int[] archers : allArchers) { // 각 턴
            int[][] newgrid = new int[n][m];
            for (int i = 0; i < n; i++) {
                System.arraycopy(grid[i], 0, newgrid[i], 0, m);
            }
            int cnt = 0;
            while (true) {
                List<int[]> dies = new ArrayList<>();

                // 세 명의 궁수 공격 턴
                for (int i = 0; i < 3; i++) {
                    int[] die = attackWho(newgrid, n, m, archers[i], d);
                    if (die != null) {
                        dies.add(die);
                    }
                }
                // 공격받은 적 죽이기 - 중복 시 cnt x
                for (int[] died : dies) {
                    if (newgrid[died[0]][died[1]] != 0) {
                        newgrid[died[0]][died[1]] = 0;
                        cnt++;
                    }
                }


                // 적 전진 턴
                // 역순회 if grid[i][j] == 1: i+1을 1로
                boolean isexit = true;
                for (int i = n - 1; i >= 0; i--) {
                    for (int j = 0; j < m; j++) {
                        if (newgrid[i][j] == 1) {
                            isexit = false;
                            if (i == n - 1) {
                                newgrid[i][j] = 0;
                                continue;
                            }
                            newgrid[i + 1][j] = 1;
                            newgrid[i][j] = 0;
                        }
                    }
                }
                maxcnt = Math.max(cnt, maxcnt);
                if (isexit) {
                    break;
                }
            }
        }
        System.out.println(maxcnt);
    }
}