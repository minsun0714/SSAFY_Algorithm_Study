import java.io.*;
import java.util.*;

public class 백준_7432_디스크트리_골드3_이민선_368ms {
    static int n;
    static class Path {
        String val;
        int level;
        List<Path> next = new ArrayList<>();

        public Path (){
            this.level = -1;
        }

        public Path(String val, int level) {
            this.val = val;
            this.level = level;
        }
    }
    static Path root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        root = new Path();

        n = Integer.parseInt(br.readLine());

        for (int i=0;i<n;i++){
            String directory = br.readLine();

            append(directory);
        }

        print();
    }

    private static void append(String directory){
        Path cur = root;

        StringBuilder path = new StringBuilder();
        for (int i=0;i<directory.length();i++){
            char ch = directory.charAt(i);
            if (ch != '\\') path.append(ch);
            if (ch == '\\' || i == directory.length() - 1) {
                String nextPath = path.toString();
                path = new StringBuilder();

                Optional<Path> existing = cur.next.stream().filter(p -> p.val.equals(nextPath)).findAny();
                if (existing.isPresent()){
                    cur = existing.get();
                } else {
                    Path next = new Path(nextPath, cur.level + 1);
                    cur.next.add(next);
                    cur = next;
                }
            }
        }
    }

    private static void print(){
        Stack<Path> stack = new Stack<>();
        stack.push(root);
        StringBuilder sb= new StringBuilder();

        while (!stack.isEmpty()){
            Path cur = stack.pop();
            for (int i=0;i<cur.level;i++) sb.append(" ");
            if (cur.val != null) sb.append(cur.val).append("\n");

            cur.next.sort(Comparator.comparing(p -> p.val));
            for (int i=cur.next.size() - 1;i>= 0;i--){
                stack.push(cur.next.get(i));
            }
        }

        System.out.println(sb);
    }
}
