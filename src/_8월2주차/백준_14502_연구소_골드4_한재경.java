package _8월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백트래킹 + bfs, 408ms
public class 백준_14502_연구소_골드4_한재경 {
    public static int maxSafeArea = 0;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};
    public static int[][] newMap;

    public static void backTracking(int n, int m, int nowi, int nowj, int[][] map, List<int[]> nowBuild) { //벽칠곳 정하기
        if (nowBuild.size() >= 3) {

            //매번 맵 새로 구조화 - 깊은복사로!!! Arrays.copyOf는 얕은 복사!
            newMap = new int[n][m];
            for (int i = 0; i < n; i++) {
                System.arraycopy(map[i], 0, newMap[i], 0, m);
            }

            //벽 세우기
            for (int[] xy : nowBuild) {
                newMap[xy[0]][xy[1]] = 1;
            }

            //바이러스 퍼뜨리기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (newMap[i][j] == 2) {
                        newMap[i][j] = 3;
                        bfs(n, m, i, j);
                    }
                }
            }
            //안전영역 크기 구하기
            int safeArea = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (newMap[i][j] == 0) {
                        safeArea++;
                    }
                }
            }
            maxSafeArea = Math.max(safeArea, maxSafeArea);
            return;
        }
        //다음 지점부터 계속 탐색
        for (int i = nowi; i < n; i++) {
            // 여기 주의!!! 현재 행이 nowi 아니면 j는 0부터 시작해야 함
            for (int j = (i == nowi) ? nowj : 0; j < m; j++) {
                if (map[i][j] == 0) {
                    nowBuild.add(new int[]{i, j});
                    backTracking(n, m, i, j + 1, map, nowBuild);
                    nowBuild.remove(nowBuild.size() - 1);
                }
            }
        }
    }

    public static void bfs(int n, int m, int nowx, int nowy) {
        Deque<int[]> q = new ArrayDeque<>();
        q.push(new int[] {nowx, nowy, 1});

        while (!q.isEmpty()) {
            int[] now = q.pollFirst();
            int cnt = now[2];
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && newMap[nx][ny] == 0) {
                    newMap[nx][ny] = 3;
                    q.addLast(new int[] {nx, ny, ++cnt});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backTracking(n, m , 0, 0, map, new ArrayList<>());

        System.out.println(maxSafeArea);
    }
}