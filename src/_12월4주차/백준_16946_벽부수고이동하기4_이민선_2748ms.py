import sys
from collections import defaultdict

n, m = map(int, input().split())

board = [list(map(int, list(input()))) for _ in range(n)]

# 각 0의 구역마다 인접한 0의 개수를 센다.
def dfs(x, y):
    stack = [(x, y)]

    while stack:
        x, y = stack.pop()
        visited[x][y] = count
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue
            if visited[nx][ny]:
                continue
            if board[nx][ny] == 1:
                continue
            stack.append((nx, ny))

dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
visited = [[0] * m for _ in range(n)]
count = 0
for i in range(n):
    for j in range(m):
        if board[i][j] == 0 and visited[i][j] == 0:
            count += 1
            dfs(i, j)

counter = defaultdict(int)
for i in range(n):
    for j in range(m):
        if visited[i][j] != 0:
            counter[visited[i][j]] += 1

for x in range(n):
    for y in range(m):
        if board[x][y] == 1:
            s = set()
            for d in range(4):
                nx, ny = x + dx[d], y + dy[d]
                if nx < 0 or ny < 0 or nx >= n or ny >= m:
                    continue
                if board[nx][ny] == 1:
                    continue
                s.add(visited[nx][ny])
            for num in s:
                board[x][y] += counter[num]

for row in board:
    for num in row:
        print((num % 10), end="")
    print()