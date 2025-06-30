import sys

def dfs(i, j, c):
    target = board[i][j]
    di, dj = [-1, 1, 0, 0], [0, 0, -1, 1]
    visited = [[0] * w for _ in range(h)]
    stack = [(i, j)]

    while stack:
        i, j = stack.pop()

        board[i][j] = c
        visited[i][j] = 1

        for d in range(4):
            ni, nj = i + di[d], j + dj[d]

            if ni < 0 or nj < 0 or ni >= h or nj >= w:
                continue
            if board[ni][nj] != target:
                continue
            if visited[ni][nj]:
                continue
            stack.append((ni, nj))

h, w = map(int, sys.stdin.readline().split())

board = [list(map(int, sys.stdin.readline().split())) for _ in range(h)]

q = int(sys.stdin.readline())

for _ in range(q):
    i, j, c = map(int, sys.stdin.readline().split())
    if board[i - 1][j - 1] != c:
        dfs(i - 1, j - 1, c)
for row in board:
    print(*row)

