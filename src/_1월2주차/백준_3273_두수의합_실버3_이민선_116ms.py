n = int(input())

nums = list(map(int, input().split()))
nums.sort()

x = int(input())
answer = 0
s, e = 0, n - 1
while s < e:
    if nums[s] + nums[e] == x:
        answer += 1
        s += 1
        e -= 1
    elif nums[s] + nums[e] < x:
        s += 1
    else:
        e -= 1

print(answer)