# 다익스트라

import heapq
import sys

m, n = map(int, sys.stdin.readline().split())

board = [list(map(int, list(sys.stdin.readline().strip()))) for _ in range(n)]

def dijkstra():
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
    visited = [[0] * m for _ in range(n)]
    hq = [(0, 0, 0)]
    heapq.heapify(hq)
    while hq:
        w, x, y = heapq.heappop(hq)
        if x == n - 1 and y == m - 1:
            return w
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue
            if visited[nx][ny]:
                continue
            visited[nx][ny] = 1

            heapq.heappush(hq, (w + board[nx][ny], nx, ny))

print(dijkstra())