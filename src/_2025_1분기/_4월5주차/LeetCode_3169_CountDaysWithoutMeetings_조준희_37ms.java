class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b)->a[0]-b[0]);

        int meeting_days = 0;
        int end = -1;
        for(int i = 0; i<meetings.length; i++){
            if(meetings[i][0]<=end && meetings[i][1]>end){
                meeting_days+=meetings[i][1]-end;
                end = meetings[i][1];
            }
            else if(meetings[i][0]>end){
                meeting_days+=meetings[i][1]-meetings[i][0]+1;
                end = meetings[i][1];
            }

        }
        int result = days-meeting_days;
        if(result>0){
            return result;
        }
        else return 0;
    }
}
