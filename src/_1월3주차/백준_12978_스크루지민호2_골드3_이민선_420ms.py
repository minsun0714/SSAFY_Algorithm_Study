import sys
sys.setrecursionlimit(10**8)
from collections import defaultdict

n = int(sys.stdin.readline())

graph = defaultdict(list)
for _ in range(n - 1):
    u, v = map(int, sys.stdin.readline().split())

    graph[u].append(v)
    graph[v].append(u)


def dfs(cur):

    visited[cur] = 1
    dp[cur][1] = 1

    for next in graph[cur]:
        if not visited[next]:
            dfs(next)
            dp[cur][0] += dp[next][1]
            dp[cur][1] += min(dp[next][0], dp[next][1])

visited  = [0] * (n + 1)
dp = [[0] * 2 for _ in range(n + 1)]
dfs(1)
print(min(dp[1]))