from collections import defaultdict
import sys
sys.setrecursionlimit(10**8)

n, m = map(int, input().split())

people = list(map(int, input().split()))

dict = defaultdict(list)

for i, person in enumerate(people):
    dict[person].append(i + 1)

def dfs(i, w):
    dp[i - 1] += w
    for next in dict[i]:
        dfs(next, dp[i - 1])

dp = [0] * n
for _ in range(m):
    i, w = map(int, input().split())
    dp[i - 1] += w
dfs(1, 0)

print(*dp)