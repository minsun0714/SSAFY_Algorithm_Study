import java.io.*;
import java.util.*;

class 프로그래머스_봉인된주문_레벨3_김민섭 {
    
    // STATIC
    static long temp;
    static long[] counter;
    
    // [AS STRING]
    private String asString(long num) {
        String str = "";
        long curr = num;
        for (int i = 1; i <= 11; i++) {
            if (0 < num) {
                long al = (long) (num % Math.pow(26, i) / Math.pow(26, i - 1)) == 0 ? 26 : (long) (num % Math.pow(26, i) / Math.pow(26, i - 1));
                str = (char) (al + 96) + str;
                num = (long) (num - Math.pow(26, i - 1) * al);
            }
        }
        return str;
    } // [AS STRING]
    
    // [AS LONG]
    private long asLong(String word) {
        long num = 0;
        num += counter[word.length()] + 1;
        for (int index = 0; index < word.length(); index++) {
            num += (word.charAt(index) - 97) * Math.pow(26, word.length() - index - 1);
        }
        return num;
    } // [AS LONG]
    
    // [SOLUTION]
    public String solution(long n, String[] bans) {
        temp = 1;
        counter = new long[12];
        for (int i = 2; i <= 11; i++) {
            temp *= 26;
            counter[i] = counter[i - 1] + temp;
        }
        
        long index = n;
        
        long[] banned = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            banned[i] = asLong(bans[i]);
        }
        Arrays.sort(banned);
        
        for (int i = 0; i < banned.length; i++) {
            if (banned[i] <= index) {
                index++;
                continue;
            }
            break;
        }
        
        String answer = asString(index);
        
        return answer;
    } // [SOLUTION]
}