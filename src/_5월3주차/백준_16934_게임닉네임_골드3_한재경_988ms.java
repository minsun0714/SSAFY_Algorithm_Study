import java.io.*;
import java.util.*;

// 구현, 문자열 - 트라이로도 해보자!!!!!!!
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>(); //닉네임:카운팅
        Set<String> set = new HashSet<>(); //별명
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String nickname = br.readLine();

            //닉네임+카운트: 이전에 동일닉네임 o
            if (map.containsKey(nickname)) {
                map.put(nickname, map.get(nickname) + 1);
                sb.append(nickname + map.get(nickname) + "\n");
                continue;
            }
            map.put(nickname, 1);
            String prefix = "";
            boolean flag = true;

            for (int j = 0; j < nickname.length(); j++) {
                char now = nickname.charAt(j);
                prefix += now;
                //닉을 별명으로: 이전에 동일닉네임 x && 전체 동일 prefix
                if (prefix.equals(nickname) && set.contains(prefix)) {
                    sb.append(nickname + "\n");
                    break;
                }
                //접두사 방식  : 이전에 동일닉네임 x && 동일 prefix x
                if (!set.contains(prefix)) {
                    set.add(prefix);
                    if (flag) {
                        sb.append(prefix + "\n");
                        flag = false;
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
