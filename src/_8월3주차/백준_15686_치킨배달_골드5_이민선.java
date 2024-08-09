package _8월3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 180ms
// 백트래킹
public class 백준_15686_치킨배달_골드5_이민선 {
    static List<int[]> homes = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][n];




        for (int i=0;i<n;i++){
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int j=0;j<n;j++){
                int next = Integer.parseInt(st.nextToken());
                if (next == 1) homes.add(new int[] {i, j});
                else if (next == 2) chickens.add(new int[] {i, j});
            }
        }

        selectChickenSurvive(new Stack<>(), 0, 0, n, m);
        System.out.println(answer);
    }

    public static void selectChickenSurvive(Stack<int[]> selected, int depth, int start, int n, int m){
        if (depth == m){
            int result = getChickenDistance(selected);
            answer = Math.min(answer, result);
            return;
        }
        for (int i=start;i<chickens.size();i++){
            selected.push(chickens.get(i));
            selectChickenSurvive(selected, depth + 1, i + 1, n, m);
            selected.pop();
        }
    }

    public static int getChickenDistance(Stack<int[]> selected){
        int chickenDistance = 0;
        for (int[] h: homes){
            int shortestDistance = Integer.MAX_VALUE;
            int r1 = h[0];
            int c1 = h[1];

            for (int[] c: selected){
                int r2 = c[0];
                int c2 = c[1];

                shortestDistance = Math.min(shortestDistance, Math.abs(r1 - r2) + Math.abs(c1 - c2));
            }
            chickenDistance += shortestDistance;
        }
        return chickenDistance;
    };
}
