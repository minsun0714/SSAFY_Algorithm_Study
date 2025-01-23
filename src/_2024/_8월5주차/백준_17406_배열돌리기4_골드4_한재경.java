import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//180ms, 순열, 구현
public class 백준_17406_배열돌리기4_골드4_한재경 {
    static int[][] grid;
    static int lutmp = 0; // 네 모서리값 저장
    static int rutmp = 0;
    static int ldtmp = 0;
    static int rdtmp = 0;
    static List<int[]> turnCases = new ArrayList<>(); //rcs 인덱스 순열
    static int minSum = Integer.MAX_VALUE;

    static void setMinSum(int n, int m) {
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < m; j++) {
                rowSum += grid[i][j];
            }
            minSum = Math.min(rowSum, minSum);
        }
    }

    // k개 중에 k개 고르는 순열
    static void permTurnCases(int k, int depth, boolean[] visited, int[] output){
        if (depth == k) {
            turnCases.add(Arrays.copyOf(output, k));
            return;
        }
        for (int i = 0; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = i;
                permTurnCases(k, depth + 1, visited, output);
                visited[i] = false;
            }
        }
    }

    // 초기에 좌상단 꼭짓점 tmp 지정하고 시작
    static void initLutmp(int r, int c, int depth) { //0 ~ s까지가 depth
        lutmp = grid[r - depth][c - depth];
    }

    static void upTurn(int r, int c, int s) {
        int pretmp = 0;
        int posttmp = 0;
        for (int j = c - s; j < c + s; j++) {
            if (j == c + s - 1) { //모서리 마지막 값
                rutmp = grid[r - s][j + 1];
            } else {
                posttmp = grid[r - s][j + 1];
            }
            if (j == c - s) { //모서리 첫 값
                grid[r - s][j + 1] = lutmp;
            } else {
                grid[r - s][j + 1] = pretmp;
            }
            pretmp = posttmp;
        }
    }

    static void rightTurn(int r, int c, int s) {
        int pretmp = 0;
        int posttmp = 0;
        for (int i = r - s; i < r + s; i++) {
            if (i == r + s - 1) { //모서리 마지막 값
                rdtmp = grid[i + 1][c + s];
            } else {
                posttmp = grid[i + 1][c + s];
            }
            if (i == r - s) { //모서리 첫 값
                grid[i + 1][c + s] = rutmp;
            } else {
                grid[i + 1][c + s] = pretmp;
            }
            pretmp = posttmp;
        }
    }

    static void downTurn(int r, int c, int s) {
        int pretmp = 0;
        int posttmp = 0;
        for (int j = c + s; j > c - s; j--) {
            if (j == c - s + 1) { //모서리 마지막 값
                ldtmp = grid[r + s][j - 1];
            } else {
                posttmp = grid[r + s][j - 1];
            }
            if (j == c + s) { //모서리 첫 값
                grid[r + s][j - 1] = rdtmp;
            } else {
                grid[r + s][j - 1] = pretmp;
            }
            pretmp = posttmp;
        }
    }

    static void leftTurn(int r, int c, int s) {
        int pretmp = 0;
        int posttmp = 0;
        for (int i = r + s; i > r - s; i--) {
            if (i == r - s) { //모서리 마지막 값
                lutmp = grid[i - 1][c - s];
            } else {
                posttmp = grid[i - 1][c - s];
            }
            if (i == r + s) { //모서리 첫 값
                grid[i - 1][c - s] = ldtmp;
            } else {
                grid[i - 1][c - s] = pretmp;
            }
            pretmp = posttmp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //행 개수
        int m = Integer.parseInt(st.nextToken()); //열 개수
        int k = Integer.parseInt(st.nextToken()); //회전 횟수
        int initGrid[][] = new int[n][m];
        grid = new int[n][m];
        int[][] rcs = new int[k][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                initGrid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int turn = 0; turn < k; turn++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; //중심행
            int c = Integer.parseInt(st.nextToken()) - 1; //중심열
            int s = Integer.parseInt(st.nextToken()); //+s -s 영역
            rcs[turn] = new int[]{r, c, s};
        }

        //돌리는 순서 모든 경우 순열
        permTurnCases(k, 0, new boolean[k], new int[k]);

        //모든 경우의 rcs 탐색
        //(r, c)를 중심으로 +-s 만큼의 크기 정사각형 시계방향으로 한 칸씩 회전

        for (int[] turnRcs : turnCases) { // 모든 턴 순열 케이스
            for (int i = 0; i < n; i++) { // 매 턴마다 그리드 원상복귀
                grid[i] = Arrays.copyOf(initGrid[i], m);
            }
            for (int turn : turnRcs) { //해당 순열 하나씩 진행
                int r = rcs[turn][0];
                int c = rcs[turn][1];
                int s = rcs[turn][2];

                for (int i = 1; i <= s; i++) {
                    initLutmp(r, c, i);
                    upTurn(r, c, i);
                    rightTurn(r, c, i);
                    downTurn(r, c, i);
                    leftTurn(r, c, i);
                }
            }
            //배열값(행 최소합) 구하기
            setMinSum(n, m);
        }
        System.out.println(minSum);
    }
}
