// 그리디

package _2월2주차;

import java.io.*;
import java.util.*;

public class 백준_14469_소가길을건너간이유3_실버4_이민선_72ms {
    static int n;
    static class Cow  implements Comparable<Cow> {
        int arrivalTime;
        int inspectionTime;

        Cow (int arrivalTime, int inspectionTime){
            this.arrivalTime = arrivalTime;
            this.inspectionTime = inspectionTime;
        }

        @Override
        public int compareTo(Cow anotherCow) {
            if (this.arrivalTime == anotherCow.arrivalTime){
                return Integer.compare(this.inspectionTime, anotherCow.inspectionTime);
            }
            return Integer.compare(this.arrivalTime, anotherCow.arrivalTime);
        }

        @Override
        public String toString(){
            return "arrivalTime: " + arrivalTime + ", inspectionTime: " + inspectionTime;
        }

    }
    static PriorityQueue<Cow> cows;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        cows = new PriorityQueue<Cow>();
        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            Cow cow = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            cows.add(cow);
        }

        int curTime = 0;
        while (!cows.isEmpty()){
            Cow cow = cows.poll();

            if (cow.arrivalTime >= curTime){
                curTime = cow.arrivalTime;
            }
            curTime += cow.inspectionTime;
        }
        System.out.println(curTime);
    }
}
