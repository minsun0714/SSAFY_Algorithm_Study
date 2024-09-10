import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//952ms, 구현
public class 백준_20055_컨베이어벨트위의로봇_골드5_한재경 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //벨트 반띵 n개
        int k = Integer.parseInt(st.nextToken()); //내구도 0인 칸 개수 k 이상 -> 과정 종료
        int[] a = new int[n * 2]; //i번칸 내구도
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n * 2; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> robots = new ArrayList<>(); //로봇 인덱스
        int result = 1;
        int start = 0; //올리는 위치
        int getOut = n - 1; //내리는 위치: start + n - 1

        while (true) {
            //1칸회전: 벨트 + 1
            //벨트 이동
            start = (start - 1 + 2 * n) % (2 * n); //올리는 위치 이동
            getOut = (getOut - 1 + 2 * n) % (2 * n); //내리는 위치 이동

            //로봇은 지정된 벨트 idx에 가만히 있으므로 이동할 필요x
            //내리는 위치에 도달한 경우만 계산
            for (int i = 0; i < robots.size(); i++) {
                if (robots.get(i) == getOut) { //내리는 위치 도달 시
                    robots.remove(i); //해당 인덱스 로봇 삭제
                    break;
                }
            }

            //가장 먼저 올라간 로봇부터 회전방향으로 이동가능시 +1
            //로봇들 자리 +1 이동
            int removeRobot = -1; //내릴 로봇 인덱스
            for (int i = 0; i < robots.size(); i++) {
                int nxt = (robots.get(i) + 1) % (2 * n); //이동할 자리 인덱스
                
                //(이동칸에 로봇 없고 내구도 >= 1 시 이동 가능
                if (!robots.contains(nxt) && a[nxt] >= 1) {
                    a[nxt]--; //이동칸 내구도 감소

                    if (nxt != getOut) { //다음 칸이 내리는 칸 아니면
                        robots.set(i, nxt); //한 칸 이동
                    } else { //내리는 칸이면 없애줘야 함
                        robots.set(i, nxt); //한 칸 이동
                        removeRobot = i;
                    }
                }
            }

            if (removeRobot >= 0) { //내리는 로봇 있으면
                robots.remove(removeRobot); //해당 인덱스 로봇 삭제
            }

            //올리는 위치 내구도 > 0 : 로봇 추가
            if (a[start] > 0 && !robots.contains(start)) {
                robots.add(start);
                a[start]--; //이동칸 내구도 감소
            }

            int cnt = 0;
            //내구도 0인 칸 개수 k 이상 -> 과정 종료
            for (int i : a) {
                if (i == 0) {
                    cnt++;
                }
            }
            if (cnt >= k) {
                break;
            }
            //종료시 몇 단계 진행 중이었는지
            result++;
        }
        System.out.println(result);
    }
}
