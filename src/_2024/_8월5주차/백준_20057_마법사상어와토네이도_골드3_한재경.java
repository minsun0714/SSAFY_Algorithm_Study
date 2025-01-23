import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//468ms, 구현, 브루트포스
public class 백준_20057_마법사상어와토네이도_골드3_한재경 {
    static int[][] grid;
    static int n;
    static int outSand = 0;

    //왼밑오위 순서
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int[][] xSand = {{-1, 1, -2, 2, -1, 1, -1, 1, 0}, {0, 0, 1, 1, 1, 1, 2, 2, 3},
            {-1, 1, -2, 2, -1, 1, -1, 1, 0}, {0, 0, -1, -1, -1, -1, -2, -2, -3}};
    static int[][] ySand = {{0, 0, -1, -1, -1, -1, -2, -2, -3}, {-1, 1, -2, 2, -1, 1, -1, 1, 0},
            {0, 0, 1, 1, 1, 1, 2, 2, 3}, {-1, 1, -2, 2, -1, 1, -1, 1, 0}};

    static int[] per = {1, 1, 2, 2, 7, 7, 10, 10, 5}; //대응되는 %값

    static void tornado() {
        int nowx = n / 2;
        int nowy = n / 2;
        int step = 1;
        while (true) {
            for (int i = 0; i < 4; i++) { //4방향 좌하우상 이동
                for (int s = 0; s < step; s++) { //한 방향으로 step만큼 반복 이동
                    int sumSand = 0; //모든 구역에 날린 모래 양
                    for (int p = 0; p < 9; p++) { //9개 구역 모래날림
                        int nx = nowx + xSand[i][p];
                        int ny = nowy + ySand[i][p];
                        int movedSand = grid[nowx + dx[i]][nowy + dy[i]] * per[p] / 100; //이번 구역에 날아가는 모래 양
                        if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                            grid[nx][ny] += movedSand;
                        } else { //구역 밖이면 answer+
                            outSand += movedSand;
                        }
                        sumSand += movedSand;
                    }
                    //a구역 모래날림: 원래 모래 - 날린모래(sumSand)
                    int ax = nowx + dx[i] * 2;
                    int ay = nowy + dy[i] * 2;
                    if (0 <= ax && ax < n && 0 <= ay && ay < n) {
                        grid[ax][ay] += grid[nowx + dx[i]][nowy + dy[i]] - sumSand;
                    } else { //구역 밖이면 answer+
                        outSand += grid[nowx + dx[i]][nowy + dy[i]] - sumSand;
                    }
                    //x, y 이동
                    nowx += dx[i];
                    nowy += dy[i];
                    grid[nowx][nowy] = 0;
                    if (nowx == 0 && nowy == 0) {
                        return;
                    }
                }
                if (i == 1 || i == 3) { //밑, 위 이동 후 스텝 조정
                    step++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tornado();

        System.out.println(outSand);
    }
}