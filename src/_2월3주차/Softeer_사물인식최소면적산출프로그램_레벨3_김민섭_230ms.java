import java.io.*;
import java.util.*;

// [class]
public class Softeer_사물인식최소면적산출프로그램_레벨3_김민섭_230ms {
    static int N;
    static int K;
    static List<int[]>[] points;

    static int left, right, bottom, top;
    static int ans;

    // [main]
    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        points = new List[K + 1];
        for (int i = 1; i <= K; i++) {
            points[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            points[c].add(new int[] {x, y});
        }

        left = 1000; right = -1000; bottom = 1000; top = -1000;
        ans = 4_000_000;
        
        // Calculate
        pick(1, 0);

        // Print
        System.out.println(ans);
    } // [/main]

    // [pick]
    static void pick(int col, int idx) {
        // Calculate
        if (K < col) {
            ans = Math.min(ans, Math.abs(right - left) * Math.abs(top - bottom));
            return;
        }

        // Next Color
        if (points[col].size() < idx) {
            pick(col + 1, 0);
            return;
        }
        
        for (int i = 0; i < points[col].size(); i++) {
            int x = points[col].get(i)[0], y = points[col].get(i)[1];
            
            if (inBoundary(x, y)) {
                pick(col + 1, 0);
                break;
            } else {
                int leftKeep = left, rightKeep = right;
                int bottomKeep = bottom, topKeep = top;

                left = Math.min(left, x); right = Math.max(right, x);
                bottom = Math.min(bottom, y); top = Math.max(top, y);
                pick(col + 1, 0);
                
                left = leftKeep; right = rightKeep;
                bottom = bottomKeep; top = topKeep;
            }
            
        }
    } // [/pick]

    // [inBoundary]
    static boolean inBoundary(int x, int y) {
        return left <= x && x <= right && bottom <= y && y <= top;
    } // [/inBoundary]
} // [/class]
