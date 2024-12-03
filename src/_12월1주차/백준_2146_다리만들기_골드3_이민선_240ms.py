# dfs, bfs

from collections import deque
import sys
sys.setrecursionlimit(10**6)

n = int(input())

board = [list(map(int, input().split())) for _ in range(n)]

def dfs(x, y, num):
    if x < 0 or y < 0 or x >= n or y >= n:
        return False

    if board[x][y] == num or board[x][y] == 0:
        return False

    board[x][y] = num

    dfs(x - 1, y, num)
    dfs(x + 1, y, num)
    dfs(x, y - 1, num)
    dfs(x, y + 1, num)

num = 2
for i in range(n):
    for j in range(n):
        if board[i][j] == 1:
            dfs(i, j, num)
            num += 1

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(x, y):
    global answer
    q = deque([(x, y, board[x][y])])
    visited = [[0] * n for _ in range(n)]
    while q:
        x, y, num = q.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= n:
                continue

            if visited[nx][ny]:
                continue

            if board[nx][ny] == num:
                continue

            if board[nx][ny]:
                answer = min(answer, visited[x][y])
                return
            visited[nx][ny] = visited[x][y] + 1


            q.append((nx, ny, num))

answer = 1e9
for i in range(n):
    for j in range(n):
        if board[i][j]:
            for k in range(4):
                nx, ny = i + dx[k], j + dy[k]
                if 0 <= nx < n and 0 <= ny < n:
                    if board[nx][ny] == 0:
                        bfs(i, j)
                        break
print(answer)