import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//188ms, 구현
public class 백준_17822_원판돌리기_골드2_한재경_188ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //반지름 i -> i번째 원판
        //끝과 끝 연결
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //n개 원판
        int m = Integer.parseInt(st.nextToken()); //각 원판에 m개 정수
        int t = Integer.parseInt(st.nextToken()); //t번 회전
        int[][] onePan = new int[n + 1][m]; //순환배열. 0번째 원판은 0으로 초기화
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                onePan[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] start = new int[n + 1]; //각 원판의 시작점 열 인덱스

        //회전
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); //번호가 x 배수인 원판
            int d = Integer.parseInt(st.nextToken()); //d 방향으로 (0시계, 1반시계)
            int k = Integer.parseInt(st.nextToken()); //k 칸 회전

            switch (d) {
                case 0: //시계방향
                    for (int i = x; i <= n; i+=x) {
                        start[i] = (m + start[i] - k) % m; //start 지점 갱신
                    }
                    break;
                case 1: //반시계방향
                    for (int i = x; i <= n; i+=x) {
                        start[i] = (start[i] + k) % m; //start 지점 갱신
                    }
                    break;
            }

            //인접 같은 수 찾기
            Set<String> sameNums = new HashSet<>(); //같은 수 xy좌표들
            //동일행 비교
            for (int i = 1; i <= n; i++) {
                int idx = start[i]; //해당 행 시작점
                for (int j = 0; j < m; j++) {
                    int current = (idx + j) % m;
                    int next = (idx + j + 1) % m;
                    //해당 열 양 옆 비교
                    if (onePan[i][current] != 0 && onePan[i][current] == onePan[i][next]) {
                        //같은 수 있는 경우
                        sameNums.add(i + "," + current);
                        sameNums.add(i + "," + next);
                    }
                }
            }

            //동일열 비교
            for (int i = 1; i < n; i++) { //i행
                for (int j = 0; j < m; j++) { //j번째 칸
                    int oneIdx = (start[i] + j) % m; //i 행의 열 idx
                    int twoIdx = (start[i + 1] + j) % m; //i+1 행의 열 idx
                    if (onePan[i][oneIdx] != 0 && onePan[i][oneIdx] == onePan[i+1][twoIdx]) {
                        sameNums.add(i + "," + oneIdx);
                        sameNums.add((i + 1) + "," + twoIdx);
                    }
                }
            }

            if (!sameNums.isEmpty()) { //인접한 같은 수 있는 경우: 모두 지우기
                for (String xy : sameNums) {
                    String[] xyStr = xy.split(",");
                    int xx = Integer.parseInt(xyStr[0]);
                    int yy = Integer.parseInt(xyStr[1]);
                    onePan[xx][yy] = 0;
                }
            } else { //없는 경우: 적힌 수 평균 구하고, 평균보다 큰 수들-1, 작은수들+1
                int sum = 0;
                int cnt = 0;
                for (int i = 1; i <= n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (onePan[i][j] != 0) {
                            sum += onePan[i][j];
                            cnt++;
                        }
                    }
                }
                if (cnt == 0) {
                    break;
                }
                double avg = (double) sum / cnt;
                for (int i = 1; i <= n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (onePan[i][j] == 0) {
                            continue;
                        }
                        if (onePan[i][j] > avg) {
                            onePan[i][j]--;
                        } else if (onePan[i][j] < avg) {
                            onePan[i][j]++;
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                result += onePan[i][j];
            }
        }
        System.out.println(result);
        //회전 후 적힌 수 합
    }
}