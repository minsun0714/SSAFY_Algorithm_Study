import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//288ms, 구현
public class 백준_17144_미세먼지안녕_골드4_한재경_288ms {
    static int[] dx = {-1, 0, 1, 0}; //상우하좌 (시계)
    static int[] dy = {0, 1, 0, -1};
    static int r;
    static int c;
    static int[][] grid;
    static int[][] newGrid; //확산된 미세먼지

    //미세먼지 확산
    static void extendDust(int x, int y, int airUpX, int airUpY, int airDownX, int airDownY) { //공청기 x,y좌표
        int outDusts = 0; //날아간 미세먼지 양

        for (int i = 0; i < 4; i++) { //인접 4방향
            int nx = x + dx[i];
            int ny = y + dy[i];
            //공청기 있으면 확산 x
            if (0 <= nx && nx < r && 0 <= ny && ny < c
                    && !(airUpX == nx && airUpY == ny) && !(airDownX == nx && airDownY == ny)) {
                int outDust = grid[x][y] / 5; // 원래양 / 5로 확산
                outDusts += outDust;
                newGrid[nx][ny] += outDust;
            }
        }
        grid[x][y] -= outDusts; //남은 미세먼지: 원래양 - 확산된양
    }

    //바람 순환
    static void cycleAir(int airX, int airY, boolean isUp) { //위쪽바람은 true 아래쪽은 false
        //역방향 탐색
        //미세먼지가 바람 방향대로 1칸씩 이동
        //공청기에 들어가면 소멸
        int dir = 0;
        int botBoundary = r - 1; //아래 경계
        int topBoundary = 0; //위 경계
        if (!isUp) { //아래쪽이면
            dir = 2; //방향 2부터 시작
            topBoundary = airX; //위쪽 경계는 자신 행
        } else { //위쪽이면
            botBoundary = airX; //아래쪽 경계는 자신 행
        } 
        int x = airX + dx[dir]; //초기에 1칸 더 이동(xy가 공청기인 경우 먼지 소멸)
        int y = airY + dy[dir];
        while (true) {
            int nx = x + dx[dir]; //nx,ny 한 칸 이동
            int ny = y + dy[dir];
            if (nx < topBoundary || nx > botBoundary || ny < 0 || ny >= c) { //범위 벗어나면
                if (isUp) {
                    dir = (dir + 1) % 4; //방향 꺾고
                } else {
                    dir = (dir + 3) % 4;
                }
                continue; //해당 방향으로 진행
            }
            if (grid[nx][ny] == -1) { //공청기 자리까지 돌아오면
                grid[x][y] = 0; //해당 자리 0으로 하고
                break; //스탑
            }
            //범위 안이면 nx ny가 xy로 옴
            grid[x][y] = grid[nx][ny];
            x = nx; //x,y 한 칸 이동
            y = ny;
        }
    }

    public static void main(String[] args) throws IOException {
        //공청기 0번열, 2행차지
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        grid = new int[r][c]; //공청기는 -1로 표시
        int[][] air = new int[][]{{-1, -1}, {-1, -1}}; //공청기 위, 아래 xy 좌표
        
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == -1) {
                    if (air[0][0] == -1) { //아직 위 좌표 들어가기 전이면
                        air[0][0] = i;
                        air[0][1] = j;
                    } else { //위 좌표 들어갔으면
                        air[1][0] = i;
                        air[1][1] = j;
                    }
                }
            }
        }

        for (int time = 0; time < t; time++) {
            //미세먼지 확산
            newGrid = new int[r][c]; //초기화
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    //모든 미세먼지 확산
                    if (grid[i][j] > 0) {
                        extendDust(i, j, air[0][0], air[0][1], air[1][0], air[1][1]);
                    }
                }
            }

            //확산된 미세먼지 newGrid를 다시 grid에 합치기
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    grid[i][j] += newGrid[i][j];
                }
            }

            //공청기 작동
            cycleAir(air[0][0], air[0][1], true); //위쪽 공청기: 반시계 방향 바람
            cycleAir(air[1][0], air[1][1], false); //아래쪽 공청기: 시계 방향 바람
        }

        int dustSum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] != -1) {
                    dustSum += grid[i][j];
                }
            }
        }
        System.out.println(dustSum);
    }
}
