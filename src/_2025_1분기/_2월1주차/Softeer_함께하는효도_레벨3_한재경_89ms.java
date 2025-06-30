import java.io.*;
import java.util.*;

//dfs
//1명 dfs 끝난 내부에서 2번 dfs -
public class Softeer_함께하는효도_레벨3_한재경_89ms {
    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[][] visited, grid, mans;
    static int n, m, sum;
    
    public static void dfs(int depth, int x, int y, int total, int manIdx) {
        if (depth == 3) { //3초동안 움직임
            sum = Math.max(sum, total);
            if (manIdx < m - 1) { //마지막 사람 아니면
                dfs(0, mans[manIdx+1][0], mans[manIdx+1][1], total, manIdx+1); //다음 사람 진행
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            //자신의 이동경로가 아닐 땐 지나갈 수 있음!
            if (0 <= nx && nx < n && 0 <= ny && ny < n && visited[nx][ny] != manIdx + 1) {
                if (visited[nx][ny] > 0) { //다른 사람이 이미 이동한 경로일 경우
                    int before = visited[nx][ny];
                    visited[nx][ny] = manIdx + 1;
                    dfs(depth + 1, nx, ny, total, manIdx); //total 그대로
                    visited[nx][ny] = before; //이전 발자국 되돌리기
                } else {
                    visited[nx][ny] = manIdx + 1;
                    dfs(depth + 1, nx, ny, total + grid[nx][ny], manIdx);
                    visited[nx][ny] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //n*n
        m = Integer.parseInt(st.nextToken()); //사람수
        grid = new int[n][n]; //맵
        mans = new int[m][2]; //사람들 [x, y]
        visited = new int[n][n];
        int initSum = 0; //시작지점은 모두 total에 포함
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            mans[i][0] = x;
            mans[i][1] = y;
            visited[x][y] = i + 1; //무조건 방문
            initSum += grid[x][y]; //시작지점은 모두 total에 포함
        }
        
        sum = initSum; //최대합 초기값 세팅
        dfs(0, mans[0][0], mans[0][1], initSum, 0);
        System.out.println(sum);
    }
}
