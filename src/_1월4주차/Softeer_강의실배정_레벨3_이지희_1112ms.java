package _1월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Softeer_강의실배정_레벨3_이지희_1112ms {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->{
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        for(int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            pq.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        // 종료 시간 순 > 시작 순으로 정렬
        // 배정 강의 수
        int count = 0;
        // 마지막 강의 끝난 시각
        int lastEnd = 0;

        while(!pq.isEmpty()) {
            int[] course = pq.poll();
            if(course[0] >= lastEnd) {
                lastEnd = course[1];
                count++;
            }
        }

        System.out.println(count);
        br.close();
    }

}
