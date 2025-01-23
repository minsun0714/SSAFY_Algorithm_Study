package _2024._8월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백트래킹, 464ms
public class 백준_15686_치킨배달_골드5_한재경 {
    static int minCityDist = Integer.MAX_VALUE;

    //cnt가 dieNum 될 때까지 백트래킹
    static void pickChicken(int[][] map, List<int[]> dieXy, int n, int dieNum, int x, int y) {
        if (dieXy.size() == dieNum) { //치킨집 없앨 만큼 모았당
            //해당 치킨집들 0으로 만들고
            int[][] copyMap = new int[map.length][map[0].length]; //0으로 만든 맵은 딥카피맵으로!
            for (int i = 0; i < copyMap.length; i++) { //이중배열 딥카피
                System.arraycopy(map[i], 0, copyMap[i], 0, copyMap[i].length);
            }

            for (int[] xy : dieXy) { //치킨집 부수기 - 2를 0으로
                copyMap[xy[0]][xy[1]] = 0;
            }

            //치킨거리 계산하쟈
            //이중for문에 각 1좌표 넘기기
            int cityDist = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (copyMap[i][j] == 1) {
                        cityDist += calculateDist(copyMap, n, i, j);
                    }
                }
            }
            minCityDist = Math.min(cityDist, minCityDist);
            return;
        }
        for (int i = x; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == x && j < y) {
                    continue;
                }
                if (map[i][j] == 2) { //치킨집 발견! 없애자!

                    dieXy.add(new int[]{i, j});
                    //다음칸부터 진행
                    if (j == n - 1) { //막열이면 행 변경
                        pickChicken(map, dieXy, n, dieNum, i + 1, 0);
                    }
                    else { //마지막 열 아니면 j+1
                        pickChicken(map, dieXy, n, dieNum, i, j + 1);
                    }
                    dieXy.remove(dieXy.size() - 1); //백트래킹
                }
            }
        }
    }

    //치킨집들 없앤 뒤 치킨거리
    static int calculateDist(int[][] map, int n, int x, int y) {
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 2) {
                    int dist = Math.abs(x - i) + Math.abs(y - j);
                    minDist = Math.min(minDist, dist);
                }
            }
        }
        return minDist;
    }

    public static void main(String[] args) throws IOException {
        //치킨거리: 가장 근접한 치킨집과의 거리
        //도시의 치킨 거리: 모든 집의 치킨거리 합
        //최대 m개 치킨집 폐업 안 시킬 때 도시의 치킨거리 최솟값
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //n*n맵
        int m = Integer.parseInt(st.nextToken()); //최대 m개 치킨집 폐업x
        int[][] map = new int[n][n]; //도시 맵: 0빈칸 1집 2치킨집
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //백트래킹 - 조합
        int cnum = 0; //치킨집 총 개수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 2) {
                    cnum++;
                }
            }
        }
        int dieNum = cnum - m; //폐업시킬 치킨집 개수
        pickChicken(map, new ArrayList<>(), n, dieNum, 0, 0);
        System.out.println(minCityDist);

    }
}
