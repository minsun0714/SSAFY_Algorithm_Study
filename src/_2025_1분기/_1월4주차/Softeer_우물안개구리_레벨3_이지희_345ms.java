package _1월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// if-else
// 그냥 지인 받을 때마다 약한 사람 체크, 동일하면 둘 다 약하다고 표기
// false : 젤 쎈 사람 -> count
public class Softeer_우물안개구리_레벨3_이지희_345ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] weights = new int[N];
        boolean[] weaker = new boolean[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int n=0; n<N; n++) {
            weights[n] = Integer.parseInt(st.nextToken());
        }

        for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            if(weights[A] < weights[B]) {
                weaker[A] = true;
            }else if(weights[A] > weights[B]) {
                weaker[B] = true;
            }else {
                weaker[A] = true;
                weaker[B] = true;
            }
        }

        int count = 0;
        for(int n=0; n<N; n++) {
            if(!weaker[n]) count++;
        }

        System.out.println(count);
        br.close();

    }

}
