from collections import defaultdict
import heapq

n, m, r = map(int, input().split())

items = list(map(int, input().split()))

graph = defaultdict(list)

for _ in range(r):
    a, b, l = map(int, input().split())

    graph[a].append((b, l))
    graph[b].append((a, l))


answer = 0
for root in range(1, n + 1):
    hq = [root]
    heapq.heapify(hq)

    dist = [1e9] * (n + 1)

    dist[root] = 0

    while hq:
        cur = heapq.heappop(hq)
        for next, nc in graph[cur]:

            if dist[next] > dist[cur] + nc:
                dist[next] = dist[cur] + nc
                heapq.heappush(hq, next)

    max_val = 0
    for node, d in enumerate(dist):
        if d <= m:
            max_val += items[node - 1]
    answer = max(answer, max_val)
print(answer)