import java.util.*;

//우선순위큐
class LeetCode_3196_CountDaysWithoutMeetings_Medium_한재경_84ms implements Comparable<Day> {
    int start;
    int end;
    Day(int start, int end) {
        this.start = start;
        this.end = end;
    }
    public int compareTo(Day o) {
        return this.start - o.start;
    }
}
class Solution {
    public int countDays(int days, int[][] meetings) {
        PriorityQueue<Day> pq = new PriorityQueue<>();
        for (int[] m : meetings) {
            pq.add(new Day(m[0], m[1]));
        }
        int st = 0; //이전 최대시작값
        int end = 0; //이전 최대끝값
        int cnt = 0;
        while (!pq.isEmpty()) {
             Day day = pq.poll();
             if (day.start > end + 1) { //이전 범위 아예 밖 (현재시작 > 이전끝 + 1)
                cnt += day.start - end - 1; //현재 시작 - 이전 end - 1
             }
             if (day.end > end) { //end 넓히기
                end = day.end;
             }
        }
        cnt += days - end;
        return cnt;
    }
}
