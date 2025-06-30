package _3월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 트라이
public class 백준_16934_게임닉네임_골드3_이민선_364ms {
    static int n;
    static class Node {
        Node[] children = new Node[26];
    }
    static Node root = new Node();
    static Map<String, Integer> members = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        while (n-- > 0){
            String nickname = br.readLine();

            int value = members.getOrDefault(nickname, 0);
            members.put(nickname, ++value);
            insert(nickname);
        }

        System.out.println(sb);
    }

    private static void insert(String nickname){
        Node cur = root;

        boolean exists = true;

        for (int i=0;i<nickname.length();i++){
            int idx = nickname.charAt(i) - 97;

            if (cur.children[idx] == null){
                if (exists) {
                    exists = false;
                    sb.append(nickname.charAt(i));
                }
                cur.children[idx] = new Node();
            } else {
                sb.append(nickname.charAt(i));
            }

            cur = cur.children[idx];
        }

        if (exists){
            int x = members.get(nickname);
            if (x > 1) sb.append(x);
        }
        sb.append("\n");
    }
}
