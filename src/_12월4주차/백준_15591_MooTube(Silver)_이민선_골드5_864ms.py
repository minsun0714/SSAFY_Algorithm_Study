# bfs
from collections import defaultdict, deque

N, Q = map(int, input().split())

graph = defaultdict(list)
for _ in range(N - 1):
    n, q, r = map(int, input().split())
    graph[n].append((q, r))
    graph[q].append((n, r))

# BFS 기반으로 유사도 조건을 만족하는 노드 수를 계산
def bfs_count(start, k):
    visited = [False] * (N + 1)
    visited[start] = True
    queue = deque([start])
    count = 0

    while queue:
        cur = queue.popleft()
        for next, usado in graph[cur]:
            if not visited[next] and usado >= k:
                visited[next] = True
                count += 1
                queue.append(next)
    return count

# 쿼리 처리
results = []
for _ in range(Q):
    k, v = map(int, input().split())
    results.append(bfs_count(v, k))

# 결과 출력
print(*results, sep='\n')
