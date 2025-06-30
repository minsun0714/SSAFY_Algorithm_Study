package _2월4주차;

import java.io.*;
import java.util.*;

// 우선순위 큐
public class Softeer_성적평가_레벨3_이민선_1685ms {
    static int n;
    static class Person implements Comparable<Person>{
        int idx;
        int score;

        Person(int idx, int score){
            this.idx = idx;
            this.score = score;
        }

        @Override
        public int compareTo(Person anotherPerson){
            return Integer.compare(anotherPerson.score, this.score);
        }

        @Override
        public String toString(){
            return "(" + idx + "," + score + ")";
        }
    }
    static PriorityQueue<Person> people;
    static int[] total;
    static Map<Integer, Integer> freq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        total = new int[n];

        for (int i=0;i<n;i++){
            total[i] = 0;
        }

        for (int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            int[] scores = new int[n];
            for (int j=0;j<n;j++){
                scores[j] = Integer.parseInt(st.nextToken());
            }
            getRank(scores);
        }

        getRank(total);

    }

    public static void getRank(int[] scores){
        people = new PriorityQueue<>();
        freq = new HashMap<>();
        for (int j=0;j<n;j++){
            int score = scores[j];
            freq.put(score, freq.get(score) == null? 1 : freq.get(score) + 1);
            total[j] += score;
            Person person = new Person(j, score);
            people.offer(person);
        }
        int[] rank = new int[n];

        int curRank = 1;
        int prevScore = people.peek().score;
        while (!people.isEmpty()){
            Person person = people.poll();
            if (person.score == prevScore){
                rank[person.idx] = curRank;
            } else {
                curRank += freq.get(prevScore);
                prevScore = person.score;
                rank[person.idx] = curRank;
            }
        }
        for (int num:rank){
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
