// 시뮬레이션
// [ 75 ms | 11 mb ]
import java.io.*;
import java.util.*;

public class 플레이페어암호 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static TreeSet<Character> alphabet = new TreeSet<>();
    static Map<Character, Integer> aToxyMap = new HashMap<>(); 
    static Map<Integer, Character> xyToaMap = new HashMap<>(); 
    static int rowSize = 5;
    static int colSize = 5;
  
    public static void main(String[] args) throws Exception {
        String plainText = input.readLine();
        String key = input.readLine();
        for (int A = 65; A < 65+26; A++) {
            alphabet.add( (char) A);
        }
        alphabet.remove('J');
        makeMatrix(key);
        List<char[]> result1 = encrypt1(plainText);
        String result2 = encrypt2(result1);
        System.out.println(result2); 
    }

    private static void makeMatrix(String key) {
        int pk=0;
        for(char c : key.toCharArray()){
            if(alphabet.contains(c)){
                alphabet.remove(c);
                aToxyMap.put(c, pk);
                xyToaMap.put(pk,c);
                pk++;
            }
        }
        for(char c : alphabet){
            aToxyMap.put(c, pk);
            xyToaMap.put(pk,c);
            pk++;
        }
    }

    private static List<char[]> encrypt1(String plainText) {
        List<char[]> result = new ArrayList<>();
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < plainText.length() ; i++) { 
            q.offer(plainText.charAt(i));
        }

        while(q.isEmpty()==false){
            char first = q.poll();
            if(q.isEmpty()){
                result.add(new char[] {first,'X'});
            }
            else if(q.peek()==first){
                if(first=='X'){
                    result.add(new char[] {first,'Q'});
                }
                else{
                    result.add(new char[] {first,'X'});
                }
            }
            else{
                result.add(new char[] {first, q.poll()});
            }
        }
        return result;
    }

    private static String encrypt2(List<char[]> result1) {
        StringBuilder result = new StringBuilder();
        for(char[] ele : result1){
            char c1 = ele[0];
            char c2 = ele[1];
            int c1Index = aToxyMap.get(c1);
            int c2Index = aToxyMap.get(c2);
            int c1Row = c1Index/colSize;
            int c1Col = c1Index%colSize;
            int c2Row = c2Index/colSize;
            int c2Col = c2Index%colSize;
            if(c1Row==c2Row){ 
                c1Index++;
                c2Index++;
                if(c1Index%colSize==0){
                    c1Index-=colSize;
                }
                result.append(xyToaMap.get(c1Index));
                if(c2Index%colSize==0){
                    c2Index-=colSize;
                }
                result.append(xyToaMap.get(c2Index));

            } else if (c1Col==c2Col) {
                c1Index+=colSize;
                c2Index+=colSize;
                if(c1Index/colSize>=rowSize){
                    c1Index-=colSize*rowSize;
                }
                result.append(xyToaMap.get(c1Index));
                if(c2Index/colSize>=rowSize){
                    c2Index-=colSize*rowSize;
                }
                result.append(xyToaMap.get(c2Index));
            } else{
                int nc1Col = c2Col;
                int nc2Col = c1Col;
                c1Index = c1Row*colSize + nc1Col;
                c2Index = c2Row*colSize + nc2Col;
                result.append(xyToaMap.get(c1Index));
                result.append(xyToaMap.get(c2Index));
            }
        }
        return result.toString();
    }
}
