package _1월4주차;

import java.util.Deque;
import java.util.LinkedList;

// 슬라이딩 윈도우 - 한칸씩 밀어가며 비교
public class 프로그래머스_징검다리건너기_레벨3_이지희 {

    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        // index 모음
        Deque<Integer> dq = new LinkedList<>();

        for(int i=0; i<stones.length; i++) {
            // 구간 개수 이전꺼 제외
            if(!dq.isEmpty() && dq.peekFirst() < i - k +1) {
                dq.pollFirst();
            }

            // 구간에서 작은 애들 지움
            while(!dq.isEmpty() && stones[dq.peekLast()] <= stones[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);

            if(i >= k-1) {
                // 구간 최댓값 중에서 작은 값
                answer = Math.min(answer, stones[dq.peekFirst()]);
                System.out.println("i: " + i + ", answer: " + answer);
            }
        }

        return answer;
    }
}
