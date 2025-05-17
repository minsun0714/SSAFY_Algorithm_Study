import java.io.*;
import java.util.*;

// 세그먼트 트리
public class 백준_2042_구간합구하기_골드1_한재경_556ms {
    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 수의 개수
        int m = Integer.parseInt(st.nextToken()); // 변경 횟수
        int k = Integer.parseInt(st.nextToken()); // 구간합 횟수

        arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        //트리 높이 구하기
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int treeSize = 1 << (h + 1); //총 노드 개수
        tree = new long[treeSize];

        build(1, 1, n);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                update(1, 1, n, b, c - arr[b]);
                arr[b] = c;
            } else {
                sb.append(query(1, 1, n, b, c) + "\n");
            }
        }
        System.out.println(sb);
    }

    // 트리 생성: 현재노드는 자식 노드의 합
    static long build(int node, int start, int end) {
        //리프노드 도착 시
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = build(node * 2, start, mid)
                + build(node * 2 + 1, mid + 1, end);
    }

    //값 업데이트: start~end에 원본배열 idx 포함하는 트리노드 diff만큼 +
    static void update(int node, int start, int end, int idx, long diff) {
        //범위 완전 벗어나면 리턴
        if (idx < start || idx > end) {
            return;
        }
        tree[node] += diff;
        if (start == end) { //리프노드
            return;
        }
        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, diff);
        update(node * 2 + 1, mid + 1, end, idx, diff);
    }

    //구간합 계산: 원본배열의 left부터 right까지의 구간합
    static long query(int node, int start, int end, int left, long right) {
        //범위 완전 벗어나면 0리턴
        if (right < start || left > end) {
            return 0;
        }
        //범위 완전 포함되면 해당 노드 리턴
        if (start >= left && end <= right) {
            return tree[node];
        }
        //범위 부분 포함 시 자식 노드 들어가기
        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right)
                + query(node * 2 + 1, mid + 1, end, left, right);
    }
}
