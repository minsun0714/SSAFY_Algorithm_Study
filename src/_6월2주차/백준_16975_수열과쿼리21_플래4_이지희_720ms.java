import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 세그먼트 트리 - Lazy Propagation
public class 백준_16975_수열과쿼리21_플래4_이지희_720ms {

    // 세그먼트 트리 클래스 구현
    static class SegmentTree {
        long[] tree, lazy; // 쿼리 수행하면서(합) 정수를 범위를 벗어날 수 있음
        int size;

        SegmentTree(int n) {
            size = n;
            tree = new long[4*n];
            lazy = new long[4*n];
        }

        // 세그먼트 트리 구성
        void build(int node, int start, int end, long[] arr) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(node * 2, start, mid, arr); // 좌
                build(node * 2 + 1, mid + 1, end, arr); // 우
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
            }
        }

        // 특정 구간에 값 업데이트(합)
        void update(int node, int start, int end, int i, int j, long k) {
            if (j < start || end < i)
                return;

            // 구간 실행 전 아직 남아 있는 lazy 실행
            propagate(node, start, end);

            // 구간 내
            if (i <= start && end <= j) {
                // 현재 요청 lazy 기록
                lazy[node] += k;
                // lazy 실행
                propagate(node, start, end);
                return;
            }

            // 하위 트리 실행
            int mid = (start + end) / 2;
            update(node * 2, start, mid, i, j, k);
            update(node * 2 + 1, mid + 1, end, i, j, k);
        }

        // 쿼리 실행 : 특정 인덱스 값 반환
        long query(int node, int start, int end, int idx) {
            propagate(node, start, end);

            if (start == end)
                return tree[node];

            int mid = (start + end) / 2;
            if (idx <= mid)
                return query(node * 2, start, mid, idx);
            else
                return query(node * 2 + 1, mid + 1, end, idx);
        }

        void propagate(int node, int start, int end) {
            if (lazy[node] != 0) {
                tree[node] += (end - start + 1) * lazy[node];
                if (start != end) {
                    lazy[node * 2] += lazy[node];
                    lazy[node * 2 + 1] += lazy[node];
                }
                lazy[node] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        SegmentTree seg = new SegmentTree(N);
        seg.build(1, 1, N, arr);

        int M = Integer.parseInt(br.readLine());
        for (int q = 0; q < M; q++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                long k = Long.parseLong(st.nextToken());
                seg.update(1, 1, N, i, j, k);

            } else { // 2
                int x = Integer.parseInt(st.nextToken());
                sb.append(seg.query(1, 1, N, x)).append("\n");
            }
        }

        System.out.println(sb.toString());
        br.close();
    }

}
