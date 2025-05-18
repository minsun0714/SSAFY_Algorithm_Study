import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 백준_16934_게임닉네임_골드3_이민선_404ms {
    static int n;
    static class Node {
        Node[] children = new Node[26];
    }
    static Node root = new Node();
    static Map<String, Integer> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i=0;i<n;i++){
            String nickname = br.readLine();
            map.putIfAbsent(nickname, 0);
            map.put(nickname, map.get(nickname) + 1);
            append(nickname);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void append(String nickname){
        Node cur = root;

        sb.append(nickname.charAt(0));
        for (int i=0;i<nickname.length();i++){
            Node next = cur.children[nickname.charAt(i) - 'a'];
            if (next == null){
                next = new Node();
                cur.children[nickname.charAt(i) - 'a'] = next;
            } else {
                if (i == nickname.length() - 1){
                    int x = map.get(nickname);
                    if (x == 1) return;
                    sb.append(x);
                } else {
                    sb.append(nickname.charAt(i + 1));
                }
            }
            cur = next;
        }
    }
}
