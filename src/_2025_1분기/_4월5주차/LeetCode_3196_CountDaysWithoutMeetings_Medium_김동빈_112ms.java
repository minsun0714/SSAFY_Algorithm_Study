package etc._4_5;

import java.util.PriorityQueue;

public class LeetCode_CountDaysWithoutMeetings_Medium {
	
	static class Meeting implements Comparable<Meeting>{
		int s;
		int e;
		public Meeting(int s, int e) {
			super();
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Meeting o) {
			int c = Integer.compare(this.s, o.e);
			if (c == 0) {
				c = Integer.compare(this.e, o.e);
			} 
			return c; 
		}
	}
	
	public int countDays(int days, int[][] meetings) {
		
		PriorityQueue<Meeting> sortedMeetings = new PriorityQueue<>();
		
		for (int[] meeting : meetings) {
			sortedMeetings.offer(new Meeting(meeting[0], meeting[1]));
		}
		
		int totaldays = sortedMeetings.peek().s - 1;
		int curDay = totaldays + 1;
		
		while(!sortedMeetings.isEmpty()) {
			
			Meeting cur = sortedMeetings.poll();
			int ns = cur.s;
			int ne = cur.e;
			
			if (curDay < ns) {
				totaldays += (ns - curDay) - 1;
			}
			curDay = Math.max(curDay, ne);
		}
		
		totaldays += (days - curDay);
		
		return totaldays;
    }
}
