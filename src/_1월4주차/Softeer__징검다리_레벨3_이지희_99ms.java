package _1월4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Softeer__징검다리_레벨3_이지희_99ms {

    // left, right, mid : index
    static int search(List<Integer> list, int no) {
        int left = 0;
        int right = list.size()-1;

        while(left <= right) {
            int mid = (left + right)/2;

            if(list.get(mid) < no) {
                // 반으로 쪼갠 후 오른쪽 구역에 있을 때,
                // left를 mid 오른쪽으로 옮겨 탐색 범위를 줄임
                left = mid + 1;
            }else {
                // 왼쪽 구역에 있을 때,
                // right를 mid 왼쪽으로 옮겨 탐색 범위를 줄임
                right = mid - 1;
            }
        }

        // 위치 반환
        return left;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] heights = new int[N];
        List<Integer> list = new ArrayList<>();

        for(int n=0; n<N; n++) {
            heights[n] = Integer.parseInt(st.nextToken());
            int index = search(list, heights[n]);

            // 기존 숫자 사이
            if(index < list.size()) {
                list.set(index, heights[n]);
            }else {
                list.add(heights[n]);
            }
        }

        System.out.println(list.size());
        br.close();

    }
}
