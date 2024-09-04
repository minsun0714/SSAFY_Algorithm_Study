import java.io.*;
import java.util.*;

//2088ms, 구현
public class SWEA_5648_원자소멸시뮬레이션_한재경_2088ms {
    static int[] dx = {0, 0, -1, 1}; // 상, 하, 좌, 우에 대한 x 변화량
    static int[] dy = {1, -1, 0, 0}; // 상, 하, 좌, 우에 대한 y 변화량
    static int[][] grid = new int[4001][4001];

    static class Atom {
        int x, y, dir, energy;

        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }

        void move() {
            x += dx[dir];
            y += dy[dir];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) {
            int n = Integer.parseInt(br.readLine()); // 원자 개수
            List<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());
                atoms.add(new Atom(x, y, dir, energy));
                grid[x][y] = energy; // 초기 그리드값 세팅
            }

            int totalEnergy = 0;

            while (!atoms.isEmpty()) {
                // 원자들을 이동시키면서 새로운 위치에 추가
                for (int i = atoms.size() - 1; i >= 0; i--) {
                    Atom atom = atoms.get(i);
                    grid[atom.x][atom.y] = 0; // 이전 자리 0으로
                    atom.move(); // 1칸 이동
                    if (atom.x < 0 || atom.x > 4000 || atom.y < 0 || atom.y > 4000) {
                        // 격자 범위 벗어난 경우 처리
                        atoms.remove(i);
                    } else {
                        grid[atom.x][atom.y] += atom.energy; // 다음 자리 에너지 추가
                    }
                }

                // 충돌 감지 및 에너지 합산
                for (int i = atoms.size() - 1; i >= 0; i--) {
                    Atom atom = atoms.get(i);
                    if (grid[atom.x][atom.y] != atom.energy) { // 충돌한 경우
                        totalEnergy += grid[atom.x][atom.y]; // 에너지 합산
                        grid[atom.x][atom.y] = 0; // 해당 자리 에너지 0으로 초기화
                        atoms.remove(i); // 충돌한 원자 제거
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(totalEnergy).append("\n");
        }
        System.out.println(sb);
    }
}
