class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> newIntervals = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) {
            newIntervals.add(intervals[i]);
            i++;
        }

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        newIntervals.add(newInterval);

        // Add all intervals that start after the new interval ends
        while (i < n) {
            newIntervals.add(intervals[i]);
            i++;
        }

        return newIntervals.toArray(new int[newIntervals.size()][]);
    }
}
