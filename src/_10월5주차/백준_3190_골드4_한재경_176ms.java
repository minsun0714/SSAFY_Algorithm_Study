import java.io.*;
import java.util.*;

//큐
public class 백준_3190_골드4_한재경_176ms {
    static int[] dx = {0, 1, 0, -1}; //우하좌상
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //n*n
        int k = Integer.parseInt(br.readLine()); //사과 개수
        int[][] apples = new int[k][2]; //i,j에 사과 위치
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            apples[i][0] = Integer.parseInt(st.nextToken()) - 1;
            apples[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
        int l = Integer.parseInt(br.readLine()); //방향 변환 횟수
        int[][] dirs = new int[l][2]; //i초 후에 j방향으로
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dirs[i][0] = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            if (Objects.equals(d, "L")) {
                dirs[i][1] = 0; //왼쪽 회전: 0
            } else {
                dirs[i][1] = 1; //오른쪽 회전: 1
            }
        }

        int[][] grid = new int[n][n]; //사과 1, 뱀 2
        for (int[] apple : apples) {
            grid[apple[0]][apple[1]] = 1;
        }
        grid[0][0] = 2;
        //선입선출 큐!!
        Queue<String> q = new ArrayDeque<>(); // "i j" (i, j)위치 큐
        q.add("0 0"); //q.poll() 시 꼬리 잘림

        int time = 0; //시간
        int dir = 0; //우하좌상, 시작방향: 오른쪽
        int idx = 0; //방향전환 idx
        int[] now = new int[]{0, 0}; //현재 머리 위치

        while(true) {
            int nx = now[0] + dx[dir];
            int ny = now[1] + dy[dir];
            time++; //1초 증가
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || grid[nx][ny] == 2) { //벽or몸: end
                break;
            } else if (grid[nx][ny] == 0) { //빈칸: 꼬리잘림
                String[] bxby = q.poll().split(" "); //큐에서 빼고
                int bx = Integer.parseInt(bxby[0]);
                int by = Integer.parseInt(bxby[1]);
                grid[bx][by] = 0; //맵에 빈칸 표시
            }
            //머리 한 칸 앞으로
            q.add(nx + " " + ny); //큐에 넣고
            grid[nx][ny] = 2; //맵에 뱀 표시하고
            now = new int[]{nx, ny}; //현재 머리 위치 변경

            //방향 전환
            if (idx < l && dirs[idx][0] == time) {
                if (dirs[idx][1] == 0) { //왼쪽 90도
                    dir = dir - 1;
                    if (dir == -1) {
                        dir = 3;
                    }
                } else { //오른쪽 90도
                    dir = dir + 1;
                    if (dir == 4) {
                        dir = 0;
                    }
                }
                idx++;
            }
        }
        System.out.println(time);
    }
}
