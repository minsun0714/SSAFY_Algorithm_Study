import java.io.*;
import java.util.*;

public class 백준_2357_최솟값과최댓값_골드1_한재경_704ms {
    static int n;
    static int m;
    static int k;
    static int[] arr;
    static long[] tree;
    static int h;
    static int treeSize;
    static long[] treeMin;

    // 부모노드는 두 자식의 최댓값
    static long buildMax(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = Math.max(buildMax(node * 2, start, mid),
                buildMax(node * 2 + 1, mid + 1, end));
    }

    // 부모노드는 두 자식의 최솟값
    static long buildMin(int node, int start, int end) {
        if (start == end) {
            return treeMin[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return treeMin[node] = Math.min(buildMin(node * 2, start, mid),
                buildMin(node * 2 + 1, mid + 1, end));
    }

    // left부터 right 까지의 최댓값
    static long queryMax(int node, int start, int end, int left, int right) {
        if (end < left || start > right) {
            return 0;
        }
        //전체 포함
        if (start >= left && end <= right) {
            return tree[node];
        }
        //부분 포함: 자식 보기
        int mid = (start + end) / 2;
        return Math.max((queryMax(node * 2, start, mid, left, right)),
                queryMax(node * 2 + 1, mid + 1, end, left, right));
    }

    // left부터 right 까지의 최솟값
    static long queryMin(int node, int start, int end, int left, int right) {
        if (end < left || start > right) {
            return Integer.MAX_VALUE;
        }
        //전체 포함
        if (start >= left && end <= right) {
            return treeMin[node];
        }
        //부분 포함: 자식 보기
        int mid = (start + end) / 2;
        return Math.min((queryMin(node * 2, start, mid, left, right)),
                queryMin(node * 2 + 1, mid + 1, end, left, right));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //수의 개수
        m = Integer.parseInt(st.nextToken()); //변경 횟수
        arr = new int[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        h = (int) Math.ceil(Math.log(n) / Math.log(2));
        treeSize = 1 << (h + 1); //총 노드 개수

        tree = new long[treeSize];
        treeMin = new long[treeSize];

        buildMax(1, 1, n);
        buildMin(1, 1, n);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a부터 b까지 최소, 최댓값
            sb.append(queryMin(1, 1, n, a, b) + " "
                    + queryMax(1, 1, n, a, b) + "\n");
        }
        System.out.println(sb);
    }
}
