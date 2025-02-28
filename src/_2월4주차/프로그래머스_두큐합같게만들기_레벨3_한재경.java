import java.util.*;

class 프로그래머스_두큐합같게만들기_레벨3_한재경 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        int l = queue1.length + queue2.length;
        int cnt = 0; //전체길이되면 -1출력
        long sum = 0;
        long lsum = 0;
        long rsum = 0;
        
        for (int i : queue1) {
            q1.add(i);
            sum+=i;
            lsum+=i;
        }
        for (int i : queue2) {
            q2.add(i);
            sum+=i;
        }
        
        if (sum % 2 != 0) return -1;
        
        while (true) {
            if (cnt >= 2*l) return -1;
            
            rsum = sum - lsum;
            if (lsum == rsum) return cnt;
            else if (lsum > rsum) {
                int a = q1.poll();
                lsum -= a;
                q2.add(a);
            } else {
                int a = q2.poll();
                lsum += a;
                q1.add(a);
            }
            cnt++;
        }
    }
}
