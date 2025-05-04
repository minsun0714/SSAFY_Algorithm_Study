// 문제 링크 : https://leetcode.com/problems/count-days-without-meetings/?envType=daily-question&envId=2025-03-24
// Merge Intervals
// [ 177 ms | 108 mb ]
import java.util.*;

class Solution {
    public int countDays(int days, int[][] meetings) {
        int result = 0;
        List<List<Integer>> meetingsList = new ArrayList<>();
        for(int i=0; i<meetings.length; i++){
            List<Integer> temp = new ArrayList<>();
            for(int j=0; j<meetings[i].length; j++){
                temp.add(meetings[i][j]);
            }
            meetingsList.add(temp);
        }
        meetingsList.sort(Comparator.comparingInt( (List<Integer> o) -> (o.get(1) - o.get(0)) ).reversed());

        Set<List<Integer>> meetingsSet = new HashSet<>();
        meetingsSet.add(meetingsList.get(0));

        for(int i=0+1; i<meetingsList.size(); i++){
            List<Integer> temp = new ArrayList<>();
            List<Integer> now = meetingsList.get(i);

            Iterator<List<Integer>> iterator = meetingsSet.iterator();
            boolean toggle = false;
            while(iterator.hasNext()){
                List<Integer> already = iterator.next();
                toggle = false;
                if( already.get(0) <= now.get(0) && now.get(1) <= already.get(1)){
                    toggle = true;
                    break;
                }
                else if( Math.abs(already.get(0)-now.get(1)) <=1 || Math.abs(already.get(1)-now.get(0)) <=1 ){
                    temp.add(already.get(0));
                    temp.add(already.get(1));
                    temp.add(now.get(0));
                    temp.add(now.get(1));
                    iterator.remove(); 
                    if(temp.size()==8){ 
                        break;
                    }
                }
                else if(
                        (already.get(0) <= now.get(1) && already.get(1) > now.get(0))
                                ||
                                (now.get(0) <= already.get(1) && now.get(1) > already.get(0) ) ){
                    temp.add(already.get(0));
                    temp.add(already.get(1));
                    temp.add(now.get(0));
                    temp.add(now.get(1));
                    iterator.remove();
                    if(temp.size()==8){ 
                        break;
                    }
                }
            }

            if(temp.isEmpty()){
                if(toggle==false){ 
                    meetingsSet.add(now);
                }
                continue;
            }

            int max = temp.get(0);
            int min = temp.get(0);
            for(Integer value : temp){
                max = Math.max(max, value);
                min = Math.min(min, value);
            }
            List<Integer> insert = new ArrayList<>();
            insert.add(min);
            insert.add(max);
            meetingsSet.add(insert);
        }

        for(List<Integer> meeting : meetingsSet){
            // System.out.println(meeting.toString());
            result += meeting.get(1)-meeting.get(0)+1;
        }
        return days-result;
    }
}
