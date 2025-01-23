# tree dp

import sys
sys.setrecursionlimit(10**8)
from collections import defaultdict

input = sys.stdin.read
data = input().split()

n = int(data[0])

tree = defaultdict(list)
index = 1
for _ in range(n - 1):
    u, v = map(int, data[index:index + 2])
    index += 2

    tree[u].append(v)
    tree[v].append(u)

def dfs(root):
    visited[root] = 1
    for child in tree[root]:
        if not visited[child]:
            dfs(child)
            dp[root][0] += dp[child][1]
            dp[root][1] += min(dp[child][0], dp[child][1])


visited = [0] * (n + 1)
dp = [[0, 1] for _ in range(n + 1)]
dfs(1)
print(min(dp[1]))