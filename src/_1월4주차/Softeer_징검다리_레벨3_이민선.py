import sys

n = int(sys.stdin.readline())

nums = list(map(int, sys.stdin.readline().split()))

dp = [0] * n
dp[0] = 1
for i in range(1, n):
    for j in range(i):
        if nums[i] > nums[j]:
            dp[i] = max(dp[i], dp[j])
    dp[i] += 1
print(max(dp))