import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// gcd
public class 백준_15998_카카오머니_골드3_이지희_412ms {

    static long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());

        final long[] remains = new long[N];
        int remainIdx = 0;

        long before = 0;
        long gcd = 0;

        for (int n = 0; n < N; n++) {
            String log = br.readLine();
            int spaceIdx = log.indexOf(' '); // 공백 앞 입출금액, 뒤 머니잔액
            long amount = Long.parseLong(log.substring(0, spaceIdx));
            long after = Long.parseLong(log.substring(spaceIdx + 1));

            long result = before + amount;

            if (result < 0) {
                long mq = after - before - amount;
                // 최초 출금 : mq
                // 이후 : gcd 계산
                gcd = (gcd == 0) ? mq : gcd(gcd, mq);

                if (gcd == 1 && (before > 0 || after > 0)) {
                    System.out.println(-1);
                    return;
                }

                remains[remainIdx++] = after;

            } else if (result != after) {
                System.out.println(-1);
                return;
            }

            before = after;
        }

        // gcd가 작아지면서 이전 log 중 잔액이 gcd 이상인 경우가 생기면 paradox
        for (int i = 0; i < remainIdx; i++) {
            if (remains[i] >= gcd) {
                System.out.println(-1);
                return;
            }
        }

        // 한 번도 출금이 필요한 적이 없었을 수도 있다 ( 모든 자연수 M 가능) => 1 출력
        System.out.println(gcd == 0 ? 1 : gcd);
    }
}