# dp
n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]

dp = [[0] * (n + 1) for _ in range(n + 1)]

max_val = -1e9
for i in range(1, n + 1):
    for j in range(1, n + 1):
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + board[i - 1][j - 1]
        for k in range(1, min(i, j) + 1):
            max_val = max(max_val, dp[i][j] - dp[i - k][j] - dp[i][j - k] + dp[i - k][j - k])


print(max_val)