import heapq

n, m = map(int, input().split())

board= [list(map(int, list(input()))) for _ in range(m)]

def dijkstra():
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    hq = [(0, 0, 0)]
    heapq.heapify(hq)

    visited = [[0] * n for _ in range(m)]
    visited[0][0] = 1

    while hq:
        cost, x, y = heapq.heappop(hq)

        if x == m - 1 and y == n - 1:
            print(cost)
            return

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or ny < 0 or nx >= m or ny >= n:
                continue
            if visited[nx][ny]:
                continue
            visited[nx][ny] = 1

            heapq.heappush(hq, (cost + board[nx][ny], nx, ny))

dijkstra()