package etc._6_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// GCD
// 이 문제 낸 사람 반성하세요
// 이 문제 낸 사람 반성하세요
public class 백준_15998_카카오머니_골드3_김동빈_600ms {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long balance = 0;
        long M = 0;
        
        long MaxB = 0L;
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());  // 입출금
            long b = Long.parseLong(st.nextToken());  // 잔액
            
            // 입금
            if (a > 0) {
                balance += a;
                if (balance != b) {
                    System.out.println(-1);
                    return;
                }
                continue;
            }
            
            // 출금
            long needed = -a;
            
            // 잔액 충분
            if (balance >= needed) {
                balance -= needed;
                if (balance != b) {
                    System.out.println(-1);
                    return;
                }
                continue;
            }
            
            // 잔액 부족
            long diff = b - balance + needed;  // 충전할 금액
            if (diff < 0) {
            	System.out.println(-1);
            	return;
            }
            
            M = (M == 0) ? diff : gcd(M, diff);
        	MaxB = Math.max(MaxB, b);
        	
        	if (M < MaxB) {
        		System.out.println(-1);
        		return;
        	}
            
            if (M == 1 && b > 0) {
            	System.out.println(-1);
            	return;
            }
            
            balance = b;
        }
        System.out.println(M > 0 ? M : 1);
    }

    static long gcd(long a, long b) {
    	if (b > a) {
    		long temp = a;
    		a = b;
    		b = temp;
    	}
    	
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
