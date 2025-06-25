#include <iostream>
#include <numeric>

using namespace std;

long long gcd(long long x, long long y) {
    return y == 0 ? x : gcd(y, x % y);
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N; 
    cin >> N;

    long long balance = 0;
    long long result = 0;
    long long max_balance = 0;       // 충전 이전 잔액

    for(int i = 0; i < N; i++){
        long long a, b;
        cin >> a >> b;

        if (a > 0) {
            balance += a;
        }
        else {
            long long w = -a;  // 출금액
            if (balance < w) {
                // 충전이 필요한 경우
                long long temp = b - balance + w;  
                // temp = k * M 이므로 최대공약수 갱신
                if (result == 0) result = temp;
                else result = gcd(result, temp);
                // 충전 후 잔액 b 기록
                max_balance= max(max_balance, b);
                balance = b;
            }
            else {
                balance -= w;
            }
        }

        // 잔액 검증
        if (balance != b) {
            cout << -1;
            return 0;
        }
    }

    // 충전 안해도 됨
    if (result == 0) {
        cout << 1;
        return 0;
    }
    // result 없을 경우
    if (result <= max_balance) {
        cout << -1;
    } else {
        cout << result;
    }
    return 0;
}
