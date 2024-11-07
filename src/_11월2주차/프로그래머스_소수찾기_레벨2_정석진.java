import java.util.*;
class Solution {
    public static final int MAX = 9999999;
        public static int[] sieve() {
        int[] list = new int[MAX+1];
        for (int i = 2; i < MAX; i++) {
            list[i] = 1;
        }

        for (int i = 2; i<= Math.sqrt(MAX); i++) {
            for(int j = i * i; j <= MAX; j += i) {
                list[j] = 0;
            }
        }

        return list;
    }
    public static class Element{
        int num;
        int visited;
        Element(int num, int visited){
            this.num = num;
            this.visited=visited;
        }
    }
    public int solution(String numbers) {
        int answer = 0;
        int len = numbers.length();
        int[] primes = sieve();

        HashSet<Integer> set = new HashSet<>();
        Queue<Element> q = new LinkedList<>();
        char[] num = numbers.toCharArray();

        for(int i=0;i<len;i++){
            int digit = num[i] - '0';
            int visited = 1 << i;  
            q.add(new Element(digit, visited));
            set.add(digit);
        }

        while(!q.isEmpty()){
            Element e = q.poll();
            set.add(e.num);
            for(int i =0;i<len;i++){
                if((e.visited&(1<<i))==0){
                    int newNum = e.num*10+(num[i]-'0');
                    int newVisited = e.visited | (1 << i);
                    q.add(new Element(newNum, newVisited));                
                }
            }
        }

        for (int i : set) {
            if (primes[i] == 1) {
                answer++;
            }
        }
        return answer;
    }
}