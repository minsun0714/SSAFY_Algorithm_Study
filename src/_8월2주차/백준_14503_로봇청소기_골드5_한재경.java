package _8월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//구현, 104ms
public class 백준_14503_로봇청소기_골드5_한재경 {
    public static int[] dx = {-1, 0, 1, 0}; //북동남서 (반시계)
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()); //현재x
        int y = Integer.parseInt(st.nextToken()); //현재y
        int d = Integer.parseInt(st.nextToken()); //현재 방향
        int[][] map = new int[n][m];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            //현재칸 청소 안됐으면 청소
            if (map[x][y] == 0) {
                map[x][y] = 2; //청소된 칸 2로!
                cnt++;
            }
            //주변에 청소안된칸 여부 확인
            boolean isDirty = false;
            for (int i = 0; i < 4; i++) {
                int newD = (d + 3) % 4; //여기 왜 (d + i + 1) % 4는 안됨? - 52번라인 삭제랑 함께
                int nx = x + dx[newD];
                int ny = y + dy[newD];
                //청소 안 된 칸 있으면 전진
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    d = newD;
                    isDirty = true;
                    break;
                }
                d = newD; //요기도 지우고
            }

            if (!isDirty) { //청소안된칸 없으면
                //후진가능하면 후진하고 다시 처음부터
                int nx = x - dx[d];
                int ny = y - dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != 1) {
                    x = nx;
                    y = ny;
                } else {
                    break; //후진 불가시 중단
                }
            }
        }

        System.out.println(cnt);
    }
}
