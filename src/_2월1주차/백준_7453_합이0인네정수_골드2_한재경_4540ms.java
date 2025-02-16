import java.io.*;
import java.util.*;

// 투포인터, 4540ms, 어째서 리스트는 시간초과날까..
public class 백준_7453_합이0인네정수_골드2_한재경_4540ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        //여기서 ab, cd배열 리스트로 하면 시간초과남!!
        int size = n * n; // A+B, C+D 배열의 크기
        int[] ab = new int[size]; // A+B의 합 배열
        int[] cd = new int[size]; // C+D의 합 배열
        int index = 0;

        // A+B와 C+D의 모든 조합을 저장 (O(N^2))
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[index] = a[i] + b[j];
                cd[index] = c[i] + d[j];
                index++;
            }
        }

        // ab, cd 배열 정렬 (O(N^2 log N^2) ≈ O(N^2 log N))
        Arrays.sort(ab);
        Arrays.sort(cd);

        // 투 포인터 사용 (O(N^2))
        int left = 0, right = size - 1;
        long count = 0;

        while (left < size && right >= 0) {
            int abSum = ab[left];
            int cdSum = cd[right];
            int sum = abSum + cdSum;
            long abCount = 0;
            long cdCount = 0;

            if (sum == 0) { // 합이 0이면 개수 세기
                while (left < size && ab[left] == abSum) {
                    left++;
                    abCount++;
                }
                while (right >= 0 && cd[right] == cdSum) {
                    right--;
                    cdCount++;
                }
                count += abCount * cdCount;
            } else if (sum < 0) { // 합이 작으면 A+B 값을 증가
                left++;
            } else { // 합이 크면 C+D 값을 감소
                right--;
            }
        }

        System.out.println(count);
    }
}
