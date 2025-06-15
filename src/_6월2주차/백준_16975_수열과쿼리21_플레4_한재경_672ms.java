import java.io.*;
import java.util.*;

// 세그먼트 트리, tree에 본 값 말고 더해야 할 값만 메모
public class 백준_16975_수열과쿼리21_플레4_한재경_672ms {
    static int n;
    static int m;
    static int[] arr;
    static long[] tree;
    static int h;
    static int treeSize;

    // left부터 end까지 전체 포함되는 노드만 +value
    static void update(int node, int start, int end, int left, int right, int value) {
        if (start > right || end < left) {
            return;
        }
        // 전체 포함
        if (start >= left && end <= right) {
            tree[node] += value;
            return;
        }
        // 부분 포함: 메모x
        int mid = (start + end) / 2;
        update(node * 2, start, mid, left, right, value);
        update(node * 2 + 1, mid + 1, end, left, right, value);
    }

    // 해당노드에 더해야 하는 값 전체: 부모노드 전체
    static long query(int node, int start, int end, int idx) {
        if (idx < start || idx > end) {
            return 0;
        }
        //리프노드
        if (start == end) {
            return tree[node];
        }

        //부분 포함: 자식 보기 (현재노드값!! + 자식노드값)
        int mid = (start + end) / 2;
        if (idx <= mid) {
            return tree[node] + query(node * 2, start, mid, idx);
        } else {
            return tree[node] + query(node * 2 + 1, mid + 1, end, idx);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); //수의 개수
        arr = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        h = (int) Math.ceil(Math.log(n) / Math.log(2));
        treeSize = 1 << (h + 1); //총 노드 개수

        tree = new long[treeSize];


        m = Integer.parseInt(br.readLine()); //변경 횟수
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                // a~b에 c 더함
                update(1, 1, n, a, b, c);
            } else {
                int a = Integer.parseInt(st.nextToken());

                // a 출력
                sb.append(arr[a] + query(1, 1, n, a) + "\n");
            }
        }
        System.out.println(sb);
    }
}
