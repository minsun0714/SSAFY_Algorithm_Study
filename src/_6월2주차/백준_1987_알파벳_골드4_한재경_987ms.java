package org.example;

import java.io.*;
import java.util.*;

// dfs + 백트래킹
public class 백준_1987_알파벳_골드4_한재경_987ms {
    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{-1, 1, 0, 0};
    static int r;
    static int c;
    static String[] arr;
    static boolean[] check; //26개 알파벳 체크
    static int ans;

    static public void dfs(int x, int y, int depth) {
        ans = Math.max(ans, depth);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < r && 0 <= ny && ny < c &&
            !check[arr[nx].charAt(ny) - 'A']) {
                check[arr[nx].charAt(ny) - 'A'] = true;
                dfs(nx, ny, depth+1);
                check[arr[nx].charAt(ny) - 'A'] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new String[r];
        ans = 0;

        for (int i = 0; i < r; i++) {
            arr[i] = br.readLine();
        }

        check = new boolean[26]; //각 알파벳 체크
        check[arr[0].charAt(0) - 'A'] = true;

        dfs(0, 0, 1);
        System.out.println(ans);
    }
}
