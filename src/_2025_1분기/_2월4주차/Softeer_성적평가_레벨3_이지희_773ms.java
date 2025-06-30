package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Softeer_성적평가_레벨3_이지희_773ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] totalScore = new int[N+1];

        for(int i=0; i<3; i++) {
            // 점수, 선수리스트
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            int[] score = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for(int n=1; n<=N; n++) {
                score[n] = Integer.parseInt(st.nextToken());
                ArrayList<Integer> candis = map.getOrDefault(score[n], new ArrayList<Integer>());
                candis.add(n);
                map.put(score[n], candis);
                totalScore[n] += score[n];
            }

            Arrays.sort(score);
            int[] order = new int[N+1];
            ArrayList<Integer> tops = map.get(score[N]); // 최고점
            for(int top : tops) {
                order[top] = 1;//1등
            }
            for(int n=2; n<=N; n++) {
                int s = score[N-n+1];
                if(s == score[N-n+2]) {
                    continue;
                }

                ArrayList<Integer> cadis = map.get(s);
                for(int cadi : cadis) {
                    order[cadi] = n;
                }
            }

            for(int n=1; n<=N; n++) {
                sb.append(order[n]).append(" ");
            }
            sb.append("\n");
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int n=1; n<=N; n++) {
            ArrayList<Integer> candis = map.getOrDefault(totalScore[n], new ArrayList<Integer>());
            candis.add(n);
            map.put(totalScore[n], candis);
        }

        Arrays.sort(totalScore);
        int[] order = new int[N+1];
        ArrayList<Integer> tops = map.get(totalScore[N]); // 최고점
        for(int top : tops) {
            order[top] = 1;//1등
        }
        for(int n=2; n<=N; n++) {
            int s = totalScore[N-n+1];
            if(s == totalScore[N-n+2]) {
                continue;
            }

            ArrayList<Integer> cadis = map.get(s);
            for(int cadi : cadis) {
                order[cadi] = n;
            }
        }

        for(int n=1; n<=N; n++) {
            sb.append(order[n]).append(" ");
        }

        System.out.println(sb.toString());
        br.close();
    }

}
