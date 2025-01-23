import heapq
t = 1
while True:
    n = int(input())
    if n == 0:
        break

    board = [list(map(int, input().split())) * n for _ in range(n)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    visited = [[0] * n for _ in range(n)]
    visited[0][0] = 1

    hq = [(board[0][0], 0, 0)]
    heapq.heapify(hq)

    while hq:
        cost, x, y = heapq.heappop(hq)

        if x == y == n - 1:
            print(f"Problem {t}: {cost}")
            t += 1
            break

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= n:
                continue
            if visited[nx][ny]:
                continue

            visited[nx][ny] = 1

            heapq.heappush(hq, (cost + board[nx][ny], nx, ny))
