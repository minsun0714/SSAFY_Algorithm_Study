#include <iostream>
#include <vector>

using namespace std;

int N, R, G, B;
long f2[6] = {1, 2, 6, 20, 70, 252}; //2개씩 조합 경우의 수
long f3[4] = {1, 6, 90, 1680}; //3개씩 조합 경우의 수
vector<vector<vector<vector<long>>>> memo;
long dfs(int m, int r, int g, int b) {
    if (r < 0 || g < 0 || b < 0) return 0;
    if (m == 0) return 1;
    long& res = memo[m][r][g][b];
    if (res != -1) return res;
    res = 0;

    // 1개씩
    res += dfs(m-1, r-m, g,   b)
         + dfs(m-1, r,   g-m, b)
         + dfs(m-1, r,   g,   b-m);

    // 2개씩
    if (m % 2 == 0) {
        int t = m/2;
        res += f2[t] * (
            dfs(m-1, r-t, g-t, b) +
            dfs(m-1, r,   g-t, b-t) +
            dfs(m-1, r-t, g,   b-t)
        );
    }

    // 3개씩
    if (m % 3 == 0) {
        int t = m/3;
        res += f3[t] * dfs(m-1, r-t, g-t, b-t);
    }

    return res;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> R >> G >> B;
    //초기화
    memo.assign(
        N+1,
        vector<vector<vector<long>>>(
            R+1,
            vector<vector<long>>(
                G+1,
                vector<long>(B+1, -1)
            )
        )
    );

    // dfs 호출 후 결과 출력
    cout << dfs(N, R, G, B) << endl;
    return 0;
}