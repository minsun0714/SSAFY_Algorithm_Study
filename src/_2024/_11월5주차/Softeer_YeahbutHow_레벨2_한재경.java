import java.io.*;
import java.util.*;

public class Softeer_YeahbutHow_레벨2_한재경 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        List<String> result = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            result.append(s.charAt(i-1) + "");
            if (s.charAt(i) == '(') {
                if (s.charAt(i-1) == ')') {
                    result.append("+");
                }
            } else {
                if (s.charAt(i-1) == '(') {
                    result.add("1");
                }
            }
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
        }
        System.out.print(")");
    }
}
