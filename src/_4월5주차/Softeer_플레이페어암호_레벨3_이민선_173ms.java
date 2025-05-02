import java.io.*;
import java.util.*;

public class Softeer_플레이페어암호_레벨3_이민선_173ms {
    static String message;
    static String key;
    static char[][] table = new char[5][5];
    static Map<Character, int[]> dict = new HashMap<>();
    static String answer = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        message = br.readLine();
        key = br.readLine();

        convertIntoTable();

        List<String> messagePairs = divideMessageIntoPairs();

        for (String pair: messagePairs){
            encodeMessagePairs(pair);
        }
        System.out.println(answer);
    }

    private static void convertIntoTable(){
        boolean[] appeared = new boolean[26];

        int idx = 0;
        for (int i=0;i<key.length();i++){
            char cur = key.charAt(i);
            if (!appeared[cur - 'A']) {
                appeared[cur - 'A'] = true;
                table[idx / 5][idx % 5] = cur;
                dict.put(cur, new int[]{idx / 5, idx % 5});
                idx++;
            }
        }

        for (int i=0;i<26;i++){
            if (i == 9) continue;
            if (!appeared[i]) {
                appeared[i] = true;
                table[idx / 5][idx % 5] = (char)('A' + i);
                dict.put((char)('A' + i), new int[]{idx / 5, idx % 5});
                idx++;
            }
        }
    }

    private static List<String> divideMessageIntoPairs(){
        List<String> messagePairs = new ArrayList<>();
        for (int i=0;i<message.length();i+=2){
            char cur = message.charAt(i);
            char next = i == message.length() - 1 ? 'X' : message.charAt(i + 1);

            if (cur == next && i < message.length() - 1){
                next = 'X';
                if (cur == 'X'){
                    next = 'Q';
                }
                i--;
            }

            messagePairs.add("" + cur + next);
        }

        return messagePairs;
    }

    private static String encodeMessagePairs(String pair){
        int[] firstPairIdx = dict.get(pair.charAt(0));
        int[] secondPairIdx = dict.get(pair.charAt(1));

        if (firstPairIdx[0] == secondPairIdx[0]) {
            int nextFirstColIdx = (firstPairIdx[1] + 1) % 5;
            int nextSecondColIdx = (secondPairIdx[1] + 1) % 5;

            answer += table[firstPairIdx[0]][nextFirstColIdx];
            answer += table[secondPairIdx[0]][nextSecondColIdx];
        }

        else if (firstPairIdx[1] == secondPairIdx[1]) {
            int nextFirstRowIdx = (firstPairIdx[0] + 1) % 5;
            int nextSecondRowIdx = (secondPairIdx[0] + 1) % 5;

            answer += table[nextFirstRowIdx][firstPairIdx[1]];
            answer += table[nextSecondRowIdx][secondPairIdx[1]];
        }

        else {
            int nextFirstColIdx = secondPairIdx[1];
            int nextSecondColIdx = firstPairIdx[1];

            answer += table[firstPairIdx[0]][nextFirstColIdx];
            answer += table[secondPairIdx[0]][nextSecondColIdx];
        }
        return answer;
    }
}
