package _8월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//구현, 364ms (2152ms에서 최적화)
public class 백준_14889_스타트와링크_실버1_한재경 {
    public static int minDiff = Integer.MAX_VALUE;

    public static void backTracking(List<Integer> nowTeam, int nowNum, int lastNum, int full, int[][] s) { //현재팀, 현재넘버, 마지막수 full사이즈
        if (nowTeam.size() == full) {
            List<Integer> anotherTeam = new ArrayList<>();
            for (int i = 1; i <= lastNum; i++) {
                if (!nowTeam.contains(Integer.valueOf(i))) {
                    anotherTeam.add(Integer.valueOf(i));
                }
            }
            int diff = calculateDiff(nowTeam, anotherTeam, s);
            minDiff = Math.min(minDiff, diff);
            return;
        }
        for (int i = nowNum + 1; i <= lastNum; i++) {
            nowTeam.add(Integer.valueOf(i));
            backTracking(nowTeam, i, lastNum, full, s); //백트래킹
            nowTeam.remove(nowTeam.size() - 1);
        }
    }

    public static int calculateDiff(List<Integer> nowTeam, List<Integer> anotherTeam, int[][] s) {
        int osum = 0, tsum = 0;
        for (int i = 0; i < nowTeam.size(); i++) { //각 팀에서 두 명 고르기
            for (int j = 0; j < nowTeam.size(); j++) {
                if (i != j) {
                    osum += s[nowTeam.get(i) - 1][nowTeam.get(j) - 1];
                    tsum += s[anotherTeam.get(i) - 1][anotherTeam.get(j) - 1];
                }
            }
        }
        return Math.abs(osum - tsum);
    }

    public static void main(String[] args) throws IOException {
        //능력치 차이 최소값
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //전체인원
        int[][] s = new int[n][n]; //S[i,j]: i,j가 같은 팀에 속했을 때 더해지는 값

        for (int i = 0; i < n; i++) { //s배열 채우기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(new ArrayList<>(), 0, n, n / 2, s); // 전체 팀 경우의 수 나누기 - 백트래킹

        System.out.println(minDiff);
    }
}
