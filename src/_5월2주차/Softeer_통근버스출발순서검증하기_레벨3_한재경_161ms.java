import java.io.*;
import java.util.*;

//브루트포스
public class Softeer_통근버스출발순서검증하기_레벨3_한재경_161ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[] bus = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bus[i] = Integer.parseInt(st.nextToken());
        }

        long result = 0;
        // i를 기준점으로 잡고, i<j<k 이면서 bus[i]<bus[j]이고 bus[i]>bus[k]인 쌍을 센다.
        for (int i = 0; i < N - 1; i++) {
            long bigger = 0;
            // j가 i 이후에 등장하면서 bus[j]가 bus[i]보다 크면 bigger++
            // 그렇지 않으면 (즉 bus[k] < bus[i]인 k자리마다) 지금까지의 bigger 값을 결과에 더함
            for (int j = i + 1; j < N; j++) {
                if (bus[i] < bus[j]) {
                    bigger++;
                } else {
                    result += bigger;
                }
            }
        }

        System.out.println(result);
    }
}
