import sys

n, c = map(int, sys.stdin.readline().split())

nums = [int(sys.stdin.readline()) for _ in range(n)]

nums.sort()

s, e = 0, nums[-1] - nums[0]
answer = 0
while s <= e:
    mid = (s + e) // 2

    total = 1
    first = nums[0]
    for num in nums:
        if num - first >= mid:
            first = num
            total += 1
    if total >= c:
        s = mid + 1
        answer = mid
    else:
        e = mid - 1

print(answer)