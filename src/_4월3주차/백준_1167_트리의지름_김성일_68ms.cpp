#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
// DP
using namespace std;

const int MAX = 100001;

vector<pair<int, int>> tree[MAX];
bool visited[MAX];
int globalMaxDistance = 0;

int DFS(int nodeId) {
    visited[nodeId] = true;
    int big1 = 0, big2 = 0;

    for (auto [next, weight] : tree[nodeId]) {
        if (!visited[next]) {
            int ret = DFS(next) + weight;
            if (ret > big1) {
                big2 = big1;
                big1 = ret;
            } else if (ret > big2) {
                big2 = ret;
            }
        }
    }

    globalMaxDistance = max(globalMaxDistance, big1 + big2);
    return big1;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int V;
    cin >> V;

    for (int i = 0; i < V; ++i) {
        int from;
        cin >> from;
        while (true) {
            int to, weight;
            cin >> to;
            if (to == -1) break;
            cin >> weight;
            tree[from].emplace_back(to, weight);
        }
    }

    DFS(1);
    cout << globalMaxDistance << '\n';

    return 0;
}
