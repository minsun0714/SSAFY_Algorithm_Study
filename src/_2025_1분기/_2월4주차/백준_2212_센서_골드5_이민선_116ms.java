package _2월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2212_센서_골드5_이민선_116ms {
    static int n, k;
    static int[] sensors;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        sensors = new int[n];
        int idx = 0;
        while (st.hasMoreTokens()) {
            sensors[idx++] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensors);

        dist = new int[n - 1];
        idx = 0;
        for (int i = 1; i < n; i++) {
            dist[idx++] = sensors[i] - sensors[i - 1];
        }


        Arrays.sort(dist);
        int answer = 0;
        for (int i=0;i< n - k;i++) answer += dist[i];
        System.out.println(answer);
    }
}
