import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int idx;
    int score;
    Node(int idx, int score) {
        this.idx = idx;
        this.score = score;
    }
    public int compareTo(Node o) {
        return o.score - this.score;
    }
}
public class Softeer_성적평가_레벨3_한재경_868ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] ans = new int[4][n];
        int[] last = new int[n];
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int sc = Integer.parseInt(st.nextToken());
                q.add(new Node(j, sc));
                last[j] += sc;
            }
            int pre = -1; //이전값
            int prer = -1; //이전 등수
            for (int j = 0; j < n; j++) {
                Node node = q.poll();
                if (node.score == pre) {
                    ans[i][node.idx] = prer;
                    //이전 등수는 유지
                } else {
                    ans[i][node.idx] = j;
                    prer = j;
                }
                pre = node.score;
            }
        }
        //마지막 라인 처리
        for (int i = 0; i < n; i++) {
            q.add(new Node(i, last[i]));
        }
        int pre = -1; //이전값
        int prer = -1; //이전 등수
        for (int j = 0; j < n; j++) {
            Node node = q.poll();
            if (node.score == pre) {
                ans[3][node.idx] = prer;
                //이전 등수는 유지
            } else {
                ans[3][node.idx] = j;
                prer = j;
            }
            pre = node.score;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(ans[i][j] + 1 +" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
