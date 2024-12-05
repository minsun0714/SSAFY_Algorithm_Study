from collections import defaultdict

n = int(input())

roots = [1] * (n + 1)

graph = defaultdict(list)

for _ in range(n - 1):
    u, v = map(int, input().split())
    graph[u].append(v)
    roots[v] = 0

def dfs(root):
    if dp[root]:
        return dp[root]

    count = 0
    for next in graph[root]:
        count += dfs(next)
    dp[root] = count
    return dp[root]

dp = [0] * (n + 1)
for root in roots[1:]:
    if not root:
        continue
    dfs(root)

print(dp)