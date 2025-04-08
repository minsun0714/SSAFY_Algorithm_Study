package _4월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_19951_태상이의훈련소생활_골드5_이민선_552ms {
    static int n, m;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        int idx = 1;
        while (st.hasMoreTokens()){
            nums[idx++] = Integer.parseInt(st.nextToken());
        }

        int[] acc = new int[n + 2];

        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            acc[a] += k;
            acc[b + 1] += -k;
        }


        for (int i=1;i<=n;i++){
            acc[i] += acc[i - 1];
            nums[i] += acc[i];
        }

        for (int i=1;i<=n;i++) sb.append(nums[i]).append(" ");
        System.out.println(sb);
    }
}
