n = int(input())

nums = list(map(int, input().split()))

nums.sort()

s, e = 0, n - 1
answer = nums[s], nums[e]
while s < e :

    if abs(nums[s] + nums[e]) < abs(sum(answer)):
        answer = nums[s], nums[e]

    if nums[s] + nums[e] < 0:
        s += 1
    else:
        e -= 1


print(answer[0], answer[1])