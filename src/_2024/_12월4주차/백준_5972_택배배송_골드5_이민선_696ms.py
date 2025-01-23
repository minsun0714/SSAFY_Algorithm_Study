# 다익스트라

import heapq
from collections import defaultdict

n, m = map(int, input().split())

graph = defaultdict(list)
for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

def dijkstra(start, end):
    hq = [start]
    heapq.heapify(hq)
    dist = [1e9] * (n + 1)
    dist[start] = 0

    while hq:
        cur = heapq.heappop(hq)

        for next, d in graph[cur]:
            if dist[cur] + d < dist[next]:
                dist[next] = dist[cur] + d

                heapq.heappush(hq, next)

    return dist

print(dijkstra(1, n)[n])
