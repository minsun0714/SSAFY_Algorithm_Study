# tree dp
from collections import defaultdict
import sys
sys.setrecursionlimit(10**9)

def dfs(root):
    visited[root] = 1
    print(root)
    for child in graph[root]:
        if not visited[child]:
            dfs(child)
            visited[root] += visited[child]

n, r, q = map(int, sys.stdin.readline().split())
graph = defaultdict(list)
for _ in range(n - 1):
    u, v = map(int, sys.stdin.readline().split())
    graph[u].append(v)
    graph[v].append(u)


visited = [0] * (n + 1)
dfs(r)
for _ in range(q):
    query = int(sys.stdin.readline())
    print(visited[query])