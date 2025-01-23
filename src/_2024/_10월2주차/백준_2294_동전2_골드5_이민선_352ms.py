# dp
n, k = map(int, input().split())

dp = [1e9] * (k + 1)

coins = []

for _ in range(n):
    coin = int(input())
    coins.append(coin)
    if coin <= k:
        dp[coin] = 1

for i in range(1, k + 1):
    for coin in coins:
        if i > coin:
            dp[i] = min(dp[i], dp[i - coin] + 1)

print(dp[-1] if dp[-1] < 1e9 else -1)
