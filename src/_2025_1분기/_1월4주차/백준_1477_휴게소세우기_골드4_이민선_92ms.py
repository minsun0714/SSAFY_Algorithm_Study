import sys

n, m, l = map(int, sys.stdin.readline().split())
if n == 0:
    nums = [l]
else:
    nums = list(map(int, sys.stdin.readline().split())) + [l]

nums.sort()

s, e = 1, l - 2
answer = 0
while s <= e:
    mid = (s + e) // 2

    prev = 0
    count = 0
    for num in nums:
        count += (num - prev - 1) // mid
        prev = num
    if count > m:
        s = mid + 1
    else:
        e = mid - 1
        answer = mid

print(answer)