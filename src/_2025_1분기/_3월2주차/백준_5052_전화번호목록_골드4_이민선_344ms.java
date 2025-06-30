package _3월2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 트라이
public class 백준_5052_전화번호목록_골드4_이민선_344ms {
    static int t;
    static Node root;
    static class Node {
        boolean isEnd;
        Node[] children = new Node[10];
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        while (t-- > 0){
            int n = Integer.parseInt(br.readLine());

            root = new Node();

            String[] phoneNumbers = new String[n];
            int idx = 0;
            while (n-- > 0){
                String phoneNumber = br.readLine();
                phoneNumbers[idx++] = phoneNumber;
                insert(phoneNumber);
            }

            boolean isConsistent = true;
            for (String phoneNumber:phoneNumbers){
                if (!search(phoneNumber)){
                    sb.append("NO").append("\n");
                    isConsistent = false;
                    break;
                }
            }
            if (isConsistent) sb.append("YES").append("\n");
        }
        System.out.println(sb);
    }

    private static void insert(String phoneNumber){
        Node cur = root;

        for (int i=0;i<phoneNumber.length();i++){
            int num = phoneNumber.charAt(i) - '0';
            if (cur.children[num] == null){
                cur.children[num] = new Node();
            }
            cur = cur.children[num];
        }
        cur.isEnd = true;
    }

    private static boolean search(String phoneNumber){
        Node cur = root;

        for (int i=0;i<phoneNumber.length();i++){
            int num = phoneNumber.charAt(i) - '0';
            if (i < phoneNumber.length() - 1 && cur.children[num].isEnd) return false;
            cur = cur.children[num];
        }

        return true;
    }
}
