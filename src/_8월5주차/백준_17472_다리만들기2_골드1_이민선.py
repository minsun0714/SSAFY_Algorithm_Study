from collections import defaultdict

n, m = map(int, input().split())


board = [[x for x in map(int, input().split())] for _ in range(n)]


def dfs(x, y, count):
    if x < 0 or y < 0 or x >= n or y >= m:
        return
    if board[x][y] != 1:
        return

    board[x][y] = count

    dfs(x - 1, y, count)
    dfs(x + 1, y, count)
    dfs(x, y - 1, count)
    dfs(x, y + 1, count)


count = 1
for i in range(n):
    for j in range(m):
        if board[i][j] == 1:
            count += 1
            dfs(i, j, count)


def make_bridge(x, y, length):
    if x < 0 or y < 0 or x >= n or y >= m:
        return
    if board[x][y] != 0 and board[x][y] != board[i][j]:
        if length > 1:
            info[board[x][y]].append((board[i][j], length))
        return

    if board[x][y] == 0:
        board[x][y] = 1
        make_bridge(x + dx[k], y + dy[k], length + 1)
        board[x][y] = 0


info = defaultdict(list)


dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
for i in range(n):
    for j in range(m):
        if board[i][j] > 1:
            for k in range(4):
                make_bridge(i + dx[k], j + dy[k], 0)


print(info)


