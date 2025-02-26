import java.util.*;
import java.io.*;

//에라토스테네스의 채, 투포인터
public class 백준_1644_소수의연속합_골드3_한재경_212ms {
    static List<Integer> prime; //{2,3,5,7,11..}

    static int getSum(int n) { //투포인터, n만드는 연속소수의 합 개수
        int l = 0;
        int r = 1;
        int cnt = 0;
        if (prime.contains(n)) {
            cnt++;
        }

        while (l < r && r < prime.size()) {
            int sum = 0;
            for (int i = l; i <= r; i++) {
                sum += prime.get(i);
            }
            if (sum < n) {
                r++;
            } else if (sum > n) {
                l++;
            } else {
                cnt++;
                r++;
            }
        }
        return cnt;
    }

    public static void setPrime(int n) {
        boolean[] p = new boolean[n+1];
        for (int i = 0; i <= n; i++) { //전부 true로 시작
            p[i] = true;
        }
        p[0] = false;
        p[1] = false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!p[i]) { //이미 소수x 메모됐으면
                continue; //건너뛰기
            }
            //i부터 시작하는 배수는
            for (int j = i*i; j <= n; j += i) {
                p[j] = false;
            }
        }
        for (int i = 0; i <= n; i++) {
            if (p[i]) {
                prime.add(i);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        prime = new ArrayList<>();

        setPrime(n);
        System.out.println(getSum(n));
    }
}
