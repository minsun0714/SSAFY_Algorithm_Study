# 위상정렬

import sys
from collections import defaultdict, deque


def topology_sort():
    q = deque()
    for i in range(1, n + 1):
        if indegree[i] == 0:
            q.append(i)

    while q:
        cur = q.popleft()

        if indegree[cur] == 0:
            print(cur, end=" ")

        for next in graph[cur]:
            if indegree[next] > 0:
                indegree[next] -= 1
                if indegree[next] == 0:
                    q.append(next)

n, m = map(int, sys.stdin.readline().split())
graph = defaultdict(list)
indegree = [0] * (n + 1)
for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    indegree[b] += 1

topology_sort()