import java.io.*;
import java.util.*;

// 최대공약수. 예외 처리 지옥.....하
public class 백준_15998_카카오머니_골드3_한재경_520ms {
    // 충전금액들의 최대공약수 구하기
    public static long getGcd(long a, long b) {
        if (a % b == 0) {
            return b;
        }
        return getGcd(b, a % b);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long c = 0; //이전 잔액
        long ans = 0; //최소충전단위: 최대공약수
        //현재잔액모음 (출금후 잔액이 최종 gcd보다 큰 경우 존재하는지)!!
        List<Long> bArr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken()); //입금/출금
            long b = Long.parseLong(st.nextToken()); //현재 잔액

            if (a < 0) { //출금
                if (a < -c) { //충전 필요할 때
                    bArr.add(b);
                    long charge = -a - c + b; //c: 이전 잔액
                    ans = getGcd(ans, charge);

                    if (charge <= 0){
                        System.out.println(-1);
                        return;
                    }
                } else { // 충전 없이 출금 가능할 때
                    if (b != c + a) { // 반드시 b == c + a 여야 함
                        System.out.println(-1);
                        return;
                    }
                }
            } else {
                // 입금 이상: 최종금액이 이전잔액 + 입금액이 아닌 경우
                if (b != c + a) {
                    System.out.println(-1);
                    return;
                }
            }

            c = b; //잔액 갱신
        }

        // 출금후 잔액이 최종 gcd보다 큰 경우 존재하는지는 최종 gcd 도출 후 가능
        for (long b : bArr) {
            if (ans > 0 && ans <= b) { //현재잔액이 출금단위 이상
                System.out.println(-1);
                return;
            }
        }

        if (ans == 0) { //충전 없는 경우
            System.out.println(1);
        } else {
            System.out.println(ans);
        }
    }
}
