import sys

n, m = map(int, sys.stdin.readline().split())

nums = [int(sys.stdin.readline()) for _ in range(n)]

nums.sort()

s, e = min(nums), max(nums) * m
answer = 0
while s <= e:
    mid = (s + e) // 2

    count = 0
    for num in nums:
        count += mid // num

    if count >= m:
        answer = mid
        e = mid - 1
    else:
        s = mid + 1

print(answer)