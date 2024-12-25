import sys
sys.setrecursionlimit(10**9)

m, n = map(int, input().split())

board = [list(map(int, sys.stdin.readline().split())) for _ in range(m)]
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
def dfs(x, y):
    if x == m - 1 and y == n - 1:
        return 1

    if dp[x][y] != -1:
        return dp[x][y]

    dp[x][y] = 0
    count = 0
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]

        if nx < 0 or ny < 0 or nx >= m or ny >= n:
            continue


        if board[nx][ny] >= board[x][y]:
            continue
        count += dfs(nx, ny)

    dp[x][y] = count
    return dp[x][y]

dp = [[-1] * n for _ in range(m)]
dfs(0, 0)
print(dp[0][0])