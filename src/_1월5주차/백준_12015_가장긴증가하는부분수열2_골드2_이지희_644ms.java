package _1월5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_12015_가장긴증가하는부분수열2_골드2_이지희_644ms {

    static int search(List<Integer> lis, int now) {
        int left = 0, right = lis.size() - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(lis.get(mid) < now) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        List<Integer> lis = new ArrayList<>();

        for(int n=0; n<N; n++) {
            int now = Integer.parseInt(st.nextToken());
            int idx = search(lis, now);

            if(idx < lis.size()) {
                lis.set(idx, now);
            }else {
                lis.add(now);
            }
        }

        System.out.println(lis.size());
        br.close();

    }

}
