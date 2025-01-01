import heapq
import sys

n = int(sys.stdin.readline())

board = [list(map(int, list(sys.stdin.readline().strip()))) for _ in range(n)]

def dijkstra(x, y):
    hq = [(0, x, y)]
    heapq.heapify(hq)

    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]

    visited = [[0] * n for _ in range(n)]

    while hq:
        color, x, y = heapq.heappop(hq)

        if x == y == n - 1:
            print(color)
            return

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= n:
                continue
            if visited[nx][ny]:
                continue

            visited[nx][ny] = 1

            heapq.heappush(hq, (color + 1 - board[nx][ny], nx, ny))

dijkstra(0, 0)