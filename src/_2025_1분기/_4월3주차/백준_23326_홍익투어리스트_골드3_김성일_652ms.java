package study.selfteam;
import java.io.*;
import java.util.*;
// 특별한 알고리즘은 없는데
class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader( System.in ));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int N;
    static int Q;
    static int nowIndex; 
    static TreeSet<Integer> hotPlace = new TreeSet<>();
    public static void 백준_23326_홍익투어리스트_골드3_김성일_652ms (String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        Q = Integer.parseInt(tokens.nextToken());
        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<N; i++){
            int isHot = Integer.parseInt(tokens.nextToken());
            if(isHot==1){
                hotPlace.add(i);
            }
        }
        for(int i=0; i<Q; i++){
            tokens = new StringTokenizer(input.readLine());
            int cmd = Integer.parseInt(tokens.nextToken());
            if(cmd==3) {
                cmd3();
            } else {
                int value = Integer.parseInt(tokens.nextToken());
                if(cmd==2){
                    cmd2(value);
                } else{
                    cmd1(value-1);
                }
            }
        }
        System.out.print(output);
    }
    static void cmd1(int value){
        if(hotPlace.contains(value)){
            hotPlace.remove(value);
        } else{
            hotPlace.add(value);
        }
    }
    static void cmd2(int value){
        nowIndex += value;
        nowIndex %= N;
    }
    static void cmd3(){
        if(hotPlace.contains(nowIndex)){
            output.append(0).append("\n");
            return;
        }
        Integer high = hotPlace.higher(nowIndex);
        if(high != null){
            int gap = high-nowIndex;
            output.append(gap).append("\n");
            return;
        }
        Integer low = hotPlace.ceiling(0);
        if(low != null){
            int gap = (N)-nowIndex + low;
            output.append(gap).append("\n");
            return;
        }
        output.append(-1).append("\n");
    }
}
