#dfs
import sys
sys.setrecursionlimit(10**8)
from collections import defaultdict


def dfs(cur):
    visited[cur] = 1
    for next, nc in graph[cur]:
        if visited[next]:
            continue
        dp[next] = dp[cur] + nc
        dfs(next)

n = int(sys.stdin.readline())

graph = defaultdict(list)

for _ in range(n - 1):
    a, b, c = map(int, sys.stdin.readline().split())

    graph[a].append((b, c))
    graph[b].append((a, c))

visited = [0] * (n + 1)
dp = [0] * (n + 1)
dfs(1)
max_val = max(dp)
start = dp.index(max_val)


visited = [0] * (n + 1)
dp = [0] * (n + 1)
dfs(start)
print(max(dp))