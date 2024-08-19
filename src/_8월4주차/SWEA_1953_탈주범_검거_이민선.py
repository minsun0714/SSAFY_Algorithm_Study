# 193ms
# BFS
from collections import deque

T = int(input())


def bfs():
    dx = [[0], [-1, 1, 0, 0], [-1, 1], [0, 0], [-1, 0], [0, 1], [0, 1],[-1, 0]]
    dy = [[0], [0, 0, -1, 1], [0, 0], [-1, 1], [0, 1], [1, 0], [-1, 0],[0,-1]]

    q = deque([(r, c, 1)])

    while q:
        x, y, depth = q.popleft()

        if depth == l:
            return

        x_list = dx[board[x][y]]
        y_list = dy[board[x][y]]

        for i in range(len(x_list)):
            nx, ny = x + x_list[i], y + y_list[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue
            if visited[nx][ny]:
                continue

            nx_list = dx[board[nx][ny]]
            ny_list = dy[board[nx][ny]]

            can_go = False
            for j in range(len(nx_list)):
                nnx, nny = nx + nx_list[j], ny + ny_list[j]

                if nnx == x and nny == y:
                    can_go = True
                    break
            if not can_go:
                continue

            visited[nx][ny] = visited[x][y] + 1
            global answer
            answer += 1


            q.append((nx, ny, depth + 1))

for t in range(1, T + 1):
    n, m, r, c, l = map(int, input().split())

    board = [list(map(int, input().split())) for _ in range(n)]

    visited = [[0] * m for _ in range(n)]
    visited[r][c] = 1

    answer = 1
    bfs()

    print(f"#{t} {answer}")