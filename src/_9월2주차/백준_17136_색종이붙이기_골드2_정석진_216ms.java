import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_17136_색종이붙이기_골드2_정석진_216ms {
    public static int[][] map = new int[10][10];
    public static int[] paper = new int[6];
    public static int result = Integer.MAX_VALUE; // 최소 값을 찾기 위해 초기값을 큰 값으로 설정

    // 주어진 좌표에 size x size 크기의 종이를 놓을 수 있는지 확인
    public static boolean canPlace(int r, int c, int size) {
        if (r + size > 10 || c + size > 10) return false; // 범위를 벗어나는지 확인
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[r + i][c + j] != 1) return false; // 종이가 모두 1을 덮는지 확인
            }
        }
        return true;
    }

    // 주어진 좌표에 size x size 크기의 종이를 놓는 함수
    public static void place(int r, int c, int size, int value) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[r + i][c + j] = value; // 종이를 놓거나 제거 (1 -> 0, 0 -> 1)
            }
        }
    }

    // 백트래킹 함수
    public static void backtrack(int r, int c, int count, int num) {
        // 만약 남은 1이 없다면 종료
        if (count == 0) {
            result = Math.min(result, num);
            return;
        }

        // 좌표가 범위를 벗어나면 종료
        if (r >= 10 || c >= 10) return;

        // 현재 좌표가 1이 아니라면 다음 좌표로 이동
        if (map[r][c] == 0) {
            if (c + 1 < 10) {
                backtrack(r, c + 1, count, num);
            } else {
                backtrack(r + 1, 0, count, num);
            }
            return;
        }

        // 1인 곳을 찾았을 때, 5x5부터 1x1까지 종이를 덮는 시도
        for (int size = 5; size >= 1; size--) {
            if (paper[size] > 0 && canPlace(r, c, size)) {
                place(r, c, size, 0); // 종이를 덮는다
                paper[size]--; // 종이 사용
                backtrack(r, c, count - size * size, num + 1); // 재귀 호출
                paper[size]++; // 종이 복구
                place(r, c, size, 1); // 종이를 다시 원상복구
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 및 초기화
        int count = 0;
        for (int r = 0; r < 10; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 10; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    count++; // 1의 개수를 카운트
                }
            }
        }

        // 각 크기의 종이 개수는 5장씩
        for (int i = 1; i <= 5; i++) {
            paper[i] = 5;
        }

        // 백트래킹 시작
        backtrack(0, 0, count, 0);

        // 결과 출력
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1); // 덮을 수 없는 경우
        } else {
            System.out.println(result); // 최소 종이 개수 출력
        }
    }
}
