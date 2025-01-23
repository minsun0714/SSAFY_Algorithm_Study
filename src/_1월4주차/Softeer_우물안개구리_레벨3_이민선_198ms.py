#그래프

import sys
from collections import defaultdict

n, m = map(int, sys.stdin.readline().split())

w_list = [0] + list(map(int, sys.stdin.readline().split()))

better = defaultdict(list)

for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    if w_list[a] >= w_list[b]:
        better[b].append(a)
    if w_list[a] <= w_list[b]:
        better[a].append(b)

answer = n - len(better)
print(answer)
