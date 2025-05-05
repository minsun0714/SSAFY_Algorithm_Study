// simple
// [ 492 ms | 86 mb ]

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
  
    static Map<String, Integer> memberInfo = new HashMap<>();
    static Map<Integer, Set<String>> groupInfo = new HashMap<>();
    static int pk;
  
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(input.readLine());
        for (int t = 0; t < T; t++) {
            memberInfo.clear();
            groupInfo.clear();
            pk = 0;
          
            int N = Integer.parseInt(input.readLine());
            for (int n = 0; n < N; n++) {
                tokens = new StringTokenizer(input.readLine());
                String person1 = tokens.nextToken();
                String person2 = tokens.nextToken();
                if(memberInfo.containsKey(person1) == false && memberInfo.containsKey(person2) == false){
                    memberInfo.put(person1, pk);
                    memberInfo.put(person2, pk);
                    groupInfo.put(pk, new HashSet<>());
                    Set<String> value =  groupInfo.get(pk);
                    value.add(person1);
                    value.add(person2);
                    output.append(value.size()).append("\n");
                    pk++;
                }
                else if (memberInfo.containsKey(person1) && memberInfo.containsKey(person2) ) {
                    int group1 = memberInfo.get(person1);
                    int group2 = memberInfo.get(person2);
                    Set<String> value1 = groupInfo.get(group1);
                    Set<String> value2 = groupInfo.get(group2);
                    if(group1==group2){
                        output.append(value2.size()).append("\n");
                        continue; 
                    }
                    if(value1.size()>value2.size()){
                        for(String name : value2){
                            memberInfo.put(name, group1);
                        }
                        value1.addAll(value2);
                        groupInfo.remove(group2);
                        output.append(value1.size()).append("\n");
                    } else{
                        for(String name : value1){
                            memberInfo.put(name, group2);
                        }
                        value2.addAll(value1);
                        groupInfo.remove(group1);
                        output.append(value2.size()).append("\n");
                    }
                }
                else{
                    boolean p1 = memberInfo.containsKey(person1);
                    if(p1){ 
                        int group = memberInfo.get(person1);
                        memberInfo.put(person2, group);
                        Set<String> value = groupInfo.get(group);
                        value.add(person2);
                        output.append(value.size()).append("\n");
                    } else {
                        int group = memberInfo.get(person2);
                        memberInfo.put(person1, group);
                        Set<String> value = groupInfo.get(group);
                        value.add(person1);
                        output.append(value.size()).append("\n");
                    }
                }
            }
        }
        System.out.println(output);
    }
}
