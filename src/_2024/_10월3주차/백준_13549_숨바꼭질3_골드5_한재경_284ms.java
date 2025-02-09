import java.util.*;

//bfs
//i-1 후 *2가 최단일 수 있으므로 dp 불가! : 모든 *2경로 다 메모하고 시작하려면 시간 이중 for문 되어야 함
public class 백준_13549_숨바꼭질3_골드5_한재경_284ms {

    static int min = Integer.MAX_VALUE;
    static int n, k;
    static boolean[] visited;
    static int max = 100000;

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        k = scan.nextInt();

        visited = new boolean[max + 1];
        bfs();
        System.out.println(min);
    }

    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(n, 0));

        while(!q.isEmpty()) {
            Node node = q.poll();
            visited[node.x] = true;
            if(node.x == k) min = Math.min(min, node.time);

            if(node.x * 2 <= max && !visited[node.x * 2]) {
                q.offer(new Node(node.x * 2, node.time)); //*2가 선점하면 +1가 못옴
            }
            if(node.x + 1 <= max && !visited[node.x + 1]) {
                q.offer(new Node(node.x + 1, node.time + 1));
            }
            if(node.x - 1 >= 0 && !visited[node.x - 1]) {
                q.offer(new Node(node.x - 1, node.time + 1));
            }
        }
    }

    public static class Node {
        int x;
        int time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
