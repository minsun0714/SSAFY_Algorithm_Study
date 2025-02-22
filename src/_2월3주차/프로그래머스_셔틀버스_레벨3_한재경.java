import java.util.*;
import java.io.*;

class Crew implements Comparable<Crew> {
    int hour;
    int min;
    Crew (int hour, int min) {
        this.hour = hour;
        this.min = min;
    }
    public int compareTo(Crew o) {
        if (o.hour == this.hour) {
            return this.min - o.min;
        }
        return this.hour - o.hour;
    }
}

class 프로그래머스_셔틀버스_레벨3_한재경 {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        PriorityQueue<Crew> q = new PriorityQueue<>();
        for (String tt : timetable) {
            String[] ht = tt.split(":");
            int hour = Integer.parseInt(ht[0]);
            int min = Integer.parseInt(ht[1]);
            q.add(new Crew(hour, min));
        }
        int hh = 9; //시간
        int mm = 0; //분
        int rest = m;
        Crew lastC = null; //마지막으로 탄 인원
        while (n > 0) { //셔틀 n회 운영
            rest = m; //태울 수 있는 인원
            
            //해당 셔틀에 사람들 태우기
            while (rest > 0 && !q.isEmpty()) { //rest명 태울 수 있음
                Crew c = q.peek();
                if (hh > c.hour || (hh == c.hour && mm >= c.min)) { //줄 선 경우
                    rest--; //태우기
                    lastC = q.poll();
                } else { //오기 전
                    break;
                }
            }
            n--;
            if (n > 0) {
                hh = hh + (mm + t) / 60;
                mm = (mm + t) % 60;
            }
        }
        
        int ansh = 0;
        int ansm = 0;
        
        if (rest > 0) { //태울 수 있는 인원 남음
            ansh = hh; //마지막 시간에 타기
            ansm = mm;
        } else { //태울 수 있는 인원 없음
            ansh = lastC.hour;
            ansm = lastC.min - 1;
            if (ansm < 0) {
                ansh--;
                ansm += 60;
            }
        }
        String ans = String.format("%02d:%02d", ansh, ansm);
        
        return ans;
    }
}
