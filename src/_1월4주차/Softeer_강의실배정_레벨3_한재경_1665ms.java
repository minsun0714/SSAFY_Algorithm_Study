import java.io.*;
import java.util.*;

// 정렬& 그리디, 1665ms
public class Softeer_강의실배정_레벨3_한재경_1665ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Node[] lectures = new Node[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures[i] = new Node(start, end);
        }
        Arrays.sort(lectures);
        
        int now = 0; //0시부터 시작
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (lectures[i].start >= now) {
                now = lectures[i].end;
                result++;
            }
        }
        System.out.println(result);
    }

    static class Node implements Comparable<Node> {
        int start;
        int end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int compareTo(Node node) {
            return this.end - node.end;
        }
    }
}
