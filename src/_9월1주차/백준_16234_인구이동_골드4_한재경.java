import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//760ms, bfs
public class 백준_16234_인구이동_골드4_한재경 {
    static int n; //n*n 땅
    static int l; //l명 이상
    static int r; //r명 이하
    static int[][] populations; //각 나라 인구 수
    static int[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int cellCnt;
    static int cellSum;
    static boolean flag;

    static void linkBfs(Queue q, int cnt) { //[x,y]와 연결된 땅 전부 cnt로 칠하기
        while (!q.isEmpty()) {
            int[] xynum = (int[]) q.poll();
            int x = xynum[0];
            int y = xynum[1];
            cellSum += populations[x][y];
            int num = xynum[2]; //인구수
            cellCnt++;
            for (int i = 0; i < 4; i++) { //인접 4방향 탐색
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n && visited[nx][ny] == 0) { //범위 안이고 not visited고
                    int diff = Math.abs(num - populations[nx][ny]); //인구차
                    if (l <= diff && diff <= r) {
                        visited[nx][ny] = cnt;
                        q.add(new int[]{nx, ny, populations[nx][ny]});
                        flag = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        populations = new int[n][n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                populations[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new int[n][n];
            int cnt = 1;
            List<Integer> cellCnts = new ArrayList<>(); //i번 연합 개수
            List<Integer> cellSums = new ArrayList<>(); //i번 연합 총합

            cellCnts.add(0);
            cellSums.add(0);

            flag = true;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        cellCnt = 0;
                        cellSum = 0;
                        Queue<int[]> q = new ArrayDeque<>();
                        visited[i][j] = cnt;
                        q.add(new int[]{i, j, populations[i][j]});
                        linkBfs(q, cnt); //[i,j]와 연결된 연결리스트 색칠
                        cnt++;
                        cellCnts.add(cellCnt);
                        cellSums.add(cellSum);
                    }
                }
            }

            if (flag) {
                break;
            }

            //같은 번호 가진 visited 인구 병합
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int cellNum = visited[i][j]; //해당 연합넘버 구하기
                    if (cellNum >= 0) {
                        populations[i][j] = cellSums.get(cellNum) / cellCnts.get(cellNum); //셀인구수 = 연합총합 / 연합개수
                    }
                }
            }
            result++;
        }
        System.out.println(result);
    }
}
