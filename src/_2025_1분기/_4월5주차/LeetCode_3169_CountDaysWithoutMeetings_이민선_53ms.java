import java.util.*;

class LeetCode_3169_CountDaysWithoutMeetings_이민선_53ms {
    public int countDays(int days, int[][] meetings) {
        int n = meetings.length;
        int answer = 0;
        Arrays.sort(meetings, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        int s = 0;
        int e = 0;

        while (e < n){
            int maxEndTime = meetings[e][1];
            while (e < n - 1 && meetings[e + 1][0] <= maxEndTime) {
                maxEndTime = Math.max(meetings[++e][1], maxEndTime);
            }

            int meetingStart = meetings[s][0];
            int meetingEnd = maxEndTime;

            answer += meetingEnd - meetingStart + 1;

            s = ++e;
        }

        return days - answer;
    }
}