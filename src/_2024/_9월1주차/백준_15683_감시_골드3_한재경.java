import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 240ms, 조합, 브루트포스
public class 백준_15683_감시_골드3_한재경 {
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int n, m = 0;
    static int minBlindSpot = Integer.MAX_VALUE; //결과값. 사각지대 최소크기
    static int[][] grid;
    static List<int[]> cctvXy = new ArrayList<>(); //cctv 좌표
    static List<Integer> cctvKind = new ArrayList<>(); //cctv 종류

    //각 방향 
    static int[][][] directions = {
            {}, // 0번은 없음
            {{0}, {1}, {2}, {3}}, // 1번 CCTV
            {{0, 1}, {2, 3}}, // 2번 CCTV
            {{0, 3}, {3, 1}, {1, 2}, {2, 0}}, // 3번 CCTV
            {{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}}, // 4번 CCTV
            {{0, 1, 2, 3}} // 5번 CCTV
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int cell = Integer.parseInt(st.nextToken());
                grid[i][j] = cell;
                if (cell >= 1 && cell <= 5) {
                    cctvXy.add(new int[]{i, j});
                    cctvKind.add(cell);
                }
            }
        }

        findMinimumBlindSpot(0); //0번째 cctv부터 n개 cctv 방향 각각 선택하는 경우
        System.out.println(minBlindSpot);
    }

    //각 조합 찾기
    static void findMinimumBlindSpot(int depth) { //depth번째 cctv 선택
        if (depth == cctvXy.size()) {
            minBlindSpot = Math.min(minBlindSpot, calculateBlindSpot()); //0인 구역 개수 min값 갱신
            return;
        }

        int[] cctv = cctvXy.get(depth); //cctv좌표
        int cctvType = cctvKind.get(depth); //cctv종류

        //이렇게 하면 재귀함수 내부에선 바뀐 grid 사용, 밖에선 이전 grid로 되돌아가기 가능!
        for (int[] direction : directions[cctvType]) { //해당 종류 cctv의 방향
            int[][] originalGrid = copyGrid(grid); //원본 카피해놨다가 밑에서 다시 가져옴
            for (int dir : direction) { //특정 방향으로 쭉 감시
                watch(cctv[0], cctv[1], dir); //grid에 감시내용 표시
            }
            findMinimumBlindSpot(depth + 1); //depth+1번째 cctv 선택
            grid = originalGrid; //원본 다시 grid에 덮어쓰기
        }
    }

    static void watch(int x, int y, int dir) { //감시 영역. dir 인덱스에 해당하는 방향으로 벽or맵 벗어날 때까지 표시
        int nx = x;
        int ny = y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || grid[nx][ny] == 6) {
                break;
            }
            if (grid[nx][ny] == 0) {
                grid[nx][ny] = -1; // 감시 영역 표시 - 0~6은 불가하므로 -1로 표시!!!!!!
            }
        }
    }

    static int calculateBlindSpot() { //0인 구역 개수 찾기
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    static int[][] copyGrid(int[][] original) { //original 배열 받아서 카피본 넘김
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, m);
        }
        return copy;
    }
}
