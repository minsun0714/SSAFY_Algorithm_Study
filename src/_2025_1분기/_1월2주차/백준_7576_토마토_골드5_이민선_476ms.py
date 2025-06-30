from collections import deque

m, n = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(n)]

def bfs(q):
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]

    while q:
        x, y, depth = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue

            if board[nx][ny] != 0:
                continue

            board[nx][ny] = depth

            q.append((nx, ny, depth + 1))

q = deque()
zero_count = 0
for x in range(n):
    for y in range(m):
        if board[x][y] == 1:
            q.append((x, y, 1))
        elif board[x][y] == 0:
            zero_count += 1

if zero_count == 0:
    print(0)
else:
    bfs(q)
    if [row.count(0) for row in board].count(0) != n:
        print(-1)
    else:
        print(max(max(row) for row in board))