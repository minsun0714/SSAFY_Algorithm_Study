# tree dp
import sys
sys.setrecursionlimit(10**6)

n = int(input())

population = list(map(int, input().split()))

tree = [[] for _ in range(n + 1)]
for _ in range(n - 1):
    a, b = map(int, input().split())

    tree[a].append(b)
    tree[b].append(a)

def dfs(root):
    visited[root] = 1
    for next in tree[root]:
        if visited[next]:
            continue
        dfs(next)
        dp[root][0] += dp[next][1]
        dp[root][1] += max(dp[next][0], dp[next][1])



dp = [[0] * 2] + [[population[i], 0] for i in range(n)]
visited = [0] * (n + 1)
dfs(1)

print(max(dp[1]))
