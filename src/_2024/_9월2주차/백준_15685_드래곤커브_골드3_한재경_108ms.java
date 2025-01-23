import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//108ms, DP
public class 백준_15685_드래곤커브_골드3_한재경_108ms {
    static int[] dx = {0, -1, 0, 1}; //우상좌하
    static int[] dy = {1, 0, -1, 0};
    static List<List<Integer>> dp;
    static boolean[][] grid;

    //dp[i]: dp[i-1] + dp[i-1] 90도 회전해서 꼬리에 붙이기
    static void setLines(int num) { // num세대 드래곤커브
        //이전lines 뒤에서부터 넣어야 함!!
        List<Integer> preLines = dp.get(num - 1); //dp[i-1]
        List<Integer> lines = new ArrayList<>(preLines); // 초기화: dp[i-1]

        //dp[i-1] 뒤에서부터 시계 90도 회전해서 추가
        for (int i = preLines.size() - 1; i >= 0; i--) {
            lines.add((preLines.get(i) + 1) % 4);
        }
        dp.add(lines);
    }

    //회전
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //드래곤커브 겹치기 가능!!
        //k세대 드래곤 커브: K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시켜 끝 점에 붙인 것
        //100*100 grid, 드래곤커브 n개, 네 꼭짓점 모두 드래곤커브 일부인 정사각형 개수
        int n = Integer.parseInt(br.readLine()); //드래곤 커브 개수
        int[][] curves = new int[n][4];
        int rc = 101;
        grid = new boolean[rc][rc]; //드래곤커브 일부 유무 체크

        dp = new ArrayList<>(); //i세대 드래곤 커브 선분 방향리스트
        dp.add(new ArrayList<>()); //시작점 초기화
        dp.get(0).add(0);

        //dp[i]: dp[i-1] + dp[i-1] 90도 회전해서 꼬리에 붙이기
        for (int i = 1; i <= 10; i++) { //세대 최대 10
            setLines(i);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); //초기방향
            int g = Integer.parseInt(st.nextToken()); //세대
            grid[x][y] = true; //시작점 칠하기

            //g세대 드래곤커브 방문지점 칠하기
            for (Integer line : dp.get(g)) { //각 선분 방향값
                int dir = (line + d) % 4; //다음 방향으로
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                grid[nx][ny] = true; //해당 점 칠하기
                x = nx;
                y = ny;
            }
        }
        int result = 0;
        for (int i = 0; i < rc - 1; i++) {
            for (int j = 0; j < rc - 1; j++) {
                if (grid[i][j] && grid[i][j + 1] && grid[i + 1][j] && grid[i + 1][j + 1]) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
