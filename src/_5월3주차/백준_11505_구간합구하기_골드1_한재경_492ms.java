import java.io.*;
import java.util.*;

public class 백준_11505_구간합구하기_골드1_한재경_492ms {
    static int n;
    static int m;
    static int k;
    static int[] arr;
    static long[] tree;
    static int h;
    static int treeSize;

    // 부모노드는 두 자식의 곱
    static long build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = (build(node * 2, start, mid) *
                build(node * 2 + 1, mid + 1, end))%(1000000007);
    }

    // 원본배열 idx를 포함하는 start부터 end까지 newValue로 교체된 리프노드 값으로 재계산
    static void update(int node, int start, int end, int idx, int newValue) {
        if (idx < start || idx > end) { //범위 벗어나는 경우
            return;
        }
        if (start == end) {
            tree[node] = newValue;
            return;
        }
        int mid = (start + end) / 2;

        update(node * 2, start, mid, idx, newValue);
        update(node * 2 + 1, mid + 1, end, idx, newValue);
        //재계산
        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1000000007;
    }

    // left부터 right 까지의 곱 리턴
    static long query(int node, int start, int end, int left, int right) {
        if (end < left || start > right) {
            return 1;
        }
        //전체 포함
        if (start >= left && end <= right) {
            return tree[node];
        }
        //부분 포함: 자식 보기
        int mid = (start + end) / 2;
        return ((query(node * 2, start, mid, left, right)) *
                query(node * 2 + 1, mid + 1, end, left, right))%(1000000007);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //수의 개수
        m = Integer.parseInt(st.nextToken()); //변경 횟수
        k = Integer.parseInt(st.nextToken()); //쿼리 개수
        arr = new int[n+1];


        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        h = (int) Math.ceil(Math.log(n) / Math.log(2));
        treeSize = 1 << (h + 1); //총 노드 개수

        tree = new long[treeSize + 1];
        for (int i = 1; i < treeSize; i++) {
            tree[i] = 1;
        }

        build(1, 1, n);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                //b를 c로 바꿈 update
                update(1, 1, n, b, c);
            } else {
                //b에서 c까지의 곱 쿼리
                sb.append(query(1, 1, n, b, c) + "\n");
            }
        }
        System.out.println(sb);
    }
}
