// Greedy
// [ 612 ms | 56 mb ]

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(input.readLine());
        for(int t=0; t<T; t++){ 

            TreeMap<Integer, Integer> map = new TreeMap<>();
            int n = Integer.parseInt(input.readLine());
            List<Integer> descSort = new ArrayList<>();
            tokens = new StringTokenizer(input.readLine());
            for(int i=0; i<n; i++){
                descSort.add(Integer.parseInt(tokens.nextToken()));
            }

            descSort.sort(Comparator.reverseOrder());
            for (int service : descSort) {
                insertService(service, map);
            }
          
            int count = 0;
            for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                count += entry.getValue();
            }
            output.append(count).append("\n");
        } 
        System.out.println(output);
    }

    private static void insertService(int service, TreeMap<Integer, Integer> map) {
        if(map.isEmpty()){
            insert(service,map);
            return;
        }
        int first = map.firstKey();
        int total = first + service;
        if(total<=900){
            insert(total, map);
            delete(first, map);
        } else{
            insert(service,map);
        }
    }

    private static void insert(int service, TreeMap<Integer, Integer> map) {
        map.putIfAbsent(service, 0);
        int count = map.get(service);
        map.put(service, count+1);
    }
    private static void delete(int service, TreeMap<Integer, Integer> map) {
        int count = map.get(service);
        if(count==1){
            map.remove(service);
        } else{
            map.put(service, count-1);
        }
    }

}

