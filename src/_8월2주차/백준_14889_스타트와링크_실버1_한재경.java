package _8월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//구현, 2152ms
public class 백준_14889_스타트와링크_실버1_한재경 {
    public static Stack<List<Integer>> teams = new Stack<>();

    public static void backTracking(List<Integer> nowList, int nowNum, int lastNum, int full) { //현재팀, 현재넘버, 마지막수 full사이즈
        if (nowList.size() == full) {
            teams.push(new ArrayList<>(nowList)); // 리스트의 복사본을 추가해야 반영됨!
            //nowList를 추가하면 nowList의 참조가 추가되므로, 나중에 nowList가 변경되면 같이 변경됨
            return;
        }
        for (int i = nowNum + 1; i <= lastNum; i++) {
            nowList.add(i);
            backTracking(nowList, i, lastNum, full); //백트래킹
            nowList.remove(nowList.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        //능력치 차이 최소값
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //전체인원
        int[][] s = new int[n][n]; //S[i,j]: i,j가 같은 팀에 속했을 때 더해지는 값

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(new ArrayList<>(), 0, n, n / 2); // 전체 팀 경우의 수 나누기 - 백트래킹
        
        List<List<Integer>> anotherTeam = new ArrayList<>(); //두 팀 분리
        int halfSize = teams.size() / 2;
        for (int i = 0; i < halfSize; i++) {
            anotherTeam.add(teams.pop());
        }

        int minDiff = Integer.MAX_VALUE;
        for (int x = 0; x < teams.size(); x++) { //각 팀의 경우
            int oneSum = 0;
            int twoSum = 0;
            for (int i = 0; i < n; i++) { //s순회
                for (int j = 0; j < n; j++) {
                    if (teams.get(x).contains(i+1) && teams.get(x).contains(j+1)) {
                        oneSum += s[i][j];
                    }
                    if (anotherTeam.get(x).contains(i+1) && anotherTeam.get(x).contains(j+1)){
                        twoSum += s[i][j];
                    }
                }
            }
            minDiff = Math.min(minDiff, Math.abs(oneSum-twoSum));
        }
        System.out.println(minDiff);
    }
}
