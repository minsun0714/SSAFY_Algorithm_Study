package _2월3주차;

import java.util.*;

public class 프로그래머스_셔틀버스_레벨3_이민선 {
  static Map<Integer, int[]> busList;
  static PriorityQueue<Integer> pq;
  public String solution(int n, int t, int m, String[] timetable) {
    busList = new LinkedHashMap<>();
    int prev = 540;
    for (int i=0;i<n;i++){
      busList.put(prev, new int[m]);
      prev += t;
    }

    Arrays.sort(timetable);

    pq = new PriorityQueue<>();
    for (String time: timetable){
      int minute = getMinute(time);
      pq.offer(minute);
    }

    int lastBusArrivalTime = 540;
    for (Integer busArrivalTime:busList.keySet()){
      for (int i=0;i<m;i++){
        if (!pq.isEmpty() && pq.peek() <= busArrivalTime) {
          busList.get(busArrivalTime)[i] = pq.poll();
        }
      }
      lastBusArrivalTime = busArrivalTime;
    }
    int[] lastBus = busList.get(lastBusArrivalTime);
    Arrays.sort(lastBus);

    String answer = "";
    if (lastBus[0] == 0) answer = getStringTime(lastBusArrivalTime);
    else answer = getStringTime(lastBus[m - 1] - 1);

    return answer;
  }

  public int getMinute(String time){
    String h = "" + time.charAt(0) + time.charAt(1);
    String m = "" + time.charAt(3) + time.charAt(4);

    return Integer.parseInt(h) * 60 + Integer.parseInt(m);
  }

  public String getStringTime(int minute){
    int h = minute / 60;
    int m = minute % 60;

    System.out.println(pad(h) + " " + pad(m));

    return pad(h) + ":" + pad(m);
  }

  private String pad(int num){
    String result = "";
    while (result.length() < 1 - num / 10){
      result += "0";
    }
    result += num;
    return result;
  }

}
